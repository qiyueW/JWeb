package system.db.dao.s;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public interface SelectDao {

    /**
     * 查询一条记录，根据ID
     *
     * @param <T>
     * @param c
     * @param id
     * @return String
     */
    public <T> T selectOneByID(Class<T> c, String id);

    /**
     * 查询一条记录。根据条件
     *
     * @param <T>
     * @param c
     * @param condition
     * @return String
     */
    public <T> T selectOneByCondition(Class<T> c, String condition);

    /**
     * 查询表的总记录数
     *
     * @param c
     * @return String
     */
    public int selectCount(Class c);

    /**
     * 查询表的记录数 根据条件
     *
     * @param c
     * @param condition
     * @return String
     */
    public int selectCountByCondition(Class c, String condition);

    /**
     * 查询所有
     *
     * @param <T>
     * @param c
     * @return String
     */
    public <T> List<T> select(Class<T> c);

    /**
     * 查询所有，并进行自定义排序
     *
     * @param <T>
     * @param c
     * @param orderby 对于数据库字段前缀：无！
     * @return String
     */
    public <T> List<T> select(Class<T> c, String orderby);
    
    /**
     * 查询所有 根据条件
     *
     * @param <T>
     * @param c
     * @param condition
     * @return String
     */
    public  <T>List<T> selectByCondition(Class<T> c, String condition);

    /**
     * 查询所有，根据条件，并进行自定义排序
     *
     * @param <T>
     * @param c
     * @param condition 条件
     * @param orderby 对于数据库字段前缀：无！
     * @return String
     */
    public  <T>List<T> selectByCondition(Class<T> c, String condition, String orderby);

    /**
     * 分页查询
     *
     * @param <T>
     * @param c
     * @param page
     * @param pageCount
     * @return
     */
    public  <T>List<T> selectVast(Class<T> c, int page, int pageCount);

    /**
     * 分页查询 并进行排序
     *
     * @param <T>
     * @param c
     * @param page
     * @param pageCount
     * @param orderby
     * @return
     */
    public  <T>List<T> selectVast(Class<T> c, int page, int pageCount, String orderby);

    /**
     * 分页查询 根据条件
     *
     * @param <T>
     * @param c
     * @param page
     * @param pageCount
     * @param condition
     * @return
     */
    public  <T>List<T> selectVastByCondition(Class<T> c, int page, int pageCount, String condition);

    /**
     * 分页查询 根据条件 并进行排序
     *
     * @param <T>
     * @param c
     * @param page
     * @param pageCount
     * @param condition
     * @param orderby
     * @return
     */
    public  <T>List<T> selectVastByCondition(Class<T> c, int page, int pageCount, String condition, String orderby);

}
