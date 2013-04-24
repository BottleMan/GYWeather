package com.bottle.weather.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class MyHttp
{

	public String httpGet(String url) throws Exception
	{
		String response = null; //返回信息

		//设置超时时间
		int timeoutConnection = 30000;
		int timeoutSocket = 50000;
//		int timeoutConnection = 3000;
//		int timeoutSocket = 5000;
		HttpParams httpParameters = new BasicHttpParams();// Set the timeout in milliseconds until a connection is established.  
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);// Set the default socket timeout (SO_TIMEOUT) 
																					// in milliseconds which is the timeout for waiting for data.  
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpConnectionParams.setTcpNoDelay(httpParameters, true);

		// 构造HttpClient的实例
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		// 创建GET方法的实例
		HttpGet httpGet = new HttpGet(url);
		try
		{
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) //SC_OK = 200，表示获取成功
			{
				// 获得返回结果
				response = EntityUtils.toString(httpResponse.getEntity());
			} else
			{
				//response = "返回码：" + statusCode;
				response = "";
			}
		} catch (Exception e)
		{
			throw new Exception(e);
		}
		return response;
	}
}
