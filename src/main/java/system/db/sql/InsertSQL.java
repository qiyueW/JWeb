package system.db.sql;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public interface InsertSQL {

    /**
     * 添加语句。
     *
     * @param obj
     * @return
     */
    public String addOne(Object obj);

    /**
     * 添加语句。
     *
     * @param obj
     * @return
     */
    public String addOneByMyID(Object obj);

    /**
     * 添加语句。
     *
     * @param obj
     * @return String[] [0]是sql,[1]是id
     */
    public String[] addAndReturnID(Object obj);

    /**
     * 生成批量操作语句
     *
     * @param <T>
     * @param objs
     * @return
     */
    public <T> String addVast(List<T> objs);

    /**
     * InsertSQL
     *
     * @param <T>
     * @param obj
     * @param replaceName
     * @param replaceValue
     * @return String
     */
    public <T> String addOne_replace(final T obj, final String replaceName, final String replaceValue);

    public <T> String addVast_replace(final List<T> objs, final String replaceName, final String replaceValue);
}
