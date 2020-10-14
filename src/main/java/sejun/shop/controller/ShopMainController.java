package sejun.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopMainController {

    @GetMapping("/")
    public String shopIndex(Model model){
        return "shopMain/index";
    }
}
