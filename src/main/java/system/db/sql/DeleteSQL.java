package system.db.sql;

/**
 *
 * @author IK
 */
public interface DeleteSQL {

    /**
     * 权限ID，删除一条记录
     *
     * @param c
     * @param id
     * @return
     */
    public String dellByID(Class c, String id);

    /**
     * 通过条件删除
     * @param c
     * @param condition
     * @return 
     */
    public String dellByCondition(Class c, String condition);

    /**
     * 批量删除(ID形式:"id1,id2,id3............")
     *
     * @param c
     * @param ids
     * @return
     */
    public String dellVast(Class c, String ids);

    /**
     * ids为 'v1','v2','v3','v4'..
     *
     * @param c
     * @param ids
     * @return
     */
    public String dellVast2(Class c, String ids);

    /**
     * 批量删除(ID形式：数组)
     *
     * @param c
     * @param ids
     * @return
     */
    public String dellVast(Class c, String[] ids);

    /**
     * 清空表
     *
     * @param c
     * @return
     */
    public String dellAll(Class c);

}
