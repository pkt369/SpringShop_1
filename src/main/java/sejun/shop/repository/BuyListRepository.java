package sejun.shop.repository;

import sejun.shop.domain.BuyList;

import java.util.List;

public interface BuyListRepository {
    BuyList save(BuyList buyList);
    List<BuyList> bringList();
}
