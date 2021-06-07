package hello.itemservice.web.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class BasicRestController {
    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/{id}", produces = "application/json")
    public String items(@PathVariable String id){
        log.info("sendForm controller");
        return "유승훈";
    }

}
