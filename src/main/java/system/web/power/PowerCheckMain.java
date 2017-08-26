package system.web.power;

import system.web.JWeb;
import system.web.power.session.PISD;

/**
 *
 * @author wangchunzi
 */
final public class PowerCheckMain {

    public static final boolean ERROR = true;
    public static final boolean SUCCESS = false;

    public static final boolean checkPowerIsError(final JWeb jw, final String url) {
        //执行用户开始方法不通过，终止服务
        if (PCD.UPMO.start_endByTrue(url, jw)) {
            return ERROR;
        }
        String[] x = PCD.getPowerData(url);
        if (null == x) {//不存在的路径
            PCD.UPMO.ggError(url, jw);//调用公共错误方法
            return ERROR;
        }
        switch (x[0]) {
            case PDK.GG_SWITCH_KEY: {//1 公共路径
                PCD.UPMO.ggSuccess(url, jw);
                return SUCCESS;
            }
            case PDK.DL_SWITCH_KEY: {//2
                //登陆失败
                if (null == jw.session.getAttribute(x[1])) {
                    PCD.UPMO.dlError(url, jw);
                    return ERROR;
                }
                //登陆成功
                PCD.UPMO.dlSuccess(url, jw);
                return SUCCESS;
            }
            case PDK.KL_SWITCH_KEY: {//3
                if (x[1].isEmpty()) {//如果没有指定范围
                    return PCD.UPMO.kl_endByTrue(x[2], url, jw);
                }
                //如果指定范围，首先检查是否在范围内
                if (null == jw.session.getAttribute(x[1])) {
                    PCD.UPMO.klError(x[2], url, jw);
                    return ERROR;
                }
                //如果符合指定范围。执行口令方法
                return PCD.UPMO.kl_endByTrue(x[2], url, jw);
            }
            case PDK.SQ_SWITCH_KEY: {//3
                //必须是指定范围，首先检查是否在范围内
                Object obj = jw.session.getAttribute(x[1]);
                if (null == obj) {
                    PCD.UPMO.sqNotLogin(url, jw);
                    return ERROR;
                }
                PISD pisd = (PISD) obj;
                for (int i = 0; i < pisd.power.length; i++) {
                    if (pisd.power[i].equals(x[2])) {
                        PCD.UPMO.sqSuccess(url, jw);
                        return SUCCESS;
                    }
                }
                PCD.UPMO.sqError(url, jw);
                return ERROR;
            }
            case PDK.ZDY_SWITCH_KEY: {
                return PCD.getOneZDY(x[1]).denyByTrue(jw,x[2]);
            }
            default:
                return ERROR;
        }
    }
}
