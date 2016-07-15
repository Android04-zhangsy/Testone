package feicuiedu.test;

/**
 * Created by Administrator on 2016/7/15.
 */

/**
 * 获取数据库classlist里的数据
 */
public class TelclassInfo {
      //电话名称
      public String name;
    //唯一 ID
      public int idx;
    //重写构造方法
    public TelclassInfo(String name, int idx) {
        super();
        this.name = name;
        this.idx = idx;
    }
    //系统默认构造方法
    public TelclassInfo() {
        super();
    }

}
