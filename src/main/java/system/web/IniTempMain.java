package system.web;

import system.base.Sc.ScF;
import system.base.timework.TimeWorkInitService;
import system.web.init.JWebInitService;
import system.web.power.InitIPD;
import system.web.power.InitPowerCode;
import system.web.power.InitUPM;

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
        
        //初始化用户权限过滤处理
        new InitUPM(scf.getMyClass());
        //执行url与权限类型相关信息关联起
        new InitPowerCode().initPowerCode(scf.getMyClass());
        
        new InitIPD(scf.getMyClass());
    }

}
