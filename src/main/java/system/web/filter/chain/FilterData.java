package system.web.filter.chain;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class FilterData {

    private final static Map<Class, FilterModel> m = new HashMap();
    
    public void add(Class<? extends FilterModel> s) {
        if (null == m.get(s)) {
            try {
                m.put(s, s.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(FilterData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 各个用户对象资源分配完毕后，将对象指向null
     *
     * @param c
     * @return
     */
    public FilterModel get(Class<? extends Class<FilterModel>> c) {
        return m.get(c);
    }
    
    
    
}
