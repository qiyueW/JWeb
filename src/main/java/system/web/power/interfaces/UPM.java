package system.web.power.interfaces;


/**
 *
 * @author wangchunzi
 */
public interface UPM {
    
    public void ggSuccess(final String url,final system.web.JWeb jw);//无需要授权，无需登陆，无需口令......
    public void ggError(final String url,final system.web.JWeb jw);//无需要授权，无需登陆，无需口令......
    
    public void dlSuccess(final String url,final system.web.JWeb jw);//登陆权限检查
    public void dlError(final String url,final system.web.JWeb jw);
    
    public void sqNotLogin(final String url,final system.web.JWeb jw);
    public void sqSuccess(final String url,final system.web.JWeb jw);
    public void sqError(final String url,final system.web.JWeb jw);
    
    /**
     * 口令处理。(    如果想不通过，return true)
     * @param url
     * @param jw
     * @return 
     */
    public boolean kl_endByTrue(final String url,final system.web.JWeb jw);
    public void klError(final String url,final system.web.JWeb jw);
    
    public boolean start_endByTrue(final String url,final system.web.JWeb jw);
    public void end(final String url,final system.web.JWeb jw);
    
}
