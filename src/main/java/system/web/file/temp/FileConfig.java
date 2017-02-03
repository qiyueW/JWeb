package system.web.file.temp;

/**
 *
 * @author wangchunzi
 */
public class FileConfig {

    public String path_web_real = system.web.WebContext.getWebContext().WEB_PATH;
    public String path_save="fileup/";
    public String path_temp_real = "";
    public boolean process_fileName = true;
    public int fileSizeThreshold = 2 * 1024 * 1024;
    public long fileSize_max = 2 * 1024 * 1024;//(1mb=1024kb=1024*1024b)
    
    public String fileType_alloy="";
    public String fileType_reject="";
    public String fileNameSuffix_alloy="";
    public String fileNameSuffix_reject="";
    public String message_error_fileSize_max = "上传错误:文件超限制大小";
    public String message_error_fileNameSuffix = "上传错误:不是合法的文件后缀名";
    public String message_error_fileType = "上传错误:不是合法的文件类型";
    public String message_success_upload = "上传成功";
    public String message_error_upload = "上传失败";

}
