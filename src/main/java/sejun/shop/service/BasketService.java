package sejun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import sejun.shop.domain.Product;
import sejun.shop.repository.MemoryBasketRepository;

import java.util.ArrayList;
import java.util.List;
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
    public List<Product> bringBasket(){
        return basketRepository.findAll();
    }

    /*
    * basket에서 상품 삭제
    * */
    public void deleteBasket(Product product){
        basketRepository.delete(product);
    }
}
