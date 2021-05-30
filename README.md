# Examination
### APP目的：实现城市天气的查询 （实现了通过手动输入城市名进行查询、获取城市列表选择城市查询）
#### api使用了聚合数据提供的天气查询和支持城市列表。
### 使用步骤：
1、开始界面：![images](https://github.com/Henry-13/CSA/blob/master/images/1.png)     
2、可以通过在对话框中输入城市名进行查询，也可以点击下方Button获取城市列表进行选择      
3、等待加载，卡一小小会（尴尬）后会出现查询城市的天气和未来五天预报
![images](https://github.com/Henry-13/CSA/blob/master/images/2.png)
### 主要实现功能：
#### 输入查询：
![images](https://github.com/Henry-13/CSA/blob/master/images/3.png)
部分代码：
```
  button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Weathersearch.class);
                intent.putExtra("cityname",edit1.getText().toString());   //使用intent跳转至天气查询activity，并传入城市名
                startActivity(intent);
            }
        });
```
```
  Message msg=mhandler.obtainMessage(1);   //使用messsge-handler机制实现线程间传递数据
  msg.obj=result;
  mhandler.sendMessage(msg);
```

#### 列表查询：
![images](https://github.com/Henry-13/CSA/blob/master/images/4.png)
```
//实现list弹窗、并在每一个item设置点击事件
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
```

### 知识点：
1、网络请求：API的使用，JSON解析，多线程的实现、线程间的通信（用回调机制来实现也学了但因为ui不好更新就没用）      
2、弹窗Dialog的实现     
3、ui界面的更新（做的很粗糙感觉....很多地方想实现一些功能但力不从心...）

### 心得体会：
学会了很多东西，也体会到了自己实现一个东西的快乐。
