package com.wd.tech.widget;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:
 */
public interface MyUrls {
    //公共网址

    boolean isDebug=true;
    String BASE_WRAP_URL = "https://172.17.8.100/";
    String BASE_URL="https://mobile.bwstudent.com/";
    static String baseUrl(){
        return isDebug==true?BASE_URL:BASE_WRAP_URL;
    }
    String url=baseUrl();
    //登录 post phone pwd
    String BASE_LOGIN="techApi/user/v1/login";

}
