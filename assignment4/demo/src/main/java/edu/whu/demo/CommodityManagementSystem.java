package edu.whu.demo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommodityManagementSystem {
    private Map<Long, commodity> commodities=
            Collections.synchronizedMap(new HashMap<Long,commodity>());
    public commodity addCommodity(commodity myCommodity){
        commodities.put(myCommodity.getId(),myCommodity);
        return myCommodity;
    }
    public void removeCommodity(long id){
        commodities.remove(id);
    }
    public void updateCommodity(long id,commodity myCommodity){
        commodity newCommodity=commodities.get(id);
        newCommodity.setName(myCommodity.name);
        newCommodity.setNum(myCommodity.num);
        newCommodity.setPrice(myCommodity.price);
        commodities.put(id,newCommodity);
    }
    public commodity getCommodity(long id){
        return commodities.get(id);
    }
    public List<commodity> findEmpty(){
        List<commodity> result = new ArrayList<commodity>();
        for(commodity nowCommodity:commodities.values()){
            if(nowCommodity.num==0) result.add(nowCommodity);
        }
        return result;
    }
}
