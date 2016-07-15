package feicuiedu.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
   EditText mText;
    Button mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mText = (EditText)findViewById(R.id.text);
        mbtn=(Button) findViewById(R.id.btn);
    }

    public void dial(View view) {
        //触发拨号键盘
        String number=mText.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
        startActivity(intent);//如果报错 提示permission check 修改targetSDK为22以下
    }
}
