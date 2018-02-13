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

    /**
     * 取得操作数据库的实例
     *
     * @param model PropertiesModel
     * @return DBEngine
     */
    public static DBEngine getDBEngine(PropertiesModel model) {
        PropertiesConfig pc = new PropertiesConfig();
        model.configuration(pc);
        PropertiesFactory pf = new PropertiesFactory(pc);
        return new DBEngine((CDBConfig) pf.get());
    }

    /**
     *  取得操作数据库的实例
     * @return  DBEngine
     */
    public static DBEngine getDBEngine() {
        PropertiesConfig pc = new PropertiesConfig();
        PropertiesFactory pf = new PropertiesFactory(pc);
        return new DBEngine((CDBConfig) pf.get());
    }
}
