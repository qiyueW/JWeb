package system.web;

/**
 * web框架启动时，用于数据的收集，然后交给GlobalContainer。然后销毁
 *
 * @author wangchunzi
 */
public class InitContainer {

    public String WEB_INF_Path;
    public String WEB_Path;
    public boolean islinux;
    public String ContextPath;
    private String rpath;

    public system.web.config.WebConfig config;

    public InitContainer(String rpath, String ContextPath, system.web.config.WebConfig config) {
        this.rpath = rpath;
        this.ContextPath = ContextPath;
        this.doIniPath();
        this.config = config;
    }

    public WebContext getWebContext() {

        WebContext webContext = new WebContext(ContextPath, WEB_Path, WEB_INF_Path, islinux, config);
        system.base.log.SysLog log = new system.base.log.SysLog();
        log.setLogTitle("初始化jweb容器");
        log.putLog(1, "路径-ContextPath ： " + ContextPath)
                .putLog(1, "路径-WEB_Path    ： " + WEB_Path)
                .putLog(1, "路径-WEB_INF_Path： " + WEB_INF_Path)
                .putLog(1, "部署环境         ： " + (islinux ? "linux(没有盘符,物理路径以/为根)" : "windows(有盘符，物理路径以盘符开始)"));
        log.println();
        return webContext;
    }

    /**
     * 在filter启动时，进行项目路径的初始化
     *
     * @param filterconfig
     */
    private void doIniPath() {
        if (!ContextPath.isEmpty()) {
            ContextPath = "/" + ContextPath.replace("/", "");
        }

        if (rpath.startsWith("/")) {
            rpath = rpath.endsWith("/") ? rpath : rpath + "/";
            WEB_Path = rpath;
            WEB_INF_Path = rpath + "WEB-INF/";
            islinux = true;
        } else {
            rpath = rpath.endsWith("\\") ? rpath : rpath + "\\";
            WEB_Path = rpath;
            WEB_INF_Path = rpath + "WEB-INF\\";
            islinux = false;
        }
    }
}
