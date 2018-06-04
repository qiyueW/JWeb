package system.web.servlet;

import system.web.filter.chain.FilterModel;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author wangchunzi
 */
public class ServletModel {

    /**
     *
     * @param fmTop 执行顶部过滤链
     * @param vm 执行校验
     * @param fmCenter 执行中部过滤链
     * @param fmButtom 执行底部过滤链
     * @param instruction 指令1-15.
     */
    public ServletModel(
            FilterModel[] fmTop, ValidateModel[] vm, FilterModel[] fmCenter, FilterModel[] fmButtom, int instruction
    ) {
        this.fmTop = fmTop;
        this.fmCenter = fmCenter;
        this.vm = vm;
        this.fmButtom = fmButtom;
        this.instruction = instruction;
        if (null != fmTop) {
            for (FilterModel fm : fmTop) {
                System.out.println(fm.getClass().getName());
            }
        }
        if (null != fmCenter) {
            for (FilterModel fm : fmCenter) {
                System.out.println(fm.getClass().getName());
            }
        }
        if (null != fmButtom) {
            for (FilterModel fm : fmButtom) {
                System.out.println(fm.getClass().getName());
            }
        }
    }
    public final FilterModel[] fmTop;
    public final ValidateModel[] vm;
    public final FilterModel[] fmCenter;
    public final FilterModel[] fmButtom;

    /**
     * 原计算出来的指令，+8，则代表以静态方法执行用户请求。
     */
    public final int instruction;

}
