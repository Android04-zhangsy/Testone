package feicuiedu.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
   // private List<Map<String,Object>> mlist;
    private Context mcontext;
    ListView mListView;
    private ArrayList<TelclassInfo> mClassinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        Copy();
        try {
            mClassinfo = DBReader.readTeldbClasslist();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mListView = (ListView) findViewById(R.id.list_view);
       /* mlist=getData();*/
        mcontext=getApplicationContext();
       // mAdapter=new MyAdapter(mlist,mcontext);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);



    }
    public  void Copy(){
        //判断当前的package中有没有数据库的存在
        if(!DBReader.isExistDBFile()){
       //不存在则copy到包名下
            try {
                AssetsDBMessager.copyAssetsFileToFile(getApplicationContext(),"db/commonnum.db",DBReader.telFile);
            }catch (IOException e){
                Toast.makeText(NumberActivity.this,"数据库copy可能有异常",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
/*
    private List<Map<String,Object>> getData() {
        mlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        */
/*dataMap.put("name", "三国演义");
        dataMap.put("auther", "罗贯中");*//*

        dataMap.put("context", "fdsdfsg");
        mlist.add(dataMap);

        dataMap = new HashMap<String, Object>();
        */
/*dataMap.put("name", "水浒传");
        dataMap.put("auther", "施耐庵");*//*

        dataMap.put("context", "gfdreswe");
        mlist.add(dataMap);

        dataMap = new HashMap<String, Object>();
        */
/*dataMap.put("name", "西游记");
        dataMap.put("auther", "吴承恩");*//*

        dataMap.put("context", "gxsfgds");
        mlist.add(dataMap);

        dataMap = new HashMap<String, Object>();
        */
/*dataMap.put("name", "红楼梦");
        dataMap.put("auther", "曹雪芹+高鹗");*//*

        dataMap.put("context", "gsagf");
        mlist.add(dataMap);

        return mlist;


    }
*/
 BaseAdapter mAdapter = new BaseAdapter() {
    @Override
    public int getCount() {
        if(mClassinfo != null) {
            return mClassinfo.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mClassinfo.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item,null);

        }
        TextView tvShowData = (TextView) convertView.findViewById(R.id.tv_showdata);
        tvShowData.setText(mClassinfo.get(position).name);
        return null;
    }
};

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击跳转
        Intent intent = new Intent(this, PhoneNumber.class);
        int idx = 0;
        try {
            idx = DBReader.readTeldbClasslist().get(position).idx;
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("idx", idx);
        startActivity(intent);
    }
}

