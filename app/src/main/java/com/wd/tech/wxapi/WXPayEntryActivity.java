package com.wd.tech.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.view.activity.information.VipActivity;
import com.wd.tech.widget.MyApp;

/**
 * date:2020/5/2 0002
 * author:胡锦涛(Administrator)
 * function:微信支付
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_w_x_pay_entry);
        //没有这句可能不会调用onResp
        MyApp.mWxApi.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            if (resp.errCode==0){
                //跳转VIP
                Intent intent = new Intent(WXPayEntryActivity.this, VipActivity.class);
                intent.putExtra("b",true);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                //跳转VIP
                Intent intent = new Intent(WXPayEntryActivity.this, VipActivity.class);
                intent.putExtra("b",false);
                startActivity(intent);
                finish();
            }
        }
    }
}
