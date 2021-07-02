package com.huchao.solid.isp.wrong;

import com.huchao.solid.isp.UserInfo;

/**
 * @author double
 * @Date 2021/7/1 11:01 上午
 */
public interface UserService {
    boolean register(String cellphone, String password);

    boolean login(String cellphone, String password);

    UserInfo getUserInfoById(long id);

    UserInfo getUserInfoByCellphone(String cellphone);

    boolean deleteUserByCellphone(String cellphone);

    boolean deleteUserById(long id);
}
