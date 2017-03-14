package system.web;

import system.base.Sc.ScF;
import system.base.timework.TimeWorkInitService;
import system.web.init.JWebInitService;

/**
 *
 * @author wangchunzi
 */
public class IniTempMain {

    public void iniHMModel(String rpath, String ContextPath, ScF scf) {

        WebContext.setWebContext(
                new system.web.InitContainer(
                        rpath, ContextPath, new system.web.config.IniWebConfig().getWebConfig(scf.getMyClass())
                ).getWebContext());
        //初始化服务资源 
        InitLinkData x = new InitLinkData();
        x.doinitHMData(scf.getMyClass());

        //执行用户初始化接口实例
        JWebInitService js = new JWebInitService();
        js.ini(scf.getMyClass());
        
        //执行时间任务查找
        new TimeWorkInitService().ini(scf.getMyClass());
    }

}
