package com.wd.tech.view.activity.information;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.DoTaskBean;
import com.wd.tech.model.bean.my.FindSingRecordingBean;
import com.wd.tech.model.bean.my.FindUserSingBean;
import com.wd.tech.mydatapicker.DPCManager;
import com.wd.tech.mydatapicker.DPDecor;
import com.wd.tech.mydatapicker.DatePicker;
import com.wd.tech.mydatapicker.DatePicker2;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.widget.MyUrls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 签到
public class SignActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.data_bt)
    Button dataBt;
    @BindView(R.id.data_dp)
    DatePicker2 dataDp;
    String result = "";//日期
    private ArrayList<String> result1  = new ArrayList<>();
    private ArrayList<String> result2;

    @Override
    protected void initData() {
        // 查询当月签到日期
        mPresenter.getNoParams(MyUrls.FIND_RECORDING, FindSingRecordingBean.class);
        dataDp.setDate(2020, 5);
        dataDp.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.RED);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2F, paint);
            }
        });

        dataDp.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(List<String> date) {
                Iterator iterator = date.iterator();
                while (iterator.hasNext()) {
                    result += iterator.next();
                    if (iterator.hasNext()) {
                        result += "\n";
                    }
                }
                Toast.makeText(SignActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
        //当月签到的所有日期
        // Intent intent = getIntent();
            /*Serializable tmd1 = intent.getSerializableExtra("tmd");
            result1 = (ArrayList<String>) tmd1;*/



        //查询当天签到的状态
        mPresenter.getNoParams(MyUrls.FIND_USER_SIGN, FindUserSingBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FindSingRecordingBean && TextUtils.equals("0000",((FindSingRecordingBean) o).getStatus())){
            result2 = (ArrayList<String>) ((FindSingRecordingBean) o).getResult();
            Log.e("TAG", "5月14日：" + result2 );
            for (int i = 0; i < result2.size(); i++) {
                String a = "";
                String s = result2.get(i);
                String[] split = s.split("-");
                for (int j = 0; j < split.length; j++) {
                    int i1 = Integer.parseInt(split[j]);
                    String s1 = String.valueOf(i1);
                    a+=s1+"-";
                }
                String substring = a.substring(0, a.length() - 1);
                result2.set(i,substring);
            }
            DPCManager.getInstance().setDecorBG(result2);
            /*Intent intent = new Intent(this,SignActivity.class);
            intent.putExtra("tmd",(Serializable) result);
            startActivity(intent);*/
        }
        if (o instanceof DoTaskBean){
            if(TextUtils.equals("0000",((DoTaskBean) o).getStatus())){
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                HashMap<String, Object> map = new HashMap<>();
                map.put("taskId",1001);
                mPresenter.postDoParams(MyUrls.DO_TASK, DoTaskBean.class,map);
            }else {
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (o instanceof FindUserSingBean && TextUtils.equals("0000",((FindUserSingBean) o).getStatus())){
            if (((FindUserSingBean) o).getResult()==1){
                dataBt.setText("已签到");
            }else {
                dataBt.setText("未签到");
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.data_bt)
    public void onViewClicked() {
        dataBt.setText("已签到");
        mPresenter.postNoParams(MyUrls.USER_SIGN, DoTaskBean.class);
    }
}
