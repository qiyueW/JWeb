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

    final private Class cc;

    public IniFieldInfo(Class cc) {
        this.cc = cc;
    }

    public ClassInfo getClassInfo() {
        Table t = (Table) cc.getAnnotation(Table.class);
        ClassInfo s = new ClassInfo(
                null == t || t.value().isEmpty() ? cc.getSimpleName() : t.value(), initField()
        );
        return s;
    }

    private FieldInfo[] initField() {
        Field[] fs = cc.getDeclaredFields();
        FieldInfo[] finfo = new FieldInfo[fs.length];
        int finfo_index = 0;
        List<FieldInfo> list = new ArrayList();
        FieldInfo info;
        for (Field f : fs) {
            system.base.annotation.ID id = f.getAnnotation(system.base.annotation.ID.class);
            system.base.annotation.C c = f.getAnnotation(system.base.annotation.C.class);
            system.base.annotation.Time date = f.getAnnotation(system.base.annotation.Time.class);
            if (id != null) {
                info = new FieldInfo(
                        getTypeCode(f.getType())//属性代码
                        , f.getType()//属性类型
                        , f.getName() //属性名
                        ,null
//                        , null //时间
//                        , null //日期
                        , id.value().isEmpty() ? f.getName() : id.value()//表单列名
                        , f
                );
                finfo[finfo_index++] = info;
            } else {
                if (null != date) {
                    info = new FieldInfo(
                            getTypeCode(f.getType())//属性代码
                            , f.getType()//属性类型
                            , f.getName() //属性名
//                            , null //时间
                            , date.value() //时间格式
                            , null == c || c.value().isEmpty() ? f.getName() : c.value()//表单列名
                            , f
                    );
                    list.add(info);
                }else {
                    info = new FieldInfo(
                            getTypeCode(f.getType())//属性代码
                            , f.getType()//属性类型
                            , f.getName() //属性名
                            , null //时间
//                            , null//日期
                            , null == c || c.value().isEmpty() ? f.getName() : c.value()//表单列名
                            , f
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
