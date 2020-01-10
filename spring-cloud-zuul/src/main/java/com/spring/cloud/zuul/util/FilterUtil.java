package com.spring.cloud.zuul.util;

import java.util.List;

/**
 * 白名单设置
 * @user zds
 * @date 2020年1月10日下午6:23:09
 **/
public class FilterUtil {
    static String[] strRegexPath = new String[]{
            ".*(/webjars*).*",
            ".*(/swagger-ui*).*",
            ".*(/swagger-resources*).*",
            ".*(/v2*).*",
            ".*(/user).*"
    };

    public static void initRegexPath(List<String> lstring){

    }

    /**
     * 判定此url是否可以通过过滤器（如：swagger、ksh接口是可以无状态的）
     * @param url
     * @return 通过返回true，不通过返回false
     */
    public static boolean isValidUrl(String url){
        for(String _path : strRegexPath){
            if (url.matches(_path)){
                return true;
            }
        }
        return false;
    }
}