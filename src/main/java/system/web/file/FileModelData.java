package system.web.file;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.web.file.temp.IniPath;

/**
 *
 * @author wangchunzi
 */
final class FileModelData {

    private static final Map<Class, FileModel> map = new HashMap();

    static FileModel get(Class<? extends FileModel> c) {
        FileModel get = map.get(c);
        return null == get ? ini(c) : get;
    }
    
    private static FileModel ini(Class<? extends FileModel> c) {
        try {
            FileModel fm = c.newInstance();
            system.web.file.temp.FileConfig fc = new system.web.file.temp.FileConfig();
            fm.configuration(fc);
            
            fm.setFileConfig(new system.web.file.FileConfig(fc));
            new IniPath().iniPath(fm.getFileConfig());
            map.put(c, fm);
            return fm;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FileModelData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
