package system.db.dao.d;

/**
 *
 * @author wangchunzi
 */
public interface OODelete {
    /**
     * 第一个参数是主键，第二个一定是主表的关联对象，之后的是副表中的相关记录。一并删除。
     * @param id
     * @param needDelete
     * @return 
     */
    public int ooDelete(String id, Class... needDelete);
    
}
