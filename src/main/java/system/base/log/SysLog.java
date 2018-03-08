package system.base.log;

/**
 *
 * @author wangchunzi
 */
//base.log.SysLog
public class SysLog {

    /**
     * 字符拼接容器
     */
    StringBuilder sb = new StringBuilder();
    /**
     * 初始值40
     */
    int count = 40;

    /**
     * 设置标题
     *
     * @param title String
     */
    public void setLogTitle(String title) {
        StringBuilder t1 = getX(count, "*");
        putLog((t1 + title + t1).substring(0, 80));
    }

    /**
     * 设置内容
     *
     * @param msg 内容
     * @return SysLog
     */
    public SysLog putLog(String msg) {
        sb.append("\n").append(msg);
        return this;
    }

    /**
     * 设置内容
     *
     * @param _t 退格多少个
     * @param msg 内容
     * @return SysLog
     */
    public SysLog putLog(int _t, String msg) {
        sb.append("\n").append(getX(_t, "\t")).append(msg);
        return this;
    }

    /**
     * 打印信息
     */
    public void println() {
        System.out.println(sb.toString());
    }

    /**
     * 生成count个字符串（拼接一起）
     *
     * @param count 多少个
     * @param x 某种字符串
     * @return StringBuilder 多个x字符串返回
     */
    private StringBuilder getX(int count, String x) {
        StringBuilder strX = new StringBuilder();
        for (int i = 0; i < count; i++) {
            strX.append(x);
        }
        return strX;
    }

}
