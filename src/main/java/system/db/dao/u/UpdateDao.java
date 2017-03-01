package system.db.dao.u;

/**
 *
 * @author IK
 */
public interface UpdateDao {

//    public int update_sql(String sql);

    /**
     * 更新对象。自动跳过为null的值
     *
     * @param obj
     * @param unique
     * @return
     */
    public int update_notNull(Object obj, String... unique);

    /**
     * 更新对象obj
     *
     * @param obj
     * @param unique
     * @return
     */
    public int update_all(Object obj, String... unique);

    /**
     * 根据ID更新（除开指定字段的值）。不管值是否为null
     *
     * @param obj //更新实体
     * @param rejectField 不更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @param unique
     * @return
     */
    public int updateSome_reject(Object obj, String rejectField, String... unique);

    /**
     * 根据ID更新指定属性。不管值是否为null
     *
     * @param obj //更新实体
     * @param alloyField 更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @param unique
     * @return
     */
    public int updateSome_alloy(Object obj, String alloyField, String... unique);

}
