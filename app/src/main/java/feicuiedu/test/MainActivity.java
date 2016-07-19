package feicuiedu.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public  static final String SP_CONFING = "sp_config";
    private static final String IS_FIRST_RUN  ="is_first_run" ;
    ViewPager mViewPager;
    ArrayList mList;
    Button mbtn;
    ImageView mImgview;
    int [] photo={R.mipmap.adware_style_applist,R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成SharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences(SP_CONFING,MODE_PRIVATE);
        //从sp文件中读取key 是否为true
         boolean isFirstRun = sharedPreferences.getBoolean(IS_FIRST_RUN,true);
        //如果不是第一次运行
        if(!isFirstRun){
            startActivity(new Intent(MainActivity.this,PageActivity.class));
            finish();
        }else {
            setContentView(R.layout.activity_main);
            initView();
            initAdapterAction();
        }
    }

    private void initAdapterAction() {
        mViewPager.setAdapter(new MyPagerAdapter(mList));
        mViewPager.addOnPageChangeListener(mListener);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.vp_guid);
        mbtn = (Button) findViewById(R.id.btn);
        mbtn.setVisibility(View.INVISIBLE);
        mbtn.setOnClickListener(this);
        //new对象  否则会出现空指针异常
        mList=new ArrayList<>();
        for (int i = 0; i <photo.length ; i++) {
            mImgview = new ImageView(this);
            mImgview.setImageResource(photo[i]);
            mList.add(mImgview);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
            //对SharedPreferences进行修改
            SharedPreferences  preferences = getSharedPreferences(SP_CONFING,MODE_PRIVATE);
            SharedPreferences.Editor editor= preferences.edit();
            editor.putBoolean(IS_FIRST_RUN,false);
            editor.apply();
            startActivity(new Intent(MainActivity.this,FirstActivity.class));
            finish();

        }


    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer{

        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    ViewPager.OnPageChangeListener mListener= new ViewPager.OnPageChangeListener() {
        @Override
        //当页面滚动时回调
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        //当页面选择后会回调
        public void onPageSelected(int position) {
                 if(position==2){
                     mbtn.setVisibility(View.VISIBLE);
                 }
        }

        @Override
        //当滑动状态改变时
        public void onPageScrollStateChanged(int state) {

        }
    };
}
