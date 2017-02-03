package system.web.config;

/**
 *
 * @author wangchunzi
 */
public class WebResourceConfig {

    public final boolean open_resource_manager;
    public final String[] alloyResource;
    public final String[] rejectResource;
    
    public WebResourceConfig(boolean open_resource_manager, String alloyResource, String rejectResource) {
        this.open_resource_manager = open_resource_manager;
        this.alloyResource = (null == alloyResource || alloyResource.trim().isEmpty())
                ? null
                : alloyResource.trim().split(",");
        this.rejectResource = (null == rejectResource || rejectResource.trim().isEmpty())
                ? null
                : rejectResource.trim().split(",");
    }
}
