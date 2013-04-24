package com.bottle.weather;

import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bottle.weather.util.JsonParase;
import com.bottle.weather.util.MyHttp;

public class Recent extends Activity {

	private TextView date_today;
	private TextView date1;
	private TextView date2;
	private TextView date3;
	private TextView date4;
	private TextView date5;
	private TextView weather1;
	private TextView weather2;
	private TextView weather3;
	private TextView weather4;
	private TextView weather5;
	private TextView temp1;
	private TextView temp2;
	private TextView temp3;
	private TextView temp4;
	private TextView temp5;
	private TextView wd1;
	private TextView wd2;
	private TextView wd3;
	private TextView wd4;
	private TextView wd5;
	private TextView ws1;
	private TextView ws2;
	private TextView ws3;
	private TextView ws4;
	private TextView ws5;
	private HashMap<String, String> hashMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recent);

		hashMap = new HashMap<String, String>();
		bindView();

		MyAsync async = new MyAsync();
		async.execute("");
		
	}

	private void bindData() {
		date_today.setText(hashMap.get("date_today"));
		date1.setText(hashMap.get("date1"));
		date2.setText(hashMap.get("date2"));
		date3.setText(hashMap.get("date3"));
		date4.setText(hashMap.get("date4"));
		date5.setText(hashMap.get("date5"));
		weather1.setText(weather1.getText() + hashMap.get("weather1"));
		weather2.setText(weather2.getText() + hashMap.get("weather2"));
		weather3.setText(weather3.getText() + hashMap.get("weather3"));
		weather4.setText(weather4.getText() + hashMap.get("weather4"));
		weather5.setText(weather5.getText() + hashMap.get("weather5"));
		temp1.setText(temp1.getText() + hashMap.get("temp1"));
		temp2.setText(temp2.getText() + hashMap.get("temp2"));
		temp3.setText(temp3.getText() + hashMap.get("temp3"));
		temp4.setText(temp4.getText() + hashMap.get("temp4"));
		temp5.setText(temp5.getText() + hashMap.get("temp5"));
		wd1.setText(wd1.getText() + hashMap.get("wd1"));
		wd2.setText(wd2.getText() + hashMap.get("wd2"));
		wd3.setText(wd3.getText() + hashMap.get("wd3"));
		wd4.setText(wd4.getText() + hashMap.get("wd4"));
		wd5.setText(wd5.getText() + hashMap.get("wd5"));
		ws1.setText(ws1.getText() + hashMap.get("ws1"));
		ws2.setText(ws1.getText() + hashMap.get("ws2"));
		ws3.setText(ws1.getText() + hashMap.get("ws3"));
		ws4.setText(ws1.getText() + hashMap.get("ws4"));
		ws5.setText(ws1.getText() + hashMap.get("ws5"));
	}

	private class MyAsync extends AsyncTask<Object, Integer, Integer> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(Recent.this, "请稍等", "正在获取数据……", true, false);
		}

		@Override
		protected Integer doInBackground(Object... params) {
			return initial();
		}

		@Override
		protected void onPostExecute(Integer result) {
			//根据返回值显示相关的Toast
			switch (result) {
				case 1:
					Toast.makeText(Recent.this, "获取信息成功", Toast.LENGTH_SHORT).show();
					bindData();
					break;
				case 0:
					Toast.makeText(Recent.this, "获取信息失败", Toast.LENGTH_SHORT).show();
					break;
			}

			progressDialog.dismiss();
		}

	}

	private int initial() {
		MyHttp myHttp = new MyHttp();
		try {
			String url = "http://m.weather.com.cn/data/101010100.html";
			String retString = "";
			retString = myHttp.httpGet(url);

			if (!retString.equals("")) {
				JsonParase jsonParase = new JsonParase();

				hashMap = jsonParase.ParaseRecent(retString);

				return 1;
			} else {
				Toast.makeText(Recent.this, "获取信息错误", Toast.LENGTH_SHORT).show();
				return 0;
			}

		} catch (Exception e) {
			Log.i("http", "Recent: " + e.toString());
			return 0;
		}
	}

	private void bindView() {
		date_today = (TextView) findViewById(R.id.date_today);
		date1 = (TextView) findViewById(R.id.date1);
		date2 = (TextView) findViewById(R.id.date2);
		date3 = (TextView) findViewById(R.id.date3);
		date4 = (TextView) findViewById(R.id.date4);
		date5 = (TextView) findViewById(R.id.date5);
		weather1 = (TextView) findViewById(R.id.weather1);
		weather2 = (TextView) findViewById(R.id.weather2);
		weather3 = (TextView) findViewById(R.id.weather3);
		weather4 = (TextView) findViewById(R.id.weather4);
		weather5 = (TextView) findViewById(R.id.weather5);
		temp1 = (TextView) findViewById(R.id.temp1);
		temp2 = (TextView) findViewById(R.id.temp2);
		temp3 = (TextView) findViewById(R.id.temp3);
		temp4 = (TextView) findViewById(R.id.temp4);
		temp5 = (TextView) findViewById(R.id.temp5);
		wd1 = (TextView) findViewById(R.id.wd1);
		wd2 = (TextView) findViewById(R.id.wd2);
		wd3 = (TextView) findViewById(R.id.wd3);
		wd4 = (TextView) findViewById(R.id.wd4);
		wd5 = (TextView) findViewById(R.id.wd5);
		ws1 = (TextView) findViewById(R.id.fl1);
		ws2 = (TextView) findViewById(R.id.fl2);
		ws3 = (TextView) findViewById(R.id.fl3);
		ws4 = (TextView) findViewById(R.id.fl4);
		ws5 = (TextView) findViewById(R.id.fl5);
	}

}
