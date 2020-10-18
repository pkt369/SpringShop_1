package sejun.shop.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sejun.shop.domain.BuyList;

import java.util.List;

@Repository
public class JdbcBuyListRepository implements BuyListRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcBuyListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BuyList save(BuyList buyList) {
        jdbcTemplate.update("insert into buyList values (?, ?, ?, ?, ?, ?, ?, ?)",
                buyList.getProductName(), buyList.getPrice(), buyList.getOption(), buyList.getUsername(),
                buyList.getAddress(), buyList.getPhone(), buyList.getNeed(), buyList.getPay());
        return buyList;
    }

    @Override
    public List<BuyList> bringList() {
       return jdbcTemplate.query("select * from buyList", listRowMapper());
    }


    private RowMapper<BuyList> listRowMapper(){
        return (rs, rowNum) -> {
            BuyList buyList = new BuyList();
            buyList.setProductName(rs.getString("productName"));
            buyList.setPrice(rs.getString("price"));
            buyList.setOption(rs.getString("option"));
            buyList.setUsername(rs.getString("username"));
            buyList.setAddress(rs.getString("address"));
            buyList.setPhone(rs.getString("phone"));
            buyList.setNeed(rs.getString("need"));
            buyList.setPay(rs.getString("pay"));
            return buyList;
        };
    }
}
