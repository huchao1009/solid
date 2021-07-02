package com.huchao.solid.srp.right;

/**
 * @author double
 * @Date 2021/7/2 2:49 下午
 */
public class UserInfo {
    private long userId;
    private String username;
    private String email;
    private String telephone;
    private long createTime;
    private long lastLoginTime;
    private String avatarUrl;

    private UserAddress userAddress;
    // ...省略其他属性和方法...
}

class UserAddress{
    private String provinceOfAddress; // 省
    private String cityOfAddress; // 市
    private String regionOfAddress; // 区
    private String detailedAddress; // 详细地址
    // ...省略其他属性和方法...
}