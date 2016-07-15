package feicuiedu.test;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyPagerAdapter extends PagerAdapter {
    ArrayList<View> mList;

    public MyPagerAdapter(ArrayList<View> list) {
          mList=list;
    }

    @Override
    public int getCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position),0);
        return mList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        boolean b=view==object;
        return b;
    }
    @Override
    //当Item不可见时  销毁Item
    public void destroyItem(ViewGroup container, int position, Object object) {
        //让系统处理 移除哪个View
        container.removeView(mList.get(position));
    }

}
