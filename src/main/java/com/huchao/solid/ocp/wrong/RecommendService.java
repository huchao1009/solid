package com.huchao.solid.ocp.wrong;

import java.util.List;

/**
 * @author double
 * @Date 2021/7/1 2:52 下午
 */
public class RecommendService {
    public static void main(String[] args) {
        new RecommendService().getRecommendList(0);
        new RecommendService().getRecommendList(1);
        new RecommendService().getRecommendList(2);
    }

    public List getRecommendList(int itemType) {
        if (itemType == 0) {
            return queryRestaurantList();
        } else if (itemType == 1) {
            return queryShoppingShopList();
        } else if (itemType == 2) {
            return queryHikingRouteList();
        }
        return null;
    }

    public List queryRestaurantList() {
        System.out.println("查询餐厅列表");
        return null;
    }

    public List queryShoppingShopList() {
        System.out.println("查询购物商店列表");
        return null;
    }

    public List queryHikingRouteList() {
        System.out.println("查询徒步路线列表");
        return null;
    }
}
