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
    //微信登录
    String BASE_WX_LOGIN="techApi/user/v1/weChatLogin";

    //我的
    //根据ID查询信息
    String BASE_BYID="techApi/user/verify/v1/getUserInfoByUserId";

    //消息
    //查询好友通知
    String BASE_FRIEND_NOTICE="techApi/chat/verify/v1/findFriendNoticePageList";
    //查询分组
    String BASE_FIND_ALLGROUP="techApi/chat/verify/v1/findFriendGroupList";
    //查询分组下所有好友信息
    String BASE_FINDMAN_BYGROUP="techApi/chat/verify/v1/findFriendListByGroupId";
    //查询我的好友列表 get searchName
    String BASE_FIND_FRIEND="techApi/chat/verify/v1/searchFriend";

}
