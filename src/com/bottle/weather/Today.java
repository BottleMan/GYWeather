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

public class Today extends Activity {
	private TextView tempTxt1;
	private TextView tempTxt2;
	private TextView weatherTxt;
	private TextView pTimeTxt;
	private HashMap<String, String> hashMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.today);
		hashMap = new HashMap<String, String>();
		
		weatherTxt = (TextView) findViewById(R.id.textView4);
		tempTxt1 = (TextView) findViewById(R.id.textView6);
		tempTxt2 = (TextView) findViewById(R.id.textView8);
		pTimeTxt = (TextView) findViewById(R.id.textView10);
		
		MyAsync async = new MyAsync();
		async.execute("");
	}

	private int initial() {
		MyHttp myHttp = new MyHttp();
		try
		{
			String url = "http://www.weather.com.cn/data/cityinfo/101010100.html";
			String retString = "";
			retString = myHttp.httpGet(url);
			
			if (!retString.equals("")) {
				JsonParase jsonParase = new JsonParase();
				
				hashMap = jsonParase.ParaseToday(retString);
				
				
				return 1;
			}
			else {
				Toast.makeText(Today.this, "获取信息错误", Toast.LENGTH_SHORT).show();
				return 0;
			}
			
		} catch (Exception e)
		{
			Log.i("http", "Today: " + e.toString());
			return 0;
		}
		
	}
	
	
	private class MyAsync extends AsyncTask<Object, Integer, Integer> {
		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(Today.this, "请稍等", "正在获取数据……", true, false);
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
					Toast.makeText(Today.this, "获取信息成功", Toast.LENGTH_SHORT).show();
					bindData();
					break;
				case 0:
					Toast.makeText(Today.this, "获取信息失败", Toast.LENGTH_SHORT).show();
					break;
			}

			progressDialog.dismiss();
		}
	}
	
	private void bindData() {
		weatherTxt.setText(hashMap.get("weather"));
		tempTxt1.setText(hashMap.get("temp1"));
		tempTxt2.setText(hashMap.get("temp2"));
		pTimeTxt.setText(hashMap.get("ptime"));
	}

}
