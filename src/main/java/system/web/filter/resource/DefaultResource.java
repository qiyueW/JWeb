package system.web.filter.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wangchunzi
 */
public final class DefaultResource extends ConfigurationResource {

    @Override
    public boolean isStaticResource(final String url, HttpServletRequest req, HttpServletResponse resp) {
        return (url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".ico")
                || url.endsWith(".png")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".html")
                || url.endsWith(".jsp")
                || url.endsWith(".jspf")
                || url.endsWith(".ico"));
    }

}
