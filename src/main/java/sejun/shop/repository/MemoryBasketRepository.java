package sejun.shop.repository;

import org.springframework.stereotype.Repository;
import sejun.shop.domain.Product;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryBasketRepository {

    private static Map<Integer, Product> basket= new HashMap<>();
    private static int sequence = 0;

    public void save(Product product){
        basket.put(++sequence, product);
    }

    public void delete(Product product){
        basket.remove(product);
    }

    public Map<Integer, Product> findAll(){
        return basket;
    }

}
