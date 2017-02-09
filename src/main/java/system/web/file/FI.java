package system.web.file;

/**
 *
 * @author ik
 */
public class FI {

    /**
     * 上传的文件名
     */
    public String fileName;
    /**
     * 加工后的文件名
     */
    public String saveName;
    /**
     * 保证的路径(相对项目的绝对路径)
     */
    public String path_saveName;
    /**
     * 是否上传成功
     */
    public boolean isok = false;
    /**
     * 信息
     */
    public String message;
    
    @Override
    public String toString() {
        return new StringBuilder()
                .append("{\"fileName\":\"").append(fileName)
                .append("\",\"saveName\":\"").append(saveName)
                .append("\",\"path_fileName\":\"").append(path_saveName)
                .append("\",\"isok\":\"").append(isok)
                .append("\",\"message\":\"").append(message).append("\"}").toString();
    }
}
