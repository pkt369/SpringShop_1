package sejun.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sejun.shop.domain.Member;
import sejun.shop.domain.Product;
import sejun.shop.service.BasketService;
import sejun.shop.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class ShopMainController {

    private final ProductService productService;
    private final BasketService basketService;

    @Autowired
    public ShopMainController(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
    }


    @GetMapping("/")
    public String nologinIndex(Principal principal, Model model, Authentication authentication){

        return "index";
    }

    @GetMapping("/user")
    public String UserIndex(Principal principal, Model model, Authentication authentication){


        return "/shopMain/home";
    }

    @GetMapping("/user/product/ladderBarrel")
    public String product_ladderBarrel(Model model){
        List<Product> result = productService.bringProduct("레더바렐");

        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);

        return "/shopMain/product";
    }

    @GetMapping("/user/product/gloves")
    public String product_gloves(Model model){
        List<Product> result = productService.bringProduct("반장갑");
        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);
        return  "/shopMain/product";
    }

    @GetMapping("/user/product/bosuBall")
    public String product_bosuBall(Model model){
        List<Product> result = productService.bringProduct("보수볼");
        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);
        return  "/shopMain/product";
    }

    @GetMapping("/user/product/yogaRing")
    public String product_yogaRing(Model model){
        List<Product> result = productService.bringProduct("요가링");
        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);
        return  "/shopMain/product";
    }

    @GetMapping("/user/product/gyroBall")
    public String product_gyroBall(Model model){
        List<Product> result = productService.bringProduct("자이로볼");

        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);
        return  "/shopMain/product";
    }

    @GetMapping("/user/product/pushUpBar")
    public String product_pushUpBar(Model model){
        List<Product> result = productService.bringProduct("푸시업바");
        model.addAttribute("index_0",result.get(0));
        model.addAttribute("listSize", result.size());
        model.addAttribute("list", result);
        return  "/shopMain/product";
    }

    @GetMapping("/user/product/basket")
    public void intoBasket(
            @RequestParam("product_name") String name,
            @RequestParam("option") String option,
            @RequestParam("coupon") String coupon){

        System.out.println(name + option + coupon);


        Product product = productService.optionBringProduct(name, option);

        String price = product.getPrice();
        int price_int = Integer.parseInt(price.substring(0, price.indexOf("원")));
        int sale = price_int - Integer.parseInt(coupon);
        product.setPrice(Integer.toString(sale)); //할인받은 돈을 넣어줌

        basketService.inputBasket(product); //상품을 장바구니에 넣어줌.

        Map<Integer, Product> productMap = basketService.bringBasket();
        System.out.println(productMap.get(1));


        /*return "redirect: /user";*/
    }

    public static String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member user = (Member) authentication.getPrincipal();
        return user.getName();

    }
}
