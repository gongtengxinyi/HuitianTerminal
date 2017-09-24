package com.huitian.service;

import com.huitian.constants.ConstantsURL;
import com.huitian.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dingjianlei on 2017/9/21.
 */
public class HttpService {
    public static String checkNameAndPass(String username, String password) {
                 String data="";
        /**
         * 构造参数列表
         */
        try {
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put("username", username);
            paraMap.put("password", password);
            data = HttpClientUtil.doPost(ConstantsURL.URL, paraMap);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        } finally {
        }
        return data;
    }

    /**
     * 检查网络状态
     *
     * @return
     */
    public static boolean checkInternet() {
        boolean res = true;
        try {
            String data = HttpClientUtil.doGet(ConstantsURL.TEST_URL);
            if (StringUtils.isBlank(data) || data == null) {
                res = false;
            }
        } catch (Exception e) {
            res = false;
        } finally {
        }
        return res;
    }
}
