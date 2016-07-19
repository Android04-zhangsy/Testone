package feicuiedu.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/18.
 */
public class GridAdatper extends BaseAdapter {
        Context mContext;
    public GridAdatper(Context context){
        mContext = context;

    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView==null){
          imageView = new ImageView(mContext);
            //对图片进行宽高设置
            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    private  Integer[] mThumbIds={
            R.mipmap.icon_rocket,R.mipmap.icon_softmgr,R.mipmap.icon_phonemgr,
            R.mipmap.icon_telmgr,R.mipmap.icon_filemgr,R.mipmap.icon_sdclean
    };
}
