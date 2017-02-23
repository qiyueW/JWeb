package system.db.dao.d;

/**
 * 1.删除某离职用户 style=0,同时查询是否有此用户发生过的业务 表yw1(有过业务则不能删除)
 * @author wangchunzi
 */
public interface DeleteByCheckDao {

    /**
     * 删除一条记录,删除前，先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     *
     * @param <T>
     * @param dellc 表关联的对象类(删除对象)
     * @param id 表记录的主键(不要加入where!)
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    public <T>int deleteOneByID_CheckToDeny(Class<T> dellc, String id, String denyCondition, Class... check);
/**
     * 删除一条记录,删除前，先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     *
     * @param <T>
     * @param dellc 表关联的对象类(删除对象)
     * @param dellCondition 自定义条件。必须加入前缀 WHERE
     * @param denyCondition  本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟dellCondition 组装成 dellCondition and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    public <T>int deleteOneByCondition_CheckToDeny(Class<T> dellc, String dellCondition, String denyCondition, Class... check);

    /**
     * 删除一条记录,删除前:
     * <p/>
     * 1.先检查主表的阻止删除条件查询结束是否有值。如果有值，直接返回-1
     * <p/>
     * 2.先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     * <p/>
     *
     * @param <T>
     * @param dellc 表关联的对象类(删除对象)
     * @param ids 表记录的主键集合 "id1,id2,id3,id4,i5...idN" 或为 (不要加入where!)
     * "'id1','id2','id3','id4'.....'idN'"
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    public <T>int deleteVastByID_CheckToDeny(Class<T> dellc, String ids, String denyCondition, Class... check);
    
    /**
     * 删除一条记录,删除前:
     * <p/>
     * 1.先检查主表的阻止删除条件查询结束是否有值。如果有值，直接返回-1
     * <p/>
     * 2.先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     * <p/>
     *
     * @param <T>
     * @param dellc 表关联的对象类(删除对象)
     * @param dellCondition 表记录的主键集合 "id1,id2,id3,id4,i5...idN" 或为
     * "'id1','id2','id3','id4'.....'idN'"
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    public <T>int deleteVastByCondition_CheckToDeny(Class<T> dellc, String dellCondition, String denyCondition, Class... check);
    
    
}
