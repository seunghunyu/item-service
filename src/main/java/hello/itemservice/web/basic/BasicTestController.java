package hello.itemservice.web.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic/sendForm")
public class BasicTestController {
    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String items(){
        log.info("sendForm controller");
        return "basic/sendForm";
    }


    @PostMapping("send")
    String getDate(@PathVariable String itemName, Model model){
        model.addAttribute("itemName",itemName);
        return "basic/sendForm";
    }
}
