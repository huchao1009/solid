package com.huchao.solid.isp.right;

/**
 * @author double
 * @Date 2021/7/1 11:02 上午
 */
public interface RestrictedUserService {
    boolean deleteUserByCellphone(String cellphone);
    boolean deleteUserById(long id);
}
