package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import adapter.CityListAdatper;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private Context context = MainActivity.this;
    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1=(EditText) findViewById(R.id.edit1);
        TextView text1 =findViewById(R.id.text1);
        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/word.ttf"));
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Weathersearch.class);
                intent.putExtra("cityname",edit1.getText().toString());
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Url="http://apis.juhe.cn/simpleWeather/cityList";
                final String Key="5cc4baad069e5f67eed896591863fb85";
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        InputStream inputStream = null;
                        BufferedReader bufferedReader = null;
                        String result = null;// 返回结果字符串
                        try{
                            // 创建远程url连接对象
                            URL url = new URL(new StringBuffer(Url).append("?key=").append(Key).toString());
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
                                Message msg=handler.obtainMessage(1);
                                msg.obj=result;
                                handler.sendMessage(msg);
                            }else{
                                Message msg=handler.obtainMessage(0);
                                msg.obj=connection.getResponseMessage();
                                handler.sendMessage(msg);
                            }

                            connection.disconnect();
                        }catch (Exception e){
                            Log.e("liu","error");
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
        });
    }

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what==1){
                try{
                    Log.e("liu","response success"+msg.obj.toString());
                    JSONObject jsonObject = new JSONObject(msg.obj.toString());
                    JSONArray result = jsonObject.getJSONArray("result");
                    List<Citydata> cityList = new ArrayList<>();
                    for(int i=0;i<result.length();i++){
                        JSONObject city = result.getJSONObject(i);
                        Citydata citydata1 = new Citydata();
                        citydata1.setCity(city.getString("city"));
                        citydata1.setProvince(city.getString("province"));
                        citydata1.setDistrict(city.getString("district"));
                        cityList.add(citydata1);
                    }
                    Log.e("liu","response success4");
                    ShowDialog(cityList);

                }catch(Exception e){
                    Log.e("liu","error");
                    e.printStackTrace();
                }
            }else{
                Log.e("liu",msg.obj.toString()+"");
            }
        }
    };

    @SuppressLint("ResourceType")
    public void ShowDialog(List<Citydata> citydataList){

        Log.e("liu","showdialog");

        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.citylist_dialog,(ViewGroup)findViewById(R.id.layout_myview));
        ListView listView = (ListView) layout.findViewById(R.id.list_view);
        CityListAdatper adapter = new CityListAdatper(context,citydataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
                //在这里面就是执行点击后要进行的操作,这里只是做一个显示
                Intent intent1 =new Intent(MainActivity.this,Weathersearch.class);
                intent1.putExtra("cityname",citydataList.get(positon).getDistrict());
                startActivity(intent1);

            }
        });
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
}