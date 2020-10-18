package sejun.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejun.shop.domain.BuyList;
import sejun.shop.repository.BuyListRepository;

import java.util.List;

@Service
public class BuyService {
    private final BuyListRepository buyListRepository;

    @Autowired
    public BuyService(BuyListRepository buyListRepository) {
        this.buyListRepository = buyListRepository;
    }

    public void save(BuyList buyList){
        buyListRepository.save(buyList);
    }

    public List<BuyList> bringList(){
        return buyListRepository.bringList();
    }


}
