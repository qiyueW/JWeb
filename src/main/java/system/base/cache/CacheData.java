package system.base.cache;

import system.db.Service;

/**
 *
 * @author jweb
 * @param <T>
 */
public abstract class CacheData<T> {
//
//    protected T obj;
//    protected T objs[];//数组
//    protected List<T> list;
//    protected String json;//纯json化
//    protected String zdyJson;//自定义json化，比如加入些参数头之类。
//    protected String zdyJson1;//自定义json化，比如加入些参数头之类。

    /**
     * 抽象方法-加载数据
     * @param db jdbc对象实例
     */
    public abstract void loadData(final Service db);
//    public abstract List<T> getList();
    
}
