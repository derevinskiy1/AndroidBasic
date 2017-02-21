package com.example.storm.dialog.PopupWindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.storm.dialog.CustomeProgressDialog;
import com.example.storm.dialog.R;

public class MainActivity extends Activity implements OnClickListener{
	private Button setButton;
	private Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);
		
		setButton = (Button) findViewById(R.id.btnSet);
		addButton = (Button) findViewById(R.id.btnAdd);
		setButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
		CustomeProgressDialog dialog=new CustomeProgressDialog(this,"",0);
		dialog.show();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSet:
			MorePopWindow morePopWindow = new MorePopWindow(MainActivity.this);
			morePopWindow.showPopupWindow(setButton);
			break;
		case R.id.btnSearch:
			
			break;
		case R.id.btnAdd:
			AddPopWindow addPopWindow = new AddPopWindow(MainActivity.this);
			addPopWindow.showPopupWindow(addButton);
			break;
		default:
			break;
		}
	}
}
