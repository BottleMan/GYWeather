package com.bottle.weather.util;

import java.util.HashMap;

import org.json.JSONObject;

import android.util.Log;

public class JsonParase {
	public JsonParase() {

	}

	public HashMap<String, String> ParaseNow(String jsonStr) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			JSONObject object = jsonObject.getJSONObject("weatherinfo");
			hashMap.put("temp", object.getString("temp"));
			hashMap.put("WD", object.getString("WD"));
			hashMap.put("WS", object.getString("WS"));
			hashMap.put("time", object.getString("time"));
		} catch (Exception e) {
			Log.i("http", "Parase now " + e.toString());
			return null;
		}

		return hashMap;
	}
	
	public HashMap<String, String> ParaseToday(String jsonStr) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			JSONObject object = jsonObject.getJSONObject("weatherinfo");
			hashMap.put("temp1", object.getString("temp1"));
			hashMap.put("temp2", object.getString("temp2"));
			hashMap.put("weather", object.getString("weather"));
			hashMap.put("ptime", object.getString("ptime"));
		} catch (Exception e) {
			Log.i("http", "Parase today " + e.toString());
			return null;
		}

		return hashMap;
	}
	
	public HashMap<String, String> ParaseRecent(String jsonStr) {
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			JSONObject object = jsonObject.getJSONObject("weatherinfo");
			hashMap.put("date_today", object.getString("date_y") + " " + object.getString("week"));
			hashMap.put("date1", DateConfig.getDate(1));
			hashMap.put("date2", DateConfig.getDate(2));
			hashMap.put("date3", DateConfig.getDate(3));
			hashMap.put("date4", DateConfig.getDate(4));
			hashMap.put("date5", DateConfig.getDate(5));
			hashMap.put("temp1", object.getString("temp2"));
			hashMap.put("temp2", object.getString("temp3"));
			hashMap.put("temp3", object.getString("temp4"));
			hashMap.put("temp4", object.getString("temp5"));
			hashMap.put("temp5", object.getString("temp6"));
			hashMap.put("weather1", object.getString("weather2"));
			hashMap.put("weather2", object.getString("weather3"));
			hashMap.put("weather3", object.getString("weather4"));
			hashMap.put("weather4", object.getString("weather5"));
			hashMap.put("weather5", object.getString("weather6"));
			hashMap.put("wd1", object.getString("wind2"));
			hashMap.put("wd2", object.getString("wind3"));
			hashMap.put("wd3", object.getString("wind4"));
			hashMap.put("wd4", object.getString("wind5"));
			hashMap.put("wd5", object.getString("wind6"));
			hashMap.put("ws1", object.getString("fl2"));
			hashMap.put("ws2", object.getString("fl3"));
			hashMap.put("ws3", object.getString("fl4"));
			hashMap.put("ws4", object.getString("fl5"));
			hashMap.put("ws5", object.getString("fl6"));
			
			
		} catch (Exception e) {
			Log.i("http", "Parase today " + e.toString());
			return null;
		}
		
		return hashMap;
	}
}
