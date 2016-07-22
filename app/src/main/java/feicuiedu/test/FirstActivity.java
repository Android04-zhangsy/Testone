package feicuiedu.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class FirstActivity extends AppCompatActivity {
    private  Integer[] mThumbIds = {
            R.mipmap.icon_rocket,R.mipmap.icon_softmgr,R.mipmap.icon_phonemgr,
            R.mipmap.icon_telmgr,R.mipmap.icon_filemgr,R.mipmap.icon_sdclean};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new GridAdatper(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==3){
                    startActivity(new Intent(FirstActivity.this,NumberActivity.class));
                }

            }
        });
    }


}
