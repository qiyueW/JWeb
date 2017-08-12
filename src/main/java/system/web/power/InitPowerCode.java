package system.web.power;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import system.base.annotation.H;
import system.base.annotation.M;
import system.web.WebContext;
import system.web.hm.HMTool;
import system.web.power.ann.DL;
import system.web.power.ann.GG;
import system.web.power.ann.KL;
import system.web.power.ann.SQ;

/**
 *
 * @author wangchunzi
 */
public class InitPowerCode extends HMTool {

    private final Map<String, String[]> urlMapPowerSortAndPowerValue = new HashMap();
    private final system.base.log.SysLog log = new system.base.log.SysLog();

    public void initPowerCode(List<Class> cs) {
        Annotation ann;
        String[] h_uapd;
        M at_M;//@M的数据
        String h_url; //@H的完整路径
        String url; //存入@H+@M的完整路径 
        for (Class c : cs) {
            ann = c.getAnnotation(H.class);
            if (null != ann) {
                h_uapd = this.getHeadSort(c);
                h_url = ((H) ann).value();
                for (Method f : c.getMethods()) {
                    at_M = f.getAnnotation(M.class);
                    if (null != at_M && !getMValueOrURL(at_M).isEmpty()) {
                        urlMapPowerSortAndPowerValue.put(requestURL(h_url, getMValueOrURL(at_M), WebContext.getWebContext().webConfig.HM_SUFFIX), getMethodSort(f, h_uapd));
                    }
                }
            }
        }
        new PCD().setUrlAndPowerData(urlMapPowerSortAndPowerValue);
    }

    private String[] getHeadSort(Class c) {
        DL dl = (DL) c.getAnnotation(DL.class);
        if (null != dl) {
            return new String[]{PDK.DL_SWITCH_KEY, dl.value()};
        }

        SQ sq = (SQ) c.getAnnotation(SQ.class);
        if (null != sq) {
            return new String[]{PDK.SQ_SWITCH_KEY, sq.scope(), sq.value()};
        }

        KL kl = (KL) c.getAnnotation(KL.class);
        if (null != kl) {
            return new String[]{PDK.KL_SWITCH_KEY, kl.value()};
        }

        return new String[]{PDK.GG_SWITCH_KEY};
    }

    private String[] getMethodSort(Method m, String[] defaultkey) {
        GG gg = m.getAnnotation(GG.class);
        if (null != gg) {
            return new String[]{PDK.GG_SWITCH_KEY};
        }
        
        DL dl = m.getAnnotation(DL.class);
        if (null != dl) {
            return new String[]{PDK.DL_SWITCH_KEY, dl.value()};
        }
        SQ sq = m.getAnnotation(SQ.class);
        if (null != sq) {
            return new String[]{PDK.SQ_SWITCH_KEY, sq.scope(), sq.value()};
        }
        KL kl = m.getAnnotation(KL.class);
        if (null != kl) {
            return new String[]{PDK.KL_SWITCH_KEY,kl.scope(), kl.value()};
        }
        //没有标注时，跟头走
        return defaultkey;
    }
}
