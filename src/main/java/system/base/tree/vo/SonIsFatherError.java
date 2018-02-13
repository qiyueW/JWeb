package system.base.tree.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;

/**
 *
 * @author wangchunzi
 */
final public class SonIsFatherError {

    private final Set<String> sons = new HashSet<>();
    private final List<IdPid> lid = new ArrayList<>();

    /**
     * 检测v_pid是否可以成为v_id的父级。
     *
     * @param list id,pid类的数据源
     * @param idName id字段名
     * @param pidName pid字段名
     */
    public SonIsFatherError(List<?> list, String idName, String pidName) {
        this.iniIdPidDate(list, idName, pidName);
        // this.isError_SonIsFather(v_id, v_pid);
    }

    /**
     *
     * @param v_id 小明
     * @param v_pid 小李
     * @return IdPidEnum
     */
    public IdPidEnum isError_SonIsFather(String v_id, String v_pid) {
        if (v_id.equalsIgnoreCase(v_pid)) {
            return IdPidEnum.ERROR_FatherIsYourSelft;
        } else {
            this.iniSon(v_id); // 找出小明的孩子们
            // 检查小李是否是小明的儿子。是的话，则小李不能成功小明的父亲。
            return sons.contains(v_pid) ? IdPidEnum.ERROR_FatherIsYourSon : IdPidEnum.SUCCESS;
        }
    }

    /**
     * 找miD的儿子
     *
     * @param miD String
     */
    private void iniSon(String miD) {
        for (IdPid obj : lid) {
            if (obj.pid.equalsIgnoreCase(miD)) {// obj对象的父ID,等于我的Id时，表明此对象是我的儿子。
                sons.add(obj.id);
                if (isFather(obj.id)) {// 检查当前儿子是否还有他自己的孩子
                    iniSon(obj.id); // 是，进行回调。循环他的儿子
                }
            }
        }
    }

    /**
     * 初始化相关树数据
     *
     * @param list List
     * @param idName String
     * @param pidName String
     */
    private void iniIdPidDate(final List<?> list, final String idName, final String pidName) {
        ClassInfo ci = ClassFactory.get(list.get(0).getClass());
        for (Object obj : list) {
            lid.add(new IdPid(ci.getFieldInfo(idName).get(obj, null), ci.getFieldInfo(pidName).get(obj, null)));
        }
    }

    /**
     * 判断是否是爸爸
     *
     * @param id String 
     * @return boolean
     */
    private boolean isFather(String id) {
        for (IdPid obj : lid) {
            if (obj.pid.equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 树简单模型
     */
    class IdPid {

        final String id;
        final String pid;

        IdPid(String id, String pid) {
            this.id = id;
            this.pid = pid;
        }
    }
}
