package system.web.file;

/**
 *
 * @author wangchunzi
 */
public class PathTool {

    String toProcessPath(boolean islinux, String real_p1, String p2) {
        if (islinux) {
            return real_p1.endsWith("/") ? (real_p1 + p2).replace("//", "/") : (real_p1 + "/" + p2).replace("//", "/");
        }
        p2 = p2.replace("/", "\\");         //相对路径转为绝对路径
        return real_p1.endsWith("\\")
                ? (real_p1 + p2).replace("\\\\", "\\") //如果real_p1与\结束，则两路径直接相接
                : (real_p1 + "\\" + p2).replace("\\\\", "\\");//否则加入\
    }
}
