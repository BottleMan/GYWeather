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

public class Now extends Activity {

	private TextView temperTxt;
	private TextView windOriTxt;
	private TextView windSpeedTxt;
	private TextView timeTxt;
	private HashMap<String, String> hashMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.now);
		
		hashMap = new HashMap<String, String>();
		
		temperTxt = (TextView) findViewById(R.id.textView4);
		windOriTxt = (TextView) findViewById(R.id.textView6);
		windSpeedTxt = (TextView) findViewById(R.id.textView8);
		timeTxt = (TextView) findViewById(R.id.textView10);
		
		MyAsync async = new MyAsync();
		async.execute("");
	}
	
	private class MyAsync extends AsyncTask<Object, Integer, Integer> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(Now.this, "请稍等", "正在获取数据……", true, false);
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
					Toast.makeText(Now.this, "获取信息成功", Toast.LENGTH_SHORT).show();
					bindData();
					break;
				case 0:
					Toast.makeText(Now.this, "获取信息失败", Toast.LENGTH_SHORT).show();
					break;
			}

			progressDialog.dismiss();
		}
	}
	
	private void bindData() {
		temperTxt.setText(hashMap.get("temp") + "℃");
		windOriTxt.setText(hashMap.get("WD"));
		windSpeedTxt.setText(hashMap.get("WS"));
		timeTxt.setText(hashMap.get("time"));
	}
	
	private int initial() {
		MyHttp myHttp = new MyHttp();
		try
		{
			String url = "http://www.weather.com.cn/data/sk/101010100.html";
			String retString = "";
			retString = myHttp.httpGet(url);
			
			if (!retString.equals("")) {
				JsonParase jsonParase = new JsonParase();
				
				hashMap = jsonParase.ParaseNow(retString);
				
				
				return 1;
			}
			else {
				Toast.makeText(Now.this, "获取信息错误", Toast.LENGTH_SHORT).show();
				return 0;
			}
			
		} catch (Exception e)
		{
			Log.i("http", "Now: " + e.toString());
			return 0;
		}
	}
	
}
