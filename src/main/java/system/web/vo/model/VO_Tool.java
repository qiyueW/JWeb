package system.web.vo.model;

/**
 *
 * @author fly
 */
public interface VO_Tool {

    public String getDateScope(String qz, String sqlField, String defaultValue, String reqParam1, String reqParam2);

    public String getDateScope2(String qz, String sqlField, String defaultValue, String d1, String d2);

    /**
     * 默认web传参key paramkeyDate1,paramkeyDate2
     *
     * @param qz
     * @param sqlField
     * @return
     */
    public String getDateScope(String qz, String sqlField);
}
