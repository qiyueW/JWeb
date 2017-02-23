      package system.db.sql;

/**
 *
 * @author IK
 */
public interface UpdateSQL {

    /**
     * 更新对象。自动跳过为null的值
     *
     * @param obj
     * @return
     */
    public String update_notNull(Object obj);
    
    /**
     * 更新对象obj
     *
     * @param obj
     * @return
     */
    public String update_all(Object obj);


    /**
     * 根据ID更新（除开指定字段的值）。不管值是否为null
     *
     * @param obj //更新实体
     * @param rejectField 不更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @return
     */
    public String updateSome_reject(Object obj, String rejectField);
    
        /**
     * 根据ID更新指定属性。不管值是否为null
     *
     * @param obj //更新实体
     * @param alloyField 更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @return
     */
    public String updateSome_alloy(Object obj, String alloyField);
    
}
