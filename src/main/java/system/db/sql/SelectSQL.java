package system.db.sql;

/**
 *
 * @author wangchunzi
 */
public interface SelectSQL {

    /**
     * 查询一条记录，根据ID
     *
     * @param c
     * @param id
     * @return String
     */
    public String selectOneByID(Class c, String id);
    /**
     * 查询一条记录。根据条件
     *
     * @param c
     * @param condition
     * @return String
     */
    public String selectOneByCondition(Class c, String condition);

    /**
     * 查询表的总记录数
     *
     * @param c
     * @return String
     */
    public String selectCount(Class c);
    /**
     * 查询表的记录数 根据条件
     * @param c
     * @param condition
     * @return String
     */
    public String selectCountByCondition(Class c, String condition);
    
    /**
     * 查询所有
     * @param c
     * @return  String
     */
    public String select(Class c);
    /**
     *  查询所有，并进行自定义排序
     * @param c
     * @param orderby 对于数据库字段前缀：无！
     * @return String
     */
    public String select(Class c, String orderby);
    /**
     * 查询所有 根据条件
     * @param c
     * @param condition
     * @return String
     */
    public String selectByCondition(Class c, String condition);
    /**
     * 查询所有，根据条件，并进行自定义排序
     * @param c
     * @param condition 条件
     * @param orderby 对于数据库字段前缀：无！
     * @return String
     */
    public String selectByCondition(Class c, String condition, String orderby);
    
    /**
     * 分页查询
     * @param c
     * @param page
     * @param pageCount
     * @return 
     */
    public String selectVast(Class c, int page, int pageCount);
    /**
     * 分页查询 并进行排序
     * @param c
     * @param page
     * @param pageCount
     * @param orderby
     * @return 
     */
    public String selectVast(Class c, int page, int pageCount, String orderby);
    /**
     * 分页查询 根据条件
     * @param c
     * @param page
     * @param pageCount
     * @param condition
     * @return 
     */
    public String selectVastByCondition(Class c, int page, int pageCount, String condition);
    /**
     * 分页查询 根据条件 并进行排序
     * @param c
     * @param page
     * @param pageCount
     * @param condition
     * @param orderby
     * @return 
     */
    public String selectVastByCondition(Class c, int page, int pageCount, String condition, String orderby);
    
}
