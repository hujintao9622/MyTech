package com.wd.tech.view.activity.information;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.OrderBean;
import com.wd.tech.model.bean.information.PayBean;
import com.wd.tech.model.bean.information.PayResult;
import com.wd.tech.model.bean.information.VipListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.MD5Util;
import com.wd.tech.view.adapter.info.VipListAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_vip)
    RecyclerView rvVip;
    @BindView(R.id.tv_vip_list_price)
    TextView tvVipListPrice;
    @BindView(R.id.rbtn_select1)
    RadioButton rbtnSelect1;
    @BindView(R.id.rbtn_select2)
    RadioButton rbtnSelect2;
    @BindView(R.id.rg)
    RadioGroup rg;
    int payType = 1;
    @BindView(R.id.btn_pay)
    Button btnPay;
    int comId=-1;
    private SharedPreferences sp;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==3){
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus, "9000")) {
                    Toast.makeText(VipActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    // TODO: 2020/5/2 0002 成功弹框
                } else {
                    // 判断resultStatus 为非"9000"则代表可能支付失败
                    // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "8000")) {
                        Toast.makeText(VipActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                    } else {
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        Toast.makeText(VipActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }
    };
    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.FIND_VIP_LIST, VipListBean.class);
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            //判断是否支付成功
            boolean b = intent.getBooleanExtra("b", false);
            if (b){
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            }
        }
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_select1://微信支付
                        payType = 1;
                        break;
                    case R.id.rbtn_select2://支付宝支付
                        payType = 2;
                        break;
                }
            }
        });
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof VipListBean) {
            if (((VipListBean) o).getStatus().equals("0000")) {
                ArrayList<VipListBean.ResultBean> list = (ArrayList<VipListBean.ResultBean>) ((VipListBean) o).getResult();
                rvVip.setLayoutManager(new GridLayoutManager(this, 2));
                VipListAdapter vipListAdapter = new VipListAdapter(R.layout.item_vip_list, list);
                rvVip.setAdapter(vipListAdapter);
                vipListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        tvVipListPrice.setText(list.get(position).getPrice() + "");
                        comId=list.get(position).getCommodityId();
                    }
                });
            }
        }
        //下单
        if (o instanceof OrderBean&& TextUtils.equals("0000",((OrderBean) o).getStatus())){
            String orderId = ((OrderBean) o).getOrderId();
            HashMap<String,Object> map=new HashMap<>();
            map.put("orderId",orderId);
            map.put("payType",payType);
            mPresenter.postDoParams(MyUrls.BASE_PAY, PayBean.class,map);
        }
        if (o instanceof PayBean&&TextUtils.equals("0000",((PayBean) o).getStatus())){
            if (payType==1){//微信支付
                String appId = ((PayBean) o).getAppId();                //应用id
                String nonceStr = ((PayBean) o).getNonceStr();          //随机字符串
                String packageValue = ((PayBean) o).getPackageValue();  //扩展字段
                String partnerId = ((PayBean) o).getPartnerId();        //微信支付商户号
                String prepayId = ((PayBean) o).getPrepayId();          //支付凭证
                String sign = ((PayBean) o).getSign();                  //签名
                String timeStamp = ((PayBean) o).getTimeStamp();        //时间戳
                if (MyApp.mWxApi.isWXAppInstalled()) {//判断用户是否安装过微信
                    //注册应用
                    MyApp.mWxApi.registerApp(appId);
                    PayReq payReq = new PayReq();
                    payReq.appId=appId;
                    Log.e("appId",appId);
                    payReq.nonceStr=nonceStr;
                    payReq.packageValue=packageValue;
                    payReq.partnerId=partnerId;
                    payReq.prepayId=prepayId;
                    payReq.sign=sign;
                    payReq.timeStamp=""+timeStamp;
                    //发起请求
                    MyApp.mWxApi.sendReq(payReq);
                }else {
                    Toast.makeText(this, "请先安装微信!", Toast.LENGTH_SHORT).show();
                }
            }else if (payType==2){//支付宝支付
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = ((PayBean) o).getResult();
                        //创建payTask对象
                        PayTask payTask = new PayTask(VipActivity.this);
                        //调用支付接口
                        Map<String, String> map = payTask.payV2(result, true);
                        Message message = Message.obtain();
                        message.what=3;
                        message.obj=map;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        String mess=null;
         if(payType==1){
            mess="确认使用微信支付";
        }else if (payType==2){
             mess="确认使用支付宝支付";
         }
        if (comId==-1){
            Toast.makeText(VipActivity.this, "请选择会员时间", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(VipActivity.this);
        builder.setTitle("请确认支付");
        builder.setMessage(mess);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下单
                HashMap<String,Object>map= new HashMap<>();
                String commId = String.valueOf(comId);
                boolean b = sp.getBoolean("b", false);
                if (b){

                    int userId = sp.getInt("userId", -1);
                    String s=userId+commId+"tech";
                    String md5 = MD5Util.MD5(s);
                    map.put("commodityId",comId);
                    map.put("sign",md5);
                    mPresenter.postDoParams(MyUrls.BASE_BUYORDER, OrderBean.class,map);
                }else {
                    Toast.makeText(VipActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
