package sejun.shop.repository;

import org.springframework.stereotype.Repository;
import sejun.shop.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryBasketRepository {

    private static ArrayList<Product> basket= new ArrayList<>();
    private static int sequence = 0;

    public void save(Product product){
        basket.add(product);
    }

    public void delete(Product product){
        basket.remove(product);
    }

    public ArrayList<Product> findAll(){
        return basket;
    }



}
