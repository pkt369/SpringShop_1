package sejun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejun.shop.domain.Product;
import sejun.shop.repository.MemoryBasketRepository;

import java.util.Map;

@Service
public class BasketService {

    private final MemoryBasketRepository basketRepository;

    @Autowired
    public BasketService(MemoryBasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    /*
    * 상품 넣기
    * */
    public void inputBasket(Product product){
        basketRepository.save(product);
    }

    /*
    * 상품 꺼내기
    * */
    public Map<Integer, Product> bringBasket(){
        return basketRepository.findAll();
    }
}
