package system.web.power.session;

import system.web.JWeb;
import system.web.power.PDK;

/**
 *
 * @author wangchunzi
 */
final public class Login {
    /**
     * 登陆
     * @param jw jweb对象
     * @param key session会话key
     * @param obj 用户对话
     * @param power 用户权限——字符串1维数组(String[])
     */
    final static public void login(final JWeb jw,final Object obj,final String[] power,final String key){
        jw.session.setAttribute(key, new PISD(obj,power));
    }
    /**
     * 登陆(session会话key采用默认的 PDK.SESSION_DEFAULT_KEY)
     * @param jw jweb对象
     * @param obj 用户对话
     * @param power 用户权限——字符串1维数组(String[])
     */
    final static public void login(final JWeb jw,final Object obj,final String[] power){
        jw.session.setAttribute(PDK.SESSION_DEFAULT_KEY, new PISD<>(obj,power));
    }
    
    final static public void reset(final JWeb jw,final Object userInfo,final String[] power,final String key){
        Object obj=jw.session.getAttribute(key);
        if(null==obj)return;
        jw.session.removeAttribute(key);//移除旧的
        jw.session.setAttribute(key, new PISD(userInfo,power));//添加新的
    }
    final static public void reset(final JWeb jw,final Object userInfo,final String[] power){
        Object obj=jw.session.getAttribute(PDK.SESSION_DEFAULT_KEY);
        if(null==obj)return;
        jw.session.removeAttribute(PDK.SESSION_DEFAULT_KEY);//移除旧的
        jw.session.setAttribute(PDK.SESSION_DEFAULT_KEY, new PISD(userInfo,power));//添加新的
    }
    
    final static public void out(final JWeb jw,final String key){
        Object obj=jw.session.getAttribute(key);
        if(null==obj)return ;
        jw.session.removeAttribute(key);
    }
    final static public void out(final JWeb jw){
        Object obj=jw.session.getAttribute(PDK.SESSION_DEFAULT_KEY);
        if(null==obj)return ;
        jw.session.removeAttribute(PDK.SESSION_DEFAULT_KEY);
    }
    
    final static public String[] getUserPower(final JWeb jw,final String sessionKey){
        Object obj= jw.session.getAttribute(sessionKey);
        if(null==obj)return new String[]{};
        return ((PISD)obj).power;
    }
    final static public String[] getUserPower(final JWeb jw){
        Object obj= jw.session.getAttribute(PDK.SESSION_DEFAULT_KEY);
        if(null==obj)return new String[]{};
        return ((PISD)obj).power;
    }
    
    final static public <T>T getUserInfo(T t,final JWeb jw,final String sessionKey){
        Object obj= jw.session.getAttribute(sessionKey);
        if(null==obj)return null;
        return (T)((PISD)obj).obj;
    }
    final static public <T>T getUserInfo(T t,final JWeb jw){
        Object obj= jw.session.getAttribute(PDK.SESSION_DEFAULT_KEY);
        if(null==obj)return null;
        return (T)((PISD)obj).obj;
    }
    
}
