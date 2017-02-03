package system.base.log;

/**
 *
 * @author wangchunzi
 */
//base.log.SysLog
public class SysLog {

    StringBuilder sb = new StringBuilder();
    int count = 40;

    public void setLogTitle(String title) {
        StringBuilder t1 = getX(count, "*");
        putLog((t1 + title + t1).substring(0, 80));
    }

    public SysLog putLog(String msg) {
        sb.append("\n").append(msg);
        return this;
    }
    
    public SysLog putLog(int _t, String msg) {
        sb.append("\n").append(getX(_t, "\t")).append(msg);
        return this;
    }

    public void println() {
        System.out.println(sb.toString());
    }

    private StringBuilder getX(int count, String x) {
        StringBuilder strX = new StringBuilder();
        for (int i = 0; i < count; i++) {
            strX.append(x);
        }
        return strX;
    }

}
