package system.web.file.engine;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import system.web.file.FI;
import system.web.file.FileModel;
import system.web.file.PartUp;

/**
 *
 * @author wangchunzi
 */
final public class PartEngine implements IFile {

    private final HttpServletRequest request;

    public PartEngine(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public FI upOne(Class<? extends FileModel> c) {
        return PartUp.upOne(request, c);
    }

    @Override
    public FI upOne(Class<? extends FileModel> c, String name) {
        return PartUp.upOne(request, c, name);
    }

    @Override
    public List<FI> upVast(Class<? extends FileModel> c) {
        return PartUp.upVast(request, c);
    }

    @Override
    public List<FI> upVast(Class<? extends FileModel> c, String fieldName) {
        return PartUp.upVast(request, c, fieldName);
    }
    
}
