package sejun.shop.service;

import org.springframework.stereotype.Service;
import sejun.shop.domain.Product;
import sejun.shop.repository.JdbcProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final JdbcProductRepository productRepository;

    public ProductService(JdbcProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * 상품 가져오기
     * */
    public List<Product> bringProduct(String name){
        return productRepository.findByName(name);
    }

    /*
    * 옵션 포함 상품 가져오기
    * */
    public Product optionBringProduct(String name, String option){
        return productRepository.findByOptionName(name, option).get();
    }

    public List<Product> bringProductAll() { return productRepository.findAll(); }
}
