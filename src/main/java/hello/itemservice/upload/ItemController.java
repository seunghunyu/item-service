package hello.itemservice.upload;

import hello.itemservice.domain.item.UploadFile;
import hello.itemservice.domain.item.UploadItem;
import hello.itemservice.domain.item.UploadItemRepository;
import hello.itemservice.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final UploadItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form){
        return "item-form";
    }

    @PostMapping("items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {

        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        //데이터베이스에 저장
        UploadItem item = new UploadItem();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFile(storeImageFiles);
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }
    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        UploadItem item = itemRepository.findById(id);
        model.addAttribute("item",item);
        return "item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+ fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        UploadItem item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        log.info("uploadFileName = {}", uploadFileName);

        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        //String contentDisposition = "attachment; filename=\""+uploadFileName+"\"";
        String contentDisposition = "attachment; filename=\""+encodeUploadFileName+"\"";
        /* 파일을 읽어서 페이지로 보여줌
        return ResponseEntity.ok()
                .body(urlResource);
        */
        //파일을 다운로드 하게함
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);

    }
}
