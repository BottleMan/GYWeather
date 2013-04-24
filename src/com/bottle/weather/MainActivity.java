package com.bottle.weather;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn1;//now
	private Button btn2;//today
	private Button btn3;//5

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Now.class);
				MainActivity.this.startActivity(intent);
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Today.class);
				MainActivity.this.startActivity(intent);

			}
		});

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Recent.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		int group1 = 1;
		menu.add(group1, 1, 1, "Feed Back");
		menu.add(group1, 2, 2, "About");
		menu.add(group1, 3, 3, "Quit");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		//响应每个菜单项(通过菜单项的ID)
			case 1://Feed back
				showNotification();
				break;
			case 2://about
				Intent intent = new Intent(MainActivity.this, About.class);
				MainActivity.this.startActivity(intent);
				break;
			case 3://quit
				MainActivity.this.finish();
				break;
			default:
				//对没有处理的事件，交给父类来处理
				return super.onOptionsItemSelected(item);
		}
		//返回true表示处理完菜单项的事件，不需要将该事件继续传播下去了
		return true;
	}

	private void showNotification() {
		//获得NotificationManager实例
		String service = NOTIFICATION_SERVICE;
		NotificationManager notificationManager = (NotificationManager) getSystemService(service);
		//实例化Notification
		Notification notification = new Notification();
		//设置显示图标
		int icon = R.drawable.ic_launcher;
		//设置提示信息
		String tickerText = "Thank You For Your Feed Back";
		//显示时间
		long when = System.currentTimeMillis();

		notification.icon = icon;
		notification.tickerText = tickerText;
		notification.when = when;
		notification.flags |= Notification.FLAG_AUTO_CANCEL; //自动终止
		notification.defaults = Notification.DEFAULT_ALL;
		notification.setLatestEventInfo(getApplicationContext(), "故意天气", "Thank You", null);
		//发出通知
		notificationManager.notify(1, notification);
	}
}
