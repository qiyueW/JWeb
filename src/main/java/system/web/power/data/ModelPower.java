package system.web.power.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jweb
 */
public class ModelPower {

    public final Integer key;
    public final List<Power> pp;
    public final List<Power> vp;

    //团体key_map
    private final static Map<Integer, ModelPower> KEY_MAP = new HashMap<>();

    public ModelPower(final Integer key, final List<Power> p, final List<Power> v) {
        this.key = key;
        pp = p;
        vp = v;
        KEY_MAP.put(key, this);
    }

    /**
     * 根据模块key，取得模块权限
     * @param key
     * @return 
     */
    public final static ModelPower[] getModelPower(int... key) {
        ModelPower mps[] = new ModelPower[key.length];
        for (int i = 0; i < key.length; i++) {
            mps[i] = KEY_MAP.get(key[i]);
        }
        return mps;
    }
}
