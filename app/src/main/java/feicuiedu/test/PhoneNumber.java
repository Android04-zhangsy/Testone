package feicuiedu.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneNumber extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView                 mShowNumber;
    private int                      mIdx;
    private ArrayList<TelnumberInfo> numberInfos;
    private Context                  mContext;
    private int                      mPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        mShowNumber = (ListView) findViewById(R.id.shownumber);
        mContext = getApplicationContext();
        //获取启动该activity的intent对象
        Intent intent = getIntent();
        mIdx = intent.getIntExtra("idx", -1);
        try {
            numberInfos = DBReader.readTeldbTable(mIdx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mShowNumber.setAdapter(mBaseAdapter);
    }

    /**
     * 当点击item时弹出对话框，提示是否拨打电话
     */
    @Override
    protected void onResume() {
        super.onResume();
        mShowNumber.setOnItemClickListener(this);

    }

    /*
    点击item事件
    */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;
        view.setBackgroundColor(Color.GRAY);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" warning ");
        builder.setMessage(" 是否拨打 " + numberInfos.get(mPosition).name +
                " ? \n\nTEL ： " + numberInfos.get(mPosition).number);
        builder.setNegativeButton(" 取消 ", null);
        builder.setPositiveButton(" 拨号 ", new
                DialogInterface.OnClickListener() {
                    @SuppressWarnings("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 拨出电话
                        Intent intent = new Intent(Intent.ACTION_CALL,
                                Uri.parse("tel:"+numberInfos.get(mPosition).number));
                        startActivity(intent);
                    }
                });
        builder.show();
    }
    BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return numberInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return numberInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_number_item, null);
            }

            TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            tvName.setText(numberInfos.get(position).name);
            tvNumber.setText(numberInfos.get(position).number);
            return convertView;
        }
    };

}
