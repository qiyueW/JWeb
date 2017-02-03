package system.web.hm.model;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wangchunzi
 */
public class LinkValueModel {

    HttpServletRequest req;
    HttpServletResponse resp;
    Map map;

    public LinkValueModel(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        map.putAll(map);
    }
    
    
}
