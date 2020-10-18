package sejun.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sejun.shop.domain.BuyList;
import sejun.shop.domain.Product;
import sejun.shop.service.BasketService;
import sejun.shop.service.BuyService;
import sejun.shop.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ShopMainController {

    private final ProductService productService;
    private final BasketService basketService;
    private final BuyService buyService;

    @Autowired
    public ShopMainController(ProductService productService, BasketService basketService, BuyService buyService) {
        this.productService = productService;
        this.basketService = basketService;
        this.buyService = buyService;
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

    @PostMapping("/user/product/basket")
    public String intoBasket(
            @RequestParam("product_name") String name,
            @RequestParam("option") String option,
            @RequestParam("coupon") String coupon){

        System.out.println(name + option + coupon);


        Product product = productService.optionBringProduct(name, option);

        String price = product.getPrice();
        int price_int = Integer.parseInt(price.substring(0, price.indexOf("원")));
        int sale = price_int - Integer.parseInt(coupon);
        product.setPrice(Integer.toString(sale) + "원"); //할인받은 돈을 넣어줌

        basketService.inputBasket(product); //상품을 장바구니에 넣어줌.

        return "redirect:/user";
    }

    @PostMapping("/user/product/basket/buy")
    public String goBasket(
            @RequestParam("product_name") String name,
            @RequestParam("option") String option,
            @RequestParam("coupon") String coupon){



        Product product = productService.optionBringProduct(name, option);

        String price = product.getPrice();
        int price_int = Integer.parseInt(price.substring(0, price.indexOf("원")));
        int sale = price_int - Integer.parseInt(coupon);
        product.setPrice(Integer.toString(sale) + "원"); //할인받은 돈을 넣어줌

        basketService.inputBasket(product); //상품을 장바구니에 넣어줌.

        return "redirect:/user/basket";
    }

    @GetMapping("/user/basket")
    public String basket(Model model){
        List<Product> products = basketService.bringBasket();
        int size = products.size();
        int totalPrice = 0;
        for(int i = 0; i < products.size(); i++){
            String price = products.get(i).getPrice();
            int price_int = Integer.parseInt(price.substring(0, price.indexOf("원")));
            totalPrice += price_int;
        }

        model.addAttribute("size", size);
        model.addAttribute("list", products);
        model.addAttribute("totalPrice", totalPrice);

        return "/shopMain/basket";
    }

    /*
    * Basket 삭제 구간
    * */
    @PostMapping("/user/basket/delete/{productName}")
    public String deleteProduct(@PathVariable String productName){


        List<Product> products = basketService.bringBasket();

        Optional<Product> result = products.stream().filter(product -> product.getName().equals(productName)).findAny();
        //찾은 값을 삭제해주는 것

        basketService.deleteBasket(result.get());

        return "redirect:/user/basket";
    }

    @PostMapping("/user/buy")
    public String writeBuyerInfo(Model model, Principal principal){
        model.addAttribute("username", principal.getName());

        return "/shopMain/buy";
    }

    @PostMapping("/user/buy/send")
    public String sendBuyerInfo(@RequestParam("name") String name,
                                @RequestParam("address") String address,
                                @RequestParam("phone") String phone,
                                @RequestParam("need") String need,
                                @RequestParam("pay") String pay){
        List<Product> products = basketService.bringBasket();
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).getName());
            Product product = products.get(i);
            BuyList buyList = new BuyList();
            buyList.setProductName(product.getName()); //이름
            buyList.setPrice(product.getPrice()); //가격
            buyList.setOption(product.getOption());//옵션
            buyList.setUsername(name);
            buyList.setAddress(address);
            buyList.setPhone(phone);
            buyList.setNeed(need);
            buyList.setPay(pay);

            buyService.save(buyList);
        }

        return "redirect:/user/buy/success";

    }

    @GetMapping("/user/buy/success")
    public String BuySuccess(){
        return "/shopMain/success";
    }

}
