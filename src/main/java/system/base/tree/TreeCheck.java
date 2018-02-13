package system.base.tree;

import java.util.List;

import system.base.tree.vo.IdPidEnum;
import system.base.tree.vo.SonIsFatherError;

final public class TreeCheck {

	 /**
     * 检查pid是否可以成为mid的上级
     * <p>
     * 通过:返回IdPidEnum(int)
     *
     * @param list List
     * @param idName String
     * @param pidName String
     * @param mid String
     * @param pid String
     * @return IdPidEnum
     */
    public IdPidEnum getError_FatherIsSon(List<?> list, String idName, String pidName, String mid, String pid) {
        return new SonIsFatherError(list, idName, pidName).isError_SonIsFather(mid, pid);
    }
    
    
}
