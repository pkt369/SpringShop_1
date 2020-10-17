package sejun.shop.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sejun.shop.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = jdbcTemplate.query("select * from PRODUCT where name=?", productRowMapper(), name);
        return result;
    }

    @Override
    public Optional<Product> findByOptionName(String name, String option) {
        List<Product> result = jdbcTemplate.query("select * from product where name=? and option=?", productRowMapper(), name, option);
        return result.stream().findAny();
    }


    private RowMapper<Product> productRowMapper(){
        return (rs, rowNum) -> {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setOption(rs.getString("option"));
            product.setSort(rs.getString("sort"));
            product.setDetailUrl(rs.getString("detailUrl"));
            product.setUrl(rs.getString("url"));
            return product;
        };
    }
}
