package com.example.storm.lottery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends Activity {
	
	LotteryView nl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.act_home);
		
		nl=(LotteryView) findViewById(R.id.nl);
		
		int[]prizesIcon={R.drawable.danfan,
				R.drawable.meizi,R.drawable.iphone,
				R.drawable.f015,R.drawable.arrow,
				R.drawable.f040,R.drawable.ipad,
				R.drawable.spree_icon,
				R.drawable.spree_success_icon};
		final List<Prize>prizes=new ArrayList<>();
		for(int x=0;x<9;x++){
			Prize lottery=new Prize();
			lottery.setId(x+1);
			lottery.setName("Lottery"+(x+1));
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), prizesIcon[x]);
			lottery.setIcon(bitmap);
			if((x+1)%2==0){
				lottery.setBgColor(0xff4fccee);
			}else if(x==4){
				lottery.setBgColor(0xffffffff);
			}else{
				lottery.setBgColor(0xff00ff34);
			}
			
			prizes.add(lottery);
		}
		nl.setPrizes(prizes);
		nl.setOnTransferWinningListener(new LotteryView.OnTransferWinningListener() {
			
			@Override
			public void onWinning(int position) {
				Toast.makeText(getApplicationContext(), prizes.get(position).getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
