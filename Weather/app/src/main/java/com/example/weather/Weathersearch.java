package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.*;


public class Weathersearch extends AppCompatActivity{

    public   String Api_url="http://apis.juhe.cn/simpleWeather/query";
    public  String Api_key="5cc4baad069e5f67eed896591863fb85";
    public   String cityname;
    public Weatherdata weatherdata=new Weatherdata();
    private final Context context1=Weathersearch.this;

    @SuppressLint("HandlerLeak")
    private final Handler mhandler =new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String response=msg.obj.toString();
                json(response);
                ImageView image_real= findViewById(R.id.image_real);
                ImageView image_future1=(ImageView)findViewById(R.id.image_future1);
                ImageView image_future2=(ImageView)findViewById(R.id.image_future2);
                ImageView image_future3=(ImageView)findViewById(R.id.image_future3);
                ImageView image_future4=(ImageView)findViewById(R.id.image_future4);
                ImageView image_future5=(ImageView)findViewById(R.id.image_future5);
                TextView text_real=(TextView)findViewById(R.id.text_real);
                TextView text_rtemperature=(TextView)findViewById(R.id.text_rtemperature);
                TextView text_rhumidity=(TextView)findViewById(R.id.text_rhumidity);
                TextView text_rdirect=(TextView)findViewById(R.id.text_rdirect);
                TextView text_rpower=(TextView)findViewById(R.id.text_rpower);
                TextView text_raqi=(TextView)findViewById(R.id.text_raqi);
                TextView text_weather1=(TextView)findViewById(R.id.text_weather1);
                TextView text_weather2=(TextView)findViewById(R.id.text_weather2);
                TextView text_weather3=(TextView)findViewById(R.id.text_weather3);
                TextView text_weather4=(TextView)findViewById(R.id.text_weather4);
                TextView text_weather5=(TextView)findViewById(R.id.text_weather5);
                TextView text_temperature1=(TextView)findViewById(R.id.text_temperature1);
                TextView text_temperature2=(TextView)findViewById(R.id.text_temperature2);
                TextView text_temperature3=(TextView)findViewById(R.id.text_temperature3);
                TextView text_temperature4=(TextView)findViewById(R.id.text_temperature4);
                TextView text_temperature5=(TextView)findViewById(R.id.text_temperature5);
                TextView text_date1=(TextView)findViewById(R.id.text_date1);
                TextView text_direct1=(TextView)findViewById(R.id.text_direct1);
                TextView text_date2=(TextView)findViewById(R.id.text_date2);
                TextView text_direct2=(TextView)findViewById(R.id.text_direct2);
                TextView text_date3=(TextView)findViewById(R.id.text_date3);
                TextView text_direct3=(TextView)findViewById(R.id.text_direct3);
                TextView text_date4=(TextView)findViewById(R.id.text_date4);
                TextView text_direct4=(TextView)findViewById(R.id.text_direct4);
                TextView text_date5=(TextView)findViewById(R.id.text_date5);
                TextView text_direct5=(TextView)findViewById(R.id.text_direct5);
                TextView city_name=(TextView)findViewById(R.id.city_name);
                TextView connect=(TextView)findViewById(R.id.connect);
                TextView move =(TextView)findViewById(R.id.move);
                connect.setMovementMethod(LinkMovementMethod.getInstance());
                text_real.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_rtemperature.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_rhumidity.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_rdirect.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_rpower.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_raqi.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_weather1.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_weather2.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_weather3.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_weather4.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_weather5.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_date1.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_date2.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_date3.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_date4.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_date5.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_temperature1.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_temperature2.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_temperature3.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_temperature4.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_temperature5.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_direct1.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_direct2.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_direct3.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_direct4.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                text_direct5.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                city_name.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
                image_real.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getRwid(),"drawable", context1.getPackageName()));
                text_real.setText(weatherdata.getRinfo());
                text_raqi.setText(weatherdata.getRaqi());
                text_rdirect.setText(weatherdata.getRdirect());
                text_rhumidity.setText(weatherdata.getRhumidity());
                text_rtemperature.setText(weatherdata.getRtemperature());
                text_rpower.setText(weatherdata.getRpower());
                text_weather1.setText(weatherdata.getWeather1());
                text_weather2.setText(weatherdata.getWeather2());
                text_weather3.setText(weatherdata.getWeather3());
                text_weather4.setText(weatherdata.getWeather4());
                text_weather5.setText(weatherdata.getWeather5());
                text_temperature1.setText(weatherdata.getTemperature1());
                text_temperature2.setText(weatherdata.getTemperature2());
                text_temperature3.setText(weatherdata.getTemperature3());
                text_temperature4.setText(weatherdata.getTemperature4());
                text_temperature5.setText(weatherdata.getTemperature5());
                image_future1.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getWid1(),"drawable", context1.getPackageName()));
                image_future2.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getWid2(),"drawable", context1.getPackageName()));
                image_future3.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getWid3(),"drawable", context1.getPackageName()));
                image_future4.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getWid4(),"drawable", context1.getPackageName()));
                image_future5.setImageResource(context1.getResources().getIdentifier("d"+weatherdata.getWid5(),"drawable", context1.getPackageName()));
                text_date1.setText(weatherdata.getDate1());
                text_date2.setText(weatherdata.getDate2());
                text_date3.setText(weatherdata.getDate3());
                text_date4.setText(weatherdata.getDate4());
                text_date5.setText(weatherdata.getDate5());
                text_direct1.setText(weatherdata.getDirect1());
                text_direct2.setText(weatherdata.getDirect2());
                text_direct3.setText(weatherdata.getDirect3());
                text_direct4.setText(weatherdata.getDirect4());
                text_direct5.setText(weatherdata.getDirect5());
                city_name.setText(weatherdata.getCity());
                move.setVisibility(View.GONE);
            }
        }
    };

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View current = getLayoutInflater().inflate(R.layout.weather_item, null);
        setContentView(R.layout.weather_item);
        Intent intent =getIntent();
        cityname =intent.getStringExtra("cityname");
        Map<String, Object> params = new HashMap<>();//组合参数
        params.put("city", cityname);
        params.put("key", Api_key);
        String queryParams=urlencode(params);
        getdata(queryParams);
    }



    public void getdata(String queryParams ){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                BufferedReader bufferedReader = null;
                String result = null;// 返回结果字符串
                try {
                    // 创建远程url连接对象
                    URL url = new URL(new StringBuffer(Api_url).append("?").append(queryParams).toString());
                    // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
                    connection = (HttpURLConnection) url.openConnection();
                    // 设置连接方式：get
                    connection.setRequestMethod("GET");
                    // 设置连接主机服务器的超时时间：15000毫秒
                    connection.setConnectTimeout(5000);
                    // 设置读取远程返回的数据时间：60000毫秒
                    connection.setReadTimeout(6000);
                    // 发送请求
                    connection.connect();
                    // 通过connection连接，获取输入流
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();
                        // 封装输入流，并指定字符集
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        // 存放数据
                        StringBuilder sbf = new StringBuilder();
                        String temp;
                        while ((temp = bufferedReader.readLine()) != null) {
                            sbf.append(temp);
                            sbf.append(System.getProperty("line.separator"));
                        }
                        result = sbf.toString();
                        Message msg=mhandler.obtainMessage(1);
                        msg.obj=result;
                        mhandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 关闭资源
                    if (null != bufferedReader) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null != inputStream) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();// 关闭远程连接
                    }
                }
            }
        }).start();
    }


    private void json(String data){
        try{
            JSONObject jsonObject=new JSONObject(data);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject realtime = result.getJSONObject("realtime");
            JSONArray future = result.getJSONArray("future");
            weatherdata.setCity(result.getString("city"));
            weatherdata.setRtemperature(realtime.getString("temperature"));
            weatherdata.setRhumidity(realtime.getString("humidity"));
            weatherdata.setRinfo(realtime.getString("info"));
            weatherdata.setRwid(realtime.getString("wid"));
            weatherdata.setRdirect(realtime.getString("direct"));
            weatherdata.setRpower(realtime.getString("power"));
            weatherdata.setRaqi(realtime.getString("aqi"));
            for(int i=0;i<future.length();i++){
                JSONObject day = future.getJSONObject(i);
                if(i==0){
                    weatherdata.setDate1(day.getString("date"));
                    weatherdata.setTemperature1(day.getString("temperature"));
                    weatherdata.setWeather1(day.getString("weather"));
                    weatherdata.setDirect1(day.getString("direct"));
                    weatherdata.setWid1(day.getJSONObject("wid").getString("day"));
                }else if(i==1){
                    weatherdata.setDate2(day.getString("date"));
                    weatherdata.setTemperature2(day.getString("temperature"));
                    weatherdata.setWeather2(day.getString("weather"));
                    weatherdata.setDirect2(day.getString("direct"));
                    weatherdata.setWid2(day.getJSONObject("wid").getString("day"));
                }else if(i==2){
                    weatherdata.setDate3(day.getString("date"));
                    weatherdata.setTemperature3(day.getString("temperature"));
                    weatherdata.setWeather3(day.getString("weather"));
                    weatherdata.setDirect3(day.getString("direct"));
                    weatherdata.setWid3(day.getJSONObject("wid").getString("day"));
                }else if(i==3){
                    weatherdata.setDate4(day.getString("date"));
                    weatherdata.setTemperature4(day.getString("temperature"));
                    weatherdata.setWeather4(day.getString("weather"));
                    weatherdata.setDirect4(day.getString("direct"));
                    weatherdata.setWid4(day.getJSONObject("wid").getString("day"));
                }else{
                    weatherdata.setDate5(day.getString("date"));
                    weatherdata.setTemperature5(day.getString("temperature"));
                    weatherdata.setWeather5(day.getString("weather"));
                    weatherdata.setDirect5(day.getString("direct"));
                    weatherdata.setWid5(day.getJSONObject("wid").getString("day"));
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
                }
    }

    public static String urlencode(Map<String, ?> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String result = sb.toString();
        result = result.substring(0, result.lastIndexOf("&"));
        return result;
    }
}