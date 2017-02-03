package system.web.file;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
final public class FileConfig {

    public final String path_web_real;
    public final String path_save;
    public final String path_save_real;
    public final String path_temp_real;
    public final boolean process_fileName;
    public final int fileSizeThreshold;
    public final long fileSize_max;//(1mb=1024kb=1024*1024b)

    public final Set<String> fileType_alloy;
    public final Set<String> fileType_reject;
    public final Set<String> fileNameSuffix_alloy;
    public final Set<String> fileNameSuffix_reject;
    public final String message_error_fileSize_max;
    public final String message_error_fileNameSuffix;
    public final String message_error_fileType;
    public final String message_success_upload;
    public final String message_error_upload;

    public FileConfig(system.web.file.temp.FileConfig fc) {
        this.path_web_real = fc.path_web_real;
        this.path_save = fc.path_save;
        this.path_temp_real = fc.path_temp_real;
//        this.path_save_real = fc.path_web_real + fc.path_save;
        this.path_save_real = new PathTool().toProcessPath(WebContext.getWebContext().islinux, fc.path_web_real, fc.path_save);
        
        this.fileSize_max = fc.fileSize_max;
        this.process_fileName = fc.process_fileName;

        this.fileNameSuffix_alloy = fc.fileNameSuffix_alloy.isEmpty() ? new HashSet() : new HashSet(Arrays.asList(fc.fileNameSuffix_alloy.toLowerCase().split(",")));
        this.fileNameSuffix_reject = fc.fileNameSuffix_reject.isEmpty() ? new HashSet() : new HashSet(Arrays.asList(fc.fileNameSuffix_reject.toLowerCase().split(",")));
        this.fileType_alloy = fc.fileType_alloy.isEmpty() ? new HashSet() : new HashSet(Arrays.asList(fc.fileType_alloy.toLowerCase().split(",")));
        this.fileType_reject = fc.fileType_reject.isEmpty() ? new HashSet() : new HashSet(Arrays.asList(fc.fileType_reject.toLowerCase().split(",")));

        this.fileSizeThreshold = fc.fileSizeThreshold;
        this.message_error_fileNameSuffix = fc.message_error_fileNameSuffix;
        this.message_error_fileSize_max = fc.message_error_fileSize_max;
        this.message_error_fileType = fc.message_error_fileType;

        this.message_error_upload = fc.message_error_upload;
        this.message_success_upload = fc.message_success_upload;
        fc = null;
    }
}
