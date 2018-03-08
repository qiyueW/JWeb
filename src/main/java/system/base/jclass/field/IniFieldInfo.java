package system.base.jclass.field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import system.base.jclass.ClassInfo;
import static system.base.jclass.ClassTypeCode.getTypeCode;
import system.base.annotation.Table;

/**
 *
 * @author wangchunzi
 */
final public class IniFieldInfo {

    final private Class<?> cc;

    /**
     * 构造方法
     * @param cc  Class参数类 
     */
    public IniFieldInfo(Class<?> cc) {
        this.cc = cc;
    }

    /**
     * 取得其他相关一些信息
     *
     * @return ClassInfo
     */
    public ClassInfo getClassInfo() {
        Table t = (Table) cc.getAnnotation(Table.class);
        ClassInfo s = new ClassInfo(
                null == t || t.value().isEmpty() ? cc.getSimpleName() : t.value(), initField(), this.unAuto
        );
        return s;
    }
    private boolean unAuto = false;

    /**
     * 初始化Field
     *
     * @return FieldInfo[]
     */
    private FieldInfo[] initField() {
//        System.out.println(cc.getName() + "//" + cc.getDeclaredFields().length + "//////////////////////////");
        Field[] fs = cc.getDeclaredFields();
        FieldInfo[] finfo = new FieldInfo[fs.length];
        int finfo_index = 0;
        List<FieldInfo> list = new ArrayList();
        FieldInfo info;
        boolean isUnauto;
        for (Field f : fs) {
//            System.out.println(f.getName());
            system.base.annotation.ID id = f.getAnnotation(system.base.annotation.ID.class);
            system.base.annotation.C c = f.getAnnotation(system.base.annotation.C.class);
            system.base.annotation.Time date = f.getAnnotation(system.base.annotation.Time.class);
            system.base.annotation.Auto auto = f.getAnnotation(system.base.annotation.Auto.class);
            isUnauto = null == auto || auto.value() == 0;
            if (!isUnauto) {//如果是自增数据库字段
                unAuto = false;//将非自动属性 设置为 false
            }
            if (id != null) {
                info = new FieldInfo(
                        getTypeCode(f.getType())//属性代码
                        ,
                         f.getType()//属性类型
                        ,
                         f.getName() //属性名
                        ,
                         null //                        , null //时间
                        //                        , null //日期
                        ,
                         id.value().isEmpty() ? f.getName() : id.value()//表单列名
                        ,
                         f,
                        isUnauto
                );
                finfo[finfo_index++] = info;
            } else {
                if (null != date) {
                    info = new FieldInfo(
                            getTypeCode(f.getType())//属性代码
                            ,
                             f.getType()//属性类型
                            ,
                             f.getName() //属性名
                            //                            , null //时间
                            ,
                             date.value() //时间格式
                            ,
                             null == c || c.value().isEmpty() ? f.getName() : c.value()//表单列名
                            ,
                             f,
                            isUnauto
                    );
                    list.add(info);
                } else {
                    info = new FieldInfo(
                            getTypeCode(f.getType())//属性代码
                            ,
                             f.getType()//属性类型
                            ,
                             f.getName() //属性名
                            ,
                             null //时间
                            //                            , null//日期
                            ,
                             null == c || c.value().isEmpty() ? f.getName() : c.value()//表单列名
                            ,
                             f,
                            isUnauto
                    );
                    list.add(info);
                }
            }
        }
        for (FieldInfo fi : list) {
            finfo[finfo_index++] = fi;
        }
        return finfo;
    }
}
