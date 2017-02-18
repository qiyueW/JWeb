package system.web.filter.chain.config;

/**
 *
 * @author wangchunzi
 */
public abstract class ConfigurationFilter {
    
    /**
     * 表示一个节点(节点由数字、字母、下划线组成)。<p/>
     * 例:  <p/>
     * 方法使用: xx.xxx.xxxx.ClassName.your_method 其中xx表示一个节点，xxx表示一个节点。即节点间是用.来隔的！<p/>
     * 请求路径使用: /manager/user/dell   其中 manager一个节点，user一个节点<p/>
     */
    public final String ONE_NODE="[a-zA-Z0-9\\_]";
    
    /**
     * 表示不定节点(每个节点仅由数字、字母、下划线组成)。
     */
    public final String ALL_NODE="[a-zA-Z0-9/\\.\\_]";
    
    /**
     * 以x开始
     * @param x
     * @return 
     */
    protected String regex_startWithX(String x){
        return x+"[a-zA-Z0-9/\\.\\_]*";
    }
    
    /**
     * 以字符x结束
     * @param x
     * @return 
     */
    protected String regex_endWithX(String x){
        return "[a-zA-Z0-9/\\.\\_]*"+x;
    }
    /**
     * 包含 字符x
     * @param x
     * @return 
     */
    protected String regex_includeX(String x){
        return "[a-zA-Z0-9/\\.\\_]*"+x+"[a-zA-Z0-9/\\.\\_]*";
    }
    
    public abstract void configuration(LinkFilters lf);
    
}
