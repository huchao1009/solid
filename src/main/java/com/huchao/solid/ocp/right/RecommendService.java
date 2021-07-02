package com.huchao.solid.ocp.right;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author double
 * @Date 2021/7/1 2:58 下午
 */
public class RecommendService {
    public static void main(String[] args) {
        new RecommendService().getRecommendList(0);
        new RecommendService().getRecommendList(1);
        new RecommendService().getRecommendList(2);
    }

    public List getRecommendList(int itemType) {
        RecommendStrategy recommendStrategy = RecommendStrategyFactory.getRecommendStrategy(itemType);
        return recommendStrategy.getRecommendList();
    }
}

interface RecommendStrategy {
    List getRecommendList();
}

class RestaurantRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询餐厅列表");
        return null;
    }
}

class ShoppingShopRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询购物商店列表");
        return null;
    }
}

class HikingRouteRecommendService implements RecommendStrategy {
    @Override
    public List getRecommendList() {
        System.out.println("查询徒步路线列表");
        return null;
    }
}

class RecommendStrategyFactory {
    private static final Map<Integer, RecommendStrategy> strategies = new HashMap<>();

    public static RecommendStrategy getRecommendStrategy(Integer entityType) {
        return strategies.get(entityType);
    }

    static {
        strategies.put(0, new RestaurantRecommendService());
        strategies.put(1, new ShoppingShopRecommendService());
        strategies.put(2, new HikingRouteRecommendService());
    }
}
