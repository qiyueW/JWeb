package system.web.vo.model;

import system.web.JWeb;

/**
 *
 * @author fly
 */
public interface VO_Tool {
    public String getDate( JWeb jw,String qz,String sqlField,String defaultValue,String reqParam1,String reqParam2);
    /**
     * 默认web传参key  paramkeyDate1,paramkeyDate2
     * @param jw
     * @param qz
     * @param sqlField
     * @return 
     */
    public String getDate( JWeb jw,String qz,String sqlField);
}
