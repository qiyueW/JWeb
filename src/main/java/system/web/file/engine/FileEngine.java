package system.web.file.engine;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import system.web.file.FI;
import system.web.file.FileModel;
import system.web.file.FileUp;

/**
 *
 * @author wangchunzi
 */
final public class FileEngine implements IFile {

    private final HttpServletRequest request;

    public FileEngine(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public FI upOne(Class<? extends FileModel> c) {
        return FileUp.upOne(request, c);
    }

    @Override
    public FI upOne(Class<? extends FileModel> c, String name) {
        return FileUp.upOne(request, c, name);
    }

    @Override
    public List<FI> upVast(Class<? extends FileModel> c) {
        return FileUp.upVast(request, c);
    }

    @Override
    public List<FI> upVast(Class<? extends FileModel> c, String fieldName) {
        return FileUp.upVast(request, c, fieldName);
    }
    
}
