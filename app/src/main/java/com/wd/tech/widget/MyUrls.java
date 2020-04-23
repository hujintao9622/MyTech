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
    //注册 post nickName phone pwd
    String BASE_REGISTER="techApi/user/v1/register";
    // 轮播图
    String BASE_BANNER="techApi/information/v1/bannerShow";
    // 资讯展示列表
    String INFO_RECOMMEND="techApi/information/v1/infoRecommendList";

    //我的
    //根据ID查询信息
    String BASE_BYID="techApi/user/verify/v1/getUserInfoByUserId";

    //消息
    //查询好友通知
    String BASE_FRIEND_NOTICE="techApi/chat/verify/v1/findFriendNoticePageList";
}
