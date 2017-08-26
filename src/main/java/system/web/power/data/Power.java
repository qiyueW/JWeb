package system.web.power.data;

public class Power {

    @system.base.annotation.ID
    private String power_id;//权限主键
    private String power_pid;//权限上级主键
    private String power_name;//权限名
    private String view_url;//视图路径
    private String pv_sort;//权限|视图
    private Boolean isexpand;//视图树-是否展开

    //indexdata2.push({ id: '2.1', pid: '2', text: 'ERP工单分类管理' ,isexpand: false});
    public String getViewMenuJSON() {
        return "{ \"power_id\":\"" + this.power_id
                + "\", \"power_pid\":\"" + this.power_pid
                + "\", \"power_name\":\"" + this.power_name
                + (null != this.view_url && this.view_url.length() > 0 ? "\", \"view_url\":\"" + this.view_url : "")
                + "\", \"isexpand\":\"" + this.isexpand + "\"}";
    }

    public String getShowPVMenuJSON() {
        return "{ \"power_id\":\"" + this.power_id
                + "\", \"power_pid\":\"" + this.power_pid
                + "\", \"power_name\":\"" + this.power_name
                + "\", \"isexpand\":\"" + this.isexpand + "\"}";
    }

    /**
     * 取 权限主键
     *
     * @return String
     */
    public String getPower_id() {
        return power_id;
    }

    /**
     * 设置 权限主键
     *
     * @param power_id
     */
    public void setPower_id(String power_id) {
        this.power_id = power_id;
    }

    /**
     * 取 权限上级主键
     *
     * @return String
     */
    public String getPower_pid() {
        return power_pid;
    }

    /**
     * 设置 权限上级主键
     *
     * @param power_pid
     */
    public void setPower_pid(String power_pid) {
        this.power_pid = power_pid;
    }

    /**
     * 取 权限名
     *
     * @return String
     */
    public String getPower_name() {
        return power_name;
    }

    /**
     * 设置 权限名
     *
     * @param power_name
     */
    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    /**
     * 取 视图路径
     *
     * @return String
     */
    public String getView_url() {
        return view_url;
    }

    /**
     * 设置 视图路径
     *
     * @param view_url
     */
    public void setView_url(String view_url) {
        this.view_url = view_url;
    }

    /**
     * 取 权限|视图
     *
     * @return String
     */
    public String getPv_sort() {
        return pv_sort;
    }

    /**
     * 设置 权限|视图
     *
     * @param pv_sort
     */
    public void setPv_sort(String pv_sort) {
        this.pv_sort = pv_sort;
    }

    /**
     * 取 视图树-是否展开
     *
     * @return Boolean
     */
    public Boolean getIsexpand() {
        return isexpand;
    }

    /**
     * 设置 视图树-是否展开
     *
     * @param isexpand
     */
    public void setIsexpand(Boolean isexpand) {
        this.isexpand = isexpand;
    }
}
