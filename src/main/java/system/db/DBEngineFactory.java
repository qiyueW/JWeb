package system.db;

import system.base.properties.PropertiesConfig;
import system.base.properties.PropertiesFactory;
import system.base.properties.PropertiesModel;
import system.db.config.CDBConfig;

/**
 *
 * @author wangchunzi
 */
public class DBEngineFactory {

    public static DBEngine getDBEngine(PropertiesModel model) {
        PropertiesConfig pc = new PropertiesConfig();
        model.configuration(pc);
        PropertiesFactory pf = new PropertiesFactory(pc);
        return new DBEngine((CDBConfig) pf.get());
    }

    public static DBEngine getDBEngine() {
        PropertiesConfig pc = new PropertiesConfig();
        PropertiesFactory pf = new PropertiesFactory(pc);
        return new DBEngine((CDBConfig) pf.get());
    }
}
