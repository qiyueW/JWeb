package system.web.filter.chain;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.web.XY_Instruction;

/**
 *
 * @author wangchunzi
 */
public class InitJWFilterModel {

    system.base.log.SysLog log = new system.base.log.SysLog();

    private final Set<Class> grobalFilterClass_0 = new LinkedHashSet();
    private final Set<Class> grobalFilterClass_1 = new LinkedHashSet();
    private final Set<Class> grobalFilterClass_2 = new LinkedHashSet();

    private final Set<Class> headFilterClass_0 = new LinkedHashSet();
    private final Set<Class> headFilterClass_1 = new LinkedHashSet();
    private final Set<Class> headFilterClass_2 = new LinkedHashSet();
//    private final Set<Class> methodFilterClass_0 = new LinkedHashSet();
//    private final Set<Class> methodFilterClass_1 = new LinkedHashSet();
//    private final Set<Class> methodFilterClass_2 = new LinkedHashSet();

    private int top = 0;
    private int center = 0;
    private int buttom = 0;

    public int getFilterInstruction() {
        return top + center + buttom;
    }

    FilterData fd = new FilterData();

    synchronized public void iniFilterModel_H(Class[] c) {
        this.findFilterModel(c, headFilterClass_0, headFilterClass_1, headFilterClass_2);

    }

    public void clear() {
        this.headFilterClass_0.clear();
        this.headFilterClass_1.clear();
        this.headFilterClass_2.clear();

        this.grobalFilterClass_0.clear();
        this.grobalFilterClass_1.clear();
        this.grobalFilterClass_2.clear();
        this.top = 0;
        this.center = 0;
        this.buttom = 0;
    }

    public void clearM() {
//        this.headFilterClass_0.clear();
//        this.headFilterClass_1.clear();
//        this.headFilterClass_2.clear();
        this.grobalFilterClass_0.clear();
        this.grobalFilterClass_1.clear();
        this.grobalFilterClass_2.clear();
        this.top = 0;
        this.center = 0;
        this.buttom = 0;
    }

    public void iniGrobalConfiguration(Set<Class> c) {
        this.findFilterModel(c, grobalFilterClass_0, grobalFilterClass_1, grobalFilterClass_2);
    }

    public void iniFilterModel_M(Class[] c) {
        //拿类容器的数据来最初赋值

        this.grobalFilterClass_0.addAll(headFilterClass_0);
        this.grobalFilterClass_1.addAll(headFilterClass_1);
        this.grobalFilterClass_2.addAll(headFilterClass_2);

//        this.methodFilterClass_0.addAll(headFilterClass_0);
//        this.methodFilterClass_1.addAll(headFilterClass_1);
//        this.methodFilterClass_2.addAll(headFilterClass_2);
        if (null != c) {
            //计算方法配置的FilterModel集合
            this.findFilterModel(c, grobalFilterClass_0, grobalFilterClass_1, grobalFilterClass_2);
            //设置指令。0表示空指令。
            if (grobalFilterClass_0.size() > 0) {
                this.top = XY_Instruction.FILTERMODEL_TOP;
            }
            if (grobalFilterClass_1.size() > 0) {
                this.center = XY_Instruction.FILTERMODEL_CENTER;
            }
            if (grobalFilterClass_2.size() > 0) {
                this.buttom = XY_Instruction.FILTERMODEL_BUTTOM;
            }
        }
    }

    /**
     * 获得顶部过滤执行器
     *
     * @return
     */
    public FilterModel[] getHMFilterModel_TOP() {
        if (this.top == 0) {
            return null;
        }

        //初始化实例容器
        FilterModel[] fm = new FilterModel[grobalFilterClass_0.size()];

        //采用单例设计。按key-value,拿到类的关联对象。set是集中，所以采用循化来取得每类的实例
        int i = 0;
        for (Class forc : grobalFilterClass_0) {
            fm[i++] = fd.get(forc);
        }
        return fm;
    }

    /**
     * 获得中部过滤执行器
     *
     * @return
     */
    public FilterModel[] getHMFilterModel_CENTER() {
        if (this.center == 0) {
            return null;
        }
        //初始化实例容器
        FilterModel[] fm = new FilterModel[grobalFilterClass_1.size()];

        //采用单例设计。按key-value,拿到类的关联对象。set是集中，所以采用循化来取得每类的实例
        int i = 0;
        for (Class forc : grobalFilterClass_1) {
            fm[i++] = fd.get(forc);
        }
        return fm;
    }

    /**
     * 获得底部过滤执行器
     *
     * @return
     */
    public FilterModel[] getHMFilterModel_BUTTOM() {
        if (this.buttom == 0) {
            return null;
        }
        //初始化实例容器
        FilterModel[] fm = new FilterModel[grobalFilterClass_2.size()];

        //采用单例设计。按key-value,拿到类的关联对象。set是集中，所以采用循化来取得每类的实例
        int i = 0;
        for (Class forc : grobalFilterClass_2) {
            fm[i++] = fd.get(forc);
        }
        return fm;
    }

    /**
     * 工具类（主要是识别FilterModel类还是FilterModels类。后者需要加工处理）
     * <p>
     * 将[]集合的class，统一识别为FilterModel,并放入result数组中
     *
     * @param resource
     * @param result_0,Set<Class> result_1
     */
    private void findFilterModel(Set<Class> resource, Set<Class> result_0, Set<Class> result_1, Set<Class> result_2) {
        //用来存入用户配置列表FilterModel(继承FilterModels的实例)

//        System.out.println();
        Set<Class> temp = new LinkedHashSet();

        //对源集合进行每个个体的判断、处理
        for (Class c : resource) {
            //如果是FilterModel的子类
            if (FilterModel.class.isAssignableFrom(c)) {
                //把此类放入FilterModel数据中心中
                //通过内部的处理成KEY-VALUE集合，class为KEY,class的实例为value

                fd.add(c);

                //判断是此FilerModel位置
                //前置：加入result_0集合
                //中置：加入result_1集合
                //后置：加入result_2集合
                this.add(c, result_0, result_1, result_2);

            } //如是FilterModels类的子类
            else if (FilterModels.class.isAssignableFrom(c)) {
                try {
                    //实例一个对象。
                    FilterModels fms = (FilterModels) c.newInstance();
                    //将用户配置的FilterModel放入temp变量集合中
                    fms.put(temp);
                    //对用户配置的FilterModel集合进行每个个体的判断、处理
                    for (Class tempc : temp) {
                        fd.add(tempc);
                        //判断是此FilerModel位置
                        //前置：加入result_0集合
                        //中置：加入result_1集合
                        //后置：加入result_2集合
                        this.add(tempc, result_0, result_1, result_2);
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(InitJWFilterModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * 工具类（主要是识别FilterModel类还是FilterModels类。后者需要加工处理）
     * <p>
     * 将[]集合的class，统一识别为FilterModel,并放入result数组中
     *
     * @param resource
     * @param result_0,Set<Class> result_1
     */
    private void findFilterModel(Class[] resource, Set<Class> result_0, Set<Class> result_1, Set<Class> result_2) {
        //用来存入用户配置列表FilterModel(继承FilterModels的实例)

//        System.out.println();
        Set<Class> temp = new LinkedHashSet();

        //对源集合进行每个个体的判断、处理
        for (Class c : resource) {
            //如果是FilterModel的子类
            if (FilterModel.class.isAssignableFrom(c)) {
                //把此类放入FilterModel数据中心中
                //通过内部的处理成KEY-VALUE集合，class为KEY,class的实例为value

                fd.add(c);

                //判断是此FilerModel位置
                //前置：加入result_0集合
                //中置：加入result_1集合
                //后置：加入result_2集合
                this.add(c, result_0, result_1, result_2);

            } //如是FilterModels类的子类
            else if (FilterModels.class.isAssignableFrom(c)) {
                try {
                    //实例一个对象。
                    FilterModels fms = (FilterModels) c.newInstance();
                    //将用户配置的FilterModel放入temp变量集合中
                    fms.put(temp);
                    //对用户配置的FilterModel集合进行每个个体的判断、处理
                    for (Class tempc : temp) {
                        fd.add(tempc);
                        //判断是此FilerModel位置
                        //前置：加入result_0集合
                        //中置：加入result_1集合
                        //后置：加入result_2集合
                        this.add(tempc, result_0, result_1, result_2);
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(InitJWFilterModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void add(Class c, Set<Class> result_TOP, Set<Class> result_CENTER, Set<Class> result_BUTTOM) {

        FilterModel fm = fd.get(c);
        switch (fm.getLocation()) {
            case FilterModel.LOCATION_TOP:
                result_TOP.add(c);
                this.top = XY_Instruction.FILTERMODEL_TOP;
                break;
            case FilterModel.LOCATION_CENTER:
                result_CENTER.add(c);
                this.center = XY_Instruction.FILTERMODEL_CENTER;
                break;
            case FilterModel.LOCATION_BUTTOM:
                result_BUTTOM.add(c);
                this.buttom = XY_Instruction.FILTERMODEL_BUTTOM;
                break;
        }
    }

}
