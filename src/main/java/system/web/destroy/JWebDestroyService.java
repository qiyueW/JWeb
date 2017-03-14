package system.web.destroy;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public class JWebDestroyService {

    public Class ini(List<Class> cs) {
        for (Class c : cs) {
            if (Destroy.class.isAssignableFrom(c) && !c.equals(Destroy.class)) {
                    return c;
            }
        }
        return null;
    }
}
