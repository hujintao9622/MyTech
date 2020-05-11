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

    // 资讯
    // 轮播图
    String BASE_BANNER="techApi/information/v1/bannerShow";
    // 资讯展示列表
    String INFO_RECOMMEND="techApi/information/v1/infoRecommendList";
    // 资讯详情
    String INFORMATION_DETAILS = "techApi/information/v1/findInformationDetails";
    // 资讯评论
    String INFORMATION_COMMENT = "techApi/information/v1/findAllInfoCommentList";
    // 查询用户积分
    String USER_INTEGRAL = "techApi/user/verify/v1/findUserIntegral";
    // 用积分兑换资讯权限
    String PAY_INTEGRAL = "techApi/information/verify/v1/infoPayByIntegral";
    // 查询所有版块
    String ALL_INFO_PLATE = "techApi/information/v1/findAllInfoPlate";
    // 根据标题模糊查询
    String INFORMATION_BY_TITLE = "techApi/information/v1/findInformationByTitle";
    // 根据作者名模糊查询
    String INFORMATION_BY_SOURCE = "techApi/information/v1/findInformationBySource";
    // 资讯点赞
    String ADD_GREAT_RECORD = "techApi/information/verify/v1/addGreatRecord";
    // 取消点赞
    String CANCEL_GREAT = "techApi/information/verify/v1/cancelGreat";
    // 添加收藏
    String ADD_COLLECTION = "techApi/user/verify/v1/addCollection";
    // 取消收藏
    String CANCEL_COLLECTION = "techApi/user/verify/v1/cancelCollection";
    // 查询所有会员商品
    String FIND_VIP_LIST = "techApi/tool/v1/findVipCommodityList";

    //微信登录
    String BASE_WX_LOGIN="techApi/user/v1/weChatLogin";
    //下单
    String BASE_BUYORDER="techApi/tool/verify/v1/buyVip";
    //支付
    String BASE_PAY="techApi/tool/verify/v1/pay";

    //我的
    //根据ID查询信息
    String BASE_BYID="techApi/user/verify/v1/getUserInfoByUserId";
    // 用户收藏列表
    String ALL_INFO_COLLECTION = "techApi/user/verify/v1/findAllInfoCollection";
    // 根据用户ID查询用户信息
    String INFO_BY_USER_ID = "techApi/user/verify/v1/getUserInfoByUserId";
    // 我的帖子
    String MY_POST_LIST = "techApi/community/verify/v1/findMyPostById";
    // 查询用户积分
    String FIND_USER_INTEGRAL = "techApi/user/verify/v1/findUserIntegral";
    // 查询用户积分明细
    String FIND_USER_INTEGRAL_RECORD = "techApi/user/verify/v1/findUserIntegralRecord";
    // 我的任务列表
    String USER_TASK_LIST = "techApi/user/verify/v1/findUserTaskList";
    //删除帖子
    String DELETE_POST = "techApi/community/verify/v1/deletePost";

    //消息
    //查询好友通知
    String BASE_FRIEND_NOTICE="techApi/chat/verify/v1/findFriendNoticePageList";
    //查询分组
    String BASE_FIND_ALLGROUP="techApi/chat/verify/v1/findFriendGroupList";
    //查询分组下所有好友信息
    String BASE_FINDMAN_BYGROUP="techApi/chat/verify/v1/findFriendListByGroupId";
    //查询我的好友列表 get searchName
    String BASE_FIND_FRIEND="techApi/chat/verify/v1/searchFriend";
    //删除好友聊天记录
    String BASE_DELETE_FRIENDINFO="techApi/chat/verify/v1/deleteChatRecord";
    //根据手机号
    String BASE_SEUSER_BYPHONE="techApi/user/verify/v1/findUserByPhone";
    //查询好友信息
    String BASE_FRIENDINFO_ID="techApi/user/verify/v1/queryFriendInformation";
    //查询好友对话记录
    String BASE_CHAT="techApi/chat/verify/v1/findDialogueRecordPageList";
    //查询好友聊天记录
    String BASE_CHATHISTORY="techApi/chat/verify/v1/findChatRecordPageList";
    //发送消息
    String BASE_SEND_MSG="techApi/chat/verify/v1/sendMessage";
    //删除好友
    String BASE_DELETE_FRIEND="techApi/chat/verify/v1/deleteFriendRelation";
    //删除好友聊天记录
    String BASE_DELETE_HISTORY="techApi/chat/verify/v1/deleteChatRecord";
    //创建群聊
    String BASE_CREATE_GROUP="techApi/group/verify/v1/createGroup";
    //查询所有加入的群组
    String BASE_ALLGROUPS="techApi/group/verify/v1/findUserJoinedGroup";
    //查看群组聊天记录
    String BASE_GROUP_HISTORY="techApi/group/verify/v1/findGroupChatRecordPage";
    //查询群组详细信息
    String BASE_GROUP_DETAILS="techApi/group/verify/v1/findGroupInfo";
    //退出群组
    String BASE_BACK_GROUP="techApi/group/verify/v1/retreat";
    //解散群组
    String BASE_DELETE_GROUP="techApi/group/verify/v1/disbandGroup";
    //修改群简介
    String BASE_UPDATE_JIANJIE="techApi/group/verify/v1/modifyGroupDescription";
    //申请进群
    String BASE_ADD_GROUP="techApi/group/verify/v1/applyAddGroup";
    //群通知
    String BASE_GROUP_NOTICE="techApi/group/verify/v1/findGroupNoticePageList";
    //审核群申请
    String BASE_AUDIT="techApi/group/verify/v1/reviewGroupApply";
    //检测是否是我的好友
    String BASE_ISFRIEND="techApi/chat/verify/v1/checkMyFriend";
    //添加好友
    String BASE_ADD_FRIEND="techApi/chat/verify/v1/addFriend";
    //查询群组所有用户
    String BASE_QUERY_ALLUSERS="techApi/group/verify/v1/findGroupMemberList";
    //调整群成员角色
    String BASE_UPDATE_GROUP="techApi/group/verify/v1/modifyPermission";
    //移除群成员
    String BASE_DETELE_GROUPUSER="techApi/group/verify/v1/removeGroupMember";
    //邀请加群
    String BASE_INVITE_GROUP="techApi/group/verify/v1/inviteAddGroup";
    //是否在群内
    String BASE_IS_INGROUP="techApi/group/verify/v1/whetherInGroup";
    //审核好友
    String BASE_REVIEW_FRIEND="techApi/chat/verify/v1/reviewFriendApply";

    //社区
    //社区列表
    String BASE_COMMUNITYLIST="techApi/community/v1/findCommunityList";
    //点赞
    String BASE_COMMUNITY_ZAN="techApi/community/verify/v1/addCommunityGreat";
    //取消点赞
    String BASE_DELETE_ZAN="techApi/community/verify/v1/cancelCommunityGreat";
    //发表帖子
    String BASE_POST="techApi/community/verify/v1/releasePost";
    //社区用户评论列表
    String BASE_COMMUNI_PL="techApi/community/v1/findCommunityUserCommentList";
    //社区评论
    String BASE_FILM="techApi/community/verify/v1/addCommunityComment";
    //查询用户帖子
    String BASE_USER_COM="techApi/community/verify/v1/findUserPostById";

}
