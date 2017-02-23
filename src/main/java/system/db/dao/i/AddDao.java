package system.db.dao.i;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public interface AddDao {

    /**
     * 添加语句。
     *
     * @param obj
     * @param unique
     * @return boolean
     */
    public int addOne(Object obj, String... unique);

    /**
     * 生成批量操作语句
     *
     * @param <T>
     * @param objs
     * @param unique
     * @return boolean
     */
    public <T> int addVast(List<T> objs, String... unique);
    
    
    
    
    
}
