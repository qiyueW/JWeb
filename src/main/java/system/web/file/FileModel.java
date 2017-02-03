package system.web.file;

/**
 *
 * @author wangchunzi
 */
public abstract class FileModel extends FileObject{

    private FileConfig fc;
    
    public abstract void configuration(system.web.file.temp.FileConfig fc);

    FileConfig getFileConfig() {
        return fc;
    }

    void setFileConfig(FileConfig fc) {
        this.fc = fc;
    }

    final void startFI(FI fi) {
        fi.saveName = fc.process_fileName ? processName(fi.fileName) : fi.fileName;
        fi.path_saveName = fc.path_save + fi.saveName;
    }

    final boolean isError_toCheckType(final String contentType) {
        return isError_checkType(fc, contentType.toLowerCase());
    }
    
    final boolean isError_toSize(final long size) {
        return isError_checkSize(fc, size);
    }

    final boolean isError_toCheckSuffix(final String suffix) {
        return isError_checkSuffix(fc, suffix.toLowerCase());
    }
}
