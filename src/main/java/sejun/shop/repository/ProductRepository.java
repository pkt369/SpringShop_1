package sejun.shop.repository;

import org.springframework.security.core.parameters.P;
import sejun.shop.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findByName(String name);
    Optional<Product> findByOptionName(String name, String option);
    List<Product> findAll();
}
