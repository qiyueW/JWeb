package system.web.power.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jweb
 */
public class PD {

    private final static List<Power> PLIST = new ArrayList<>();
    private final static List<Power> VLIST = new ArrayList<>();

    //团体key_map
//    private final static Map<Integer, ModelPower> KEY_MAP = new HashMap<>();
    private final List<Power> plist = new ArrayList<>();
    private final List<Power> vlist = new ArrayList<>();

    public void initEndModelDo_setKeyMapModule(Integer key) {
        new ModelPower(key, plist, vlist);
    }

    public enum PowerSort {
        hm("p"), view("v"), hmViwe("pv");
        public final String key;

        private PowerSort(String key) {
            this.key = key;
        }
    }

    /**
     * 菜单容器其 权限
     *
     * @param key
     * @return
     */
    public static List<Power> getPowerMenue(int... key) {
        List<Power> list = new ArrayList<>();
        for (ModelPower mp : ModelPower.getModelPower(key)) {
            list.addAll(mp.pp);
        }
        return list;
    }

    /**
     * 显示所有菜单
     *
     * @param key
     * @return
     */
    public static List<Power> getViewMenue(int... key) {
        List<Power> list = new ArrayList<>();
        for (ModelPower mp : ModelPower.getModelPower(key)) {
            list.addAll(mp.vp);
        }
        return list;
    }

    /**
     * 菜单容器其 权限
     *
     * @return
     */
    public static List<Power> getPowerMenue() {
        return PLIST;
    }

    /**
     * 显示所有菜单
     *
     * @return
     */
    public static List<Power> getViewMenue() {
        return VLIST;
    }

    public static String getViewMenueByJson(final String[] ids, int... key) {

        if (null == ids || ids.length == 0) {
            return "[{}]";
        }
        StringBuilder sb = new StringBuilder();
        List<Power> vmpower = getViewMenue(key);
        xx:
        for (Power obj : vmpower) {
            for (int i = 0; i < ids.length; i++) {
                if (obj.getPower_id().equals(ids[i])) {
                    sb.append(",{")
                            .append("\"power_pid\":\"").append(obj.getPower_pid()).append("\"")
                            .append(",\"power_id\":\"").append(obj.getPower_id()).append("\"")
                            .append(",\"power_name\":\"").append(obj.getPower_name()).append("\"")
                            .append(",\"pv_sort\":\"").append(obj.getPv_sort()).append("\"");
                    if (null != obj.getView_url() && obj.getView_url().length() > 0) {
                        sb.append(",\"view_url\":\"").append(obj.getView_url()).append("\"");
                    }
                    sb.append("}");
                    continue xx;
                }
            }
        }
        return sb.length() > 0 ? "[" + sb.substring(1) + "]" : "[{}]";
    }

    /**
     * 显示指定id的所有菜单(按顺序)
     *
     * @param ids
     * @return
     */
    public static String getViewMenueByJson(final String[] ids) {
        if (null == ids || ids.length == 0) {
            return "[{}]";
        }
        StringBuilder sb = new StringBuilder();
        xx:
        for (Power obj : VLIST) {
            for (int i = 0; i < ids.length; i++) {
                if (obj.getPower_id().equals(ids[i])) {
                    sb.append(",{")
                            .append("\"power_pid\":\"").append(obj.getPower_pid()).append("\"")
                            .append(",\"power_id\":\"").append(obj.getPower_id()).append("\"")
                            .append(",\"power_name\":\"").append(obj.getPower_name()).append("\"")
                            .append(",\"pv_sort\":\"").append(obj.getPv_sort()).append("\"");
                    if (null != obj.getView_url() && obj.getView_url().length() > 0) {
                        sb.append(",\"view_url\":\"").append(obj.getView_url()).append("\"");
                    }
                    sb.append("}");
                    continue xx;
                }
            }
        }
        return sb.length() > 0 ? "[" + sb.substring(1) + "]" : "[{}]";
    }

    //==============================================================================
    /**
     * 加入一个HM节点(默认展开)
     *
     * @param power_pid 父健
     * @param power_id 主键
     * @param power_name 显示名 // * @param isExpand 是否展开
     * @return
     */
    public PD setHMNode(String power_pid, String power_id, String power_name) {
        if(this.isError(power_id)){
            System.err.print("权限标识重复! id:"+power_id+",name:"+power_name);
            System.exit(0);
        }
        Power obj = new Power();
        obj.setPower_pid(power_pid);
        obj.setPower_id(power_id.trim());
        obj.setPower_name(power_name);
        obj.setIsexpand(false);
        obj.setPv_sort(PowerSort.hm.key);
        PLIST.add(obj);
        this.plist.add(obj);
        return this;
    }

    private boolean isError(String id) {
        id = id.trim();
        for (Power obj : PLIST) {
            if (obj.getPower_id().equals(id)) {
                return true;
            }
        }
        for (Power obj : VLIST) {
            if (obj.getPower_id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 加入一个视图节点(默认展开)
     *
     * @param power_pid 父健
     * @param power_id 主键
     * @param power_name 显示名
     * @param view_url 视图路径
     * @param isExpand 是否展开
     * @return
     */
    public PD setViewNode(String power_pid, String power_id, String power_name, String view_url, boolean isExpand) {
        if(this.isError(power_id)){
            System.err.print("权限标识重复! id:"+power_id+",name:"+power_name);
            System.exit(0);
        }
        Power obj = new Power();
        obj.setPower_pid(power_pid);
        obj.setPower_id(power_id);
        obj.setPower_name(power_name);
        obj.setView_url(view_url);
        obj.setIsexpand(isExpand);
        obj.setPv_sort(PowerSort.view.key);

        PLIST.add(obj);
        VLIST.add(obj);
        this.plist.add(obj);
        this.vlist.add(obj);
        return this;
    }

    /**
     * 加入一个HM混合视图节点(默认展开)
     *
     * @param power_pid 父健
     * @param power_id 主键
     * @param power_name 显示名
     * @param view_url 视图路径
     * @param isExpand 是否展开
     * @return
     */
    public PD setHMAndViewNode(String power_pid, String power_id, String power_name, String view_url, boolean isExpand) {
        if(this.isError(power_id)){
            System.err.print("权限标识重复! id:"+power_id+",name:"+power_name);
            System.exit(0);
        }
        Power obj = new Power();
        obj.setPower_pid(power_pid);
        obj.setPower_id(power_id);
        obj.setPower_name(power_name);
        obj.setView_url(view_url);
        obj.setIsexpand(isExpand);
        obj.setPv_sort(PowerSort.hmViwe.key);

        PLIST.add(obj);
        VLIST.add(obj);
        this.plist.add(obj);
        this.vlist.add(obj);
        return this;
    }

    /**
     * 加入一个节点
     *
     * @param power_pid 父健
     * @param power_id 主键
     * @param power_name 显示名
     * @param isExpand 是否展开
     * @return
     */
    public PD setEmptyNode(String power_pid, String power_id, String power_name, boolean isExpand) {
        if(this.isError(power_id)){
            System.err.print("权限标识重复! id:"+power_id+",name:"+power_name);
            System.exit(0);
        }
        Power obj = new Power();
        obj.setPower_pid(power_pid);
        obj.setPower_id(power_id);
        obj.setPower_name(power_name);
        obj.setIsexpand(isExpand);
        obj.setPv_sort(PowerSort.view.key);
        PLIST.add(obj);
        VLIST.add(obj);
        this.plist.add(obj);
        this.vlist.add(obj);
        return this;
    }
}
