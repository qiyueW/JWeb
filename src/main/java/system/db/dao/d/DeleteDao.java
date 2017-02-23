package system.db.dao.d;


/**
 *
 * @author IK
 */
public interface DeleteDao {

    /**
     * 权限ID，删除一条记录
     *
     * @param c
     * @param id
     * @return boolean
     */
    public int dellByID(Class c, String id);

    /**
     * 通过条件删除
     * @param c
     * @param condition
     * @return boolean
     */
    public int dellByCondition(Class c, String condition);

    /**
     * 批量删除(ID形式:"id1,id2,id3............" 或 'v1','v2','v3','v4'..)
     *
     * @param c
     * @param ids
     * @return boolean
     */
    public int dellVast(Class c, String ids);

    /**
     * 清空表
     *
     * @param c
     * @return boolean
     */
    public int dellAll(Class c);

}
