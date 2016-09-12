package com.kejiang.yuandl.mytimepick;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.wheelpickerdemo.StrericWheelAdapter;
import com.example.wheelpickerdemo.TimePickerPopupWindow;
import com.example.wheelpickerdemo.TimePickerPopupWindow.onTimeSelectListener;
import com.example.wheelpickerdemo.WheelView;


public class MainActivity2 extends Activity  implements onTimeSelectListener{


	private String[] timeData=new String[4];
	private TimePickerPopupWindow pickerPopupWindow;
	private Button btn;
	private AlertDialog alertDialog;
	private WheelView wheelView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTimeData();
//		pickerPopupWindow=new TimePickerPopupWindow(MainActivity.this, MainActivity.this, timeData);
//		btn=(Button) findViewById(R.id.btn);
		//initAlertDialog();
	}

	private void initTimeData(){
		timeData[0]="10分钟";
		timeData[1]="15分钟";
		timeData[2]="20分钟";
		timeData[3]="30分钟";
	}

	private void initAlertDialog(){
		View view = LayoutInflater.from(MainActivity2.this).inflate(
				R.layout.pop_menu, null);
		wheelView = (WheelView) view.findViewById(R.id.timeWheel);
		wheelView.setAdapter(new StrericWheelAdapter(timeData));
		wheelView.setCurrentItem(1);
		wheelView.setCyclic(true);
		wheelView.setInterpolator(new AnticipateOvershootInterpolator());

		view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(alertDialog!=null&&alertDialog.isShowing())
				{
					alertDialog.dismiss();
				}

			}
		});
      view.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(alertDialog!=null&&alertDialog.isShowing())
				{
					Toast.makeText(MainActivity2.this, "您选择了："+timeData[Integer.valueOf(wheelView.getCurrentItem())], Toast.LENGTH_LONG).show();
					alertDialog.dismiss();
				}

			}
		});

		alertDialog = new AlertDialog.Builder(MainActivity2.this)
		.create();
		alertDialog.show();

		LayoutParams params = alertDialog.getWindow()
				.getAttributes();
		params.width = MainActivity2.this.getWindowManager().getDefaultDisplay()
				.getWidth() ;
		int height=MainActivity2.this.getWindowManager().getDefaultDisplay()
				.getHeight();
		params.height=LayoutParams.WRAP_CONTENT;
		params.gravity=Gravity.BOTTOM;
		
		Window window = alertDialog.getWindow();
		window.setAttributes(params);
		window.setContentView(view);
		alertDialog.getWindow().setContentView(view);
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			initAlertDialog();
			break;

		default:
			break;
		}

	}

	@Override
	public void getTimeString(String timeString) {
		// TODO Auto-generated method stub
		
	}
}
