package system.web.file.engine;

import java.util.List;
import system.web.file.FI;
import system.web.file.FileModel;

/**
 *
 * @author wangchunzi
 */
public interface IFile {

    public FI upOne(Class<? extends FileModel> c);

    public FI upOne(Class<? extends FileModel> c, final String name);

    public List<FI> upVast(Class<? extends FileModel> c);

    public List<FI> upVast(Class<? extends FileModel> c, final String fieldName);
}
