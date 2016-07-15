package feicuiedu.test;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/15.
 * path:Assets内db文件路径
 * toFile：目标位置
 *
 */
public class AssetsDBMessager {
    //复制本地Assets 中的db文件到指定目录中
    public static  void copyAssetsFileToFile(Context context, String path, File toFile)throws IOException {
        InputStream inputStream = null;
        //输入流(用来读取当前项目的Assets内的DB文件)
        BufferedInputStream bufferedInputStream = null;
        //输出流（用来将读取到的DB信息写到指定目录文件toFile中去）
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream=context.getAssets().open(path);
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile,false));
            //IO操作
            int len = 0;
            byte[] buff = new byte[2*1024];
            while ((len=bufferedInputStream.read(buff))!=-1){
                      bufferedOutputStream.write(buff,0,len);
            }
            bufferedOutputStream.flush();
            //IO关闭
            bufferedOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        }catch (IOException e){
          throw  e;
        }

    }

}
