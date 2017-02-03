package system.web.filter.chain.config;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class ConfigurationFilterService {

    private final LinkFilters lfObject = new LinkFilters();

    public void iniUserConfig(Class c) {
        if (ConfigurationFilter.class.isAssignableFrom(c) && ConfigurationFilter.class != c) {
            try {
                ConfigurationFilter config = (ConfigurationFilter) c.newInstance();
                config.configuration(lfObject);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ConfigurationFilterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public LinkFilters getLinkFilters() {
        return this.lfObject;
    }
}
