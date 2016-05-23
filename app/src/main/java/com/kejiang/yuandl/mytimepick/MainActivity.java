package com.kejiang.yuandl.mytimepick;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.kejiang.yuandl.mytimepick.timepick.MyTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView tv_chose;
    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_chose = (TextView) findViewById(R.id.tv_chose);
//        Rotate3d rotate = new Rotate3d();
//        rotate.setDuration(3000);
//        tv.measure(0, 0);
//        rotate.setCenter(tv.getMeasuredWidth() / 2, tv.getMeasuredHeight() / 2);
//        rotate.setFillAfter(true); // 使动画结束后定在最终画面，如果不设置为true，则将会回到初始画面
//        tv_chose.startAnimation(rotate);
//        applyRotation(0, 90);
        tv_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoseTimeDialog();
            }
        });
    }

    private void showChoseTimeDialog() {
        final ArrayList<String> minutes = get64minutes();
        final ArrayList<String> hours = get24hours();
        final ArrayList<String> dates = get7Date();

        MyTimePicker myTimePicker = new MyTimePicker(this, dates, hours, minutes);
        myTimePicker.setSelectedItem(0, 0, 0);
        myTimePicker.setTitleText("选择时间");
        myTimePicker.setTitleTextSize(16);

        myTimePicker.setTopBackgroundColor(Color.parseColor("#FFEAEAEB"));
        myTimePicker.setTextSize(21);

        myTimePicker.setCancelText("取消");
        myTimePicker.setSubmitText("完成");
        myTimePicker.setSubmitTextColor(Color.parseColor("#F77B55"));
        myTimePicker.setLineColor(Color.parseColor("#FFEAEAEB"));
        myTimePicker.setTextColor(Color.parseColor("#000000"));
        myTimePicker.show();
        myTimePicker.setOnAddressPickListener(new MyTimePicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(String province, String city, String county) {
                System.out.println("province=" + province);
                System.out.println("city=" + city);
                System.out.println("county=" + county);
            }
        });
    }

    /**
     * 获取从今天后的7天
     *
     * @return
     */
    private ArrayList<String> get7Date() {
        ArrayList<String> dates = new ArrayList<String>();
        dates.add("现在");
        for (int i = 0; i < 7; i++) {
            dates.add(getStatetime(i));
        }
        return dates;
    }

    /**
     * 获取24小时
     *
     * @return
     */
    private ArrayList<String> get24hours() {
        ArrayList<String> dates = new ArrayList<String>();
        dates.add("--");
        for (int i = 0; i < 24; i++) {

            dates.add((i < 10 ? "0" + i : i) + "时");
        }
        return dates;
    }

    /**
     * 获取60分
     *
     * @return
     */
    private ArrayList<String> get64minutes() {
        int curentMiniutis = 0;
        ArrayList<String> dates = new ArrayList<String>();
        dates.add("--");
        while(curentMiniutis<60){
            dates.add((curentMiniutis<10?("0"+curentMiniutis):curentMiniutis)+"分");
            curentMiniutis+=5;
        }
        return dates;
    }

    private String getStatetime(int i) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }
    /*****************************以下动画部分****/
    private void applyRotation(float start, float end) {
        // 计算中心点
        final float centerX = tv_chose.getWidth() / 2.0f;
        final float centerY = tv_chose.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
                centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        // 设置监听
        rotation.setAnimationListener(new DisplayNextView());

        tv_chose.startAnimation(rotation);
    }

    private final class DisplayNextView implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {
        }

        // 动画结束
        public void onAnimationEnd(Animation animation) {
            tv_chose.post(new SwapViews());
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    private final class SwapViews implements Runnable {

        public void run() {
            final float centerX = tv_chose.getWidth() / 2.0f;
            final float centerY = tv_chose.getHeight() / 2.0f;
            Rotate3dAnimation rotation = null;

            tv_chose.requestFocus();

            rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f,
                    false);
            rotation.setDuration(1000);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            // 开始动画
            tv_chose.startAnimation(rotation);
//            tv_chose.setText(String.valueOf(count++));
        }
    }



}
