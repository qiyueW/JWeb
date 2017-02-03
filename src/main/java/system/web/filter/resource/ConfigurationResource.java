package system.web.filter.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wangchunzi
 */
public abstract class ConfigurationResource {

    public abstract boolean isStaticResource(final String url, HttpServletRequest req, HttpServletResponse resp);
}
