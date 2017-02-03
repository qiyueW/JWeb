package system.web.file.temp;

import java.io.File;
import system.web.WebContext;
import system.web.file.FileConfig;

/**
 *
 * @author wangchunzi
 */
public class IniPath {

    public void iniPath(FileConfig fc) {
        iniTool(fc.path_save_real);
        iniTool(fc.path_temp_real);
    }

    private void iniTool(String path) {
        if (null == path || path.isEmpty()) {
            return;
        }

        if (WebContext.getWebContext().islinux) {
            path = path.replace("//", "/");
            path = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
        } else {
            path = path.replace("/", "\\").replace("\\\\", "\\");
            path = path.endsWith("\\") ? path.substring(0, path.length() - 1) : path;
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

}
