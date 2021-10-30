package hello.itemservice.domain.item;

import lombok.Data;

import java.util.List;

@Data
public class UploadItem {
    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile>imageFile;

}
