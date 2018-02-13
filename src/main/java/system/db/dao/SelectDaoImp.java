package system.db.dao;

import java.util.List;
import system.db.dao.s.SelectDao;
import system.db.sql.SQL;

/**
 *
 * @author wangchunzi
 */
final public class SelectDaoImp implements SelectDao {

    private final SQL sql;
    private final ADUS adus;
    
    public SelectDaoImp(final ADUS adus, final SQL sql) {
        this.sql =sql;
        this.adus=adus;
    }

    
    /**
     * 查询一条记录，根据ID
     *
     * @param <T> 泛型
     * @param c 类型
     * @param id
     * @return T
     */
    @Override
    public <T> T selectOneByID(Class<T> c, String id) {
        return adus.executeQueryOne(c, sql.selectOneByID(c, id));
    }

    /**
     * 查询一条记录。根据条件
     *
     * @param <T> 泛型
     * @param c 类型
     * @param condition
     * @return T
     */
    @Override
    public <T> T selectOneByCondition(Class<T> c, String condition) {
        return adus.executeQueryOne(c, sql.selectOneByCondition(c, condition));
    }

    /**
     * 查询表的总记录数
     *
     * @param c 类型
     * @return int
     */
    @Override
    public int selectCount(Class c) {
        return adus.executeQueryCount(sql.selectCount(c));
    }

    /**
     * 查询表的记录数 根据条件
     *
     * @param c 类型
     * @param condition
     * @return int
     */
    @Override
    public int selectCountByCondition(Class c, String condition) {
        return adus.executeQueryCount(sql.selectCountByCondition(c, condition));
    }

    /**
     * 查询所有
     *
     * @param <T> 泛型
     * @param c 类型
     * @return List
     */
    @Override
    public <T> List<T> select(Class<T> c) {
        return adus.executeQueryVast(c, sql.select(c));
    }

    /**
     * 查询所有，并进行自定义排序
     *
     * @param <T> 泛型
     * @param c 类型
     * @param orderby 对于数据库字段前缀：无！
     * @return List
     */
    @Override
    public <T> List<T> select(Class<T> c, String orderby) {
        return adus.executeQueryVast(c, sql.select(c, orderby));
    }

    /**
     * 查询所有 根据条件
     *
     * @param <T> 泛型
     * @param c 类型
     * @param condition
     * @return List
     */
    @Override
    public <T> List<T> selectByCondition(Class<T> c, String condition) {
        return adus.executeQueryVast(c, sql.selectByCondition(c, condition));
    }

    /**
     * 查询所有，根据条件，并进行自定义排序
     *
     * @param <T> 泛型
     * @param c 类型
     * @param condition 条件
     * @param orderby 对于数据库字段前缀：无！
     * @return List
     */
    @Override
    public <T> List<T> selectByCondition(Class<T> c, String condition, String orderby) {
        return adus.executeQueryVast(c, sql.selectByCondition(c, condition, orderby));
    }

    /**
     * 分页查询
     *
     * @param <T> 泛型
     * @param c 类型
     * @param page
     * @param pageCount
     * @return List
     */
    @Override
    public <T> List<T> selectVast(Class<T> c, int page, int pageCount) {
        return adus.executeQueryVast(c, sql.selectVast(c, page, pageCount));
    }

    /**
     * 分页查询 并进行排序
     *
     * @param <T> 泛型
     * @param c 类型
     * @param page
     * @param pageCount
     * @param orderby
     * @return List
     */
    @Override
    public <T> List<T> selectVast(Class<T> c, int page, int pageCount, String orderby) {
        return adus.executeQueryVast(c, sql.selectVast(c, page, pageCount, orderby));
    }

    /**
     * 分页查询 根据条件
     *
     * @param <T> 泛型
     * @param c 类型
     * @param page 
     * @param pageCount
     * @param condition
     * @return List
     */
    @Override
    public <T> List<T> selectVastByCondition(Class<T> c, int page, int pageCount, String condition) {
        return adus.executeQueryVast(c, sql.selectVastByCondition(c, page, pageCount, condition));
    }

    /**
     * 分页查询 根据条件 并进行排序
     *
     * @param <T> 泛型
     * @param c 类型
     * @param page
     * @param pageCount
     * @param condition
     * @param orderby
     * @return List
     */
    @Override
    public <T> List<T> selectVastByCondition(Class<T> c, int page, int pageCount, String condition, String orderby) {
        return adus.executeQueryVast(c, sql.selectVastByCondition(c, page, pageCount, condition, orderby));
    }


}
