package system.web.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

/**
 *
 * @author wangchunzi
 */
final public class FileUp {

    /**
     * 默认找到第一个文件进行操作
     *
     * @param req
     * @param c
     * @return
     */
    public static FI upOne(HttpServletRequest req, Class<? extends FileModel> c) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return null;
        }
        FileModel fm = FileModelData.get(c);
        FI fi = new FI();
        doUpOne(findFile(getFileItems(req, fm)), fm, fi);
        return fi;
    }

    /**
     * 找到指定name的文件进行操作
     *
     * @param req
     * @param c
     * @param name
     * @return
     */
    public static FI upOne(HttpServletRequest req, Class<? extends FileModel> c, final String name) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return null;
        }
        FileModel fm = FileModelData.get(c);
        FI fi = new FI();
        doUpOne(findFile(getFileItems(req, fm), name), fm, fi);
        return fi;
    }

    public static List<FI> upVast(HttpServletRequest req, Class<? extends FileModel> c) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return null;
        }
        FileModel fm = FileModelData.get(c);
        List<FI> f = new ArrayList();
        FI fi;
        Iterator<FileItem> iter = getFileItems(req, fm).iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField() && item.getName().lastIndexOf(".") != -1) {
                fi = new FI();
                doUpOne(item, fm, fi);
                f.add(fi);
            }
        }
        return f;
    }

    public static List<FI> upVast(HttpServletRequest req, Class<? extends FileModel> c, final String fieldName) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return null;
        }
        FileModel fm = FileModelData.get(c);
        List<FI> f = new ArrayList();
        FI fi;
        Iterator<FileItem> iter = getFileItems(req, fm).iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField() && item.getName().lastIndexOf(".") != -1 && item.getFieldName().equalsIgnoreCase(fieldName)) {
                fi = new FI();
                doUpOne(item, fm, fi);
                f.add(fi);
            }
        }
        return f;
    }

    private static void doUpOne(FileItem item, FileModel fm, FI fi) {
        fi.fileName = item.getFieldName();//item.getName();
        System.out.println("============================"+item.getFieldName()+"//"+item.getName()+"//"+item.getString());
        if (fm.isError_toCheckType(item.getContentType())) {
            fi.message = fm.getFileConfig().message_error_fileType;
            return;
        }
        if (fm.isError_toCheckSuffix(fi.fileName.substring(fi.fileName.lastIndexOf(".") + 1))) {
            System.out.println(fi.fileName.lastIndexOf("."));
            fi.message = fm.getFileConfig().message_error_fileNameSuffix;
            return;
        }
        if (fm.isError_toSize(item.getSize())) {
            fi.message = fm.getFileConfig().message_error_fileSize_max;
            return;
        }
        fm.startFI(fi);
        try {
            item.write(new File(fm.getFileConfig().path_save_real + fi.saveName));
            fi.isok = true;
            fi.message = fm.getFileConfig().message_success_upload;
            return;
        } catch (Exception ex) {
            Logger.getLogger(FileUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        fi.message = fm.getFileConfig().message_error_upload;
    }
    private static final Map<String, DiskFileItemFactory> map = new HashMap();

    private static List<FileItem> getFileItems(HttpServletRequest req, FileModel fm) {
        try {
            DiskFileItemFactory factory = map.get(fm.getFileConfig().path_temp_real);
//            org.apache.commons.fileupload.servlet.FileCleanerCleanup
            if (null == factory) {
                FileCleaningTracker fileCleaningTracker
                        = FileCleanerCleanup.getFileCleaningTracker(req.getServletContext());
                factory = new DiskFileItemFactory(
                        fm.getFileConfig().fileSizeThreshold, fm.getFileConfig().path_temp_real.isEmpty() //缓存路径为这，采用默认路径
                        ? (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir")
                        : new File(fm.getFileConfig().path_temp_real)
                );
                factory.setFileCleaningTracker(fileCleaningTracker);
                map.put(fm.getFileConfig().path_temp_real, factory);
            }
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");

            return upload.parseRequest(req);
        } catch (FileUploadException ex) {
            Logger.getLogger(FileUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static FileItem findFile(List<FileItem> items) {
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField() && item.getName().lastIndexOf(".") != -1) {
                return item;
            }
        }
        return null;
    }

    private static FileItem findFile(List<FileItem> items, String fieldName) {
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField() && item.getName().lastIndexOf(".") != -1 && fieldName.equalsIgnoreCase(item.getFieldName())) {
                return item;
            }
        }
        return null;
    }

}
