package system.web.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author wangchunzi
 */
final public class PartUp {

    /**
     * 默认找到第一个文件进行操作
     *
     * @param req
     * @param c
     * @return
     */
    public static FI upOne(HttpServletRequest req, Class<? extends FileModel> c) {
        FileModel fm = FileModelData.get(c);
        FI fi = new FI();
        try {
            Iterator<Part> iter = req.getParts().iterator();
            if (iter.hasNext()) {
                Part item = iter.next();
                fi = new FI();
                doUpOne(item, fm, fi);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(PartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        FileModel fm = FileModelData.get(c);
        FI fi = new FI();
        try {
            doUpOne(req.getPart(name), fm, fi);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(PartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fi;
    }

    public static List<FI> upVast(HttpServletRequest req, Class<? extends FileModel> c) {
        FileModel fm = FileModelData.get(c);
        List<FI> f = new ArrayList();
        FI fi;
        try {
            Iterator<Part> iter = req.getParts().iterator();
            while (iter.hasNext()) {
                Part item = iter.next();
                fi = new FI();
                doUpOne(item, fm, fi);
                f.add(fi);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(PartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    public static List<FI> upVast(HttpServletRequest req, Class<? extends FileModel> c, final String fieldName) {

        FileModel fm = FileModelData.get(c);
        List<FI> f = new ArrayList();
        FI fi;
        try {
            Iterator<Part> iter = req.getParts().iterator();
            while (iter.hasNext()) {
                Part item = iter.next();
                if (item.getSubmittedFileName().equalsIgnoreCase(fieldName)) {
                    fi = new FI();
                    doUpOne(item, fm, fi);
                    f.add(fi);
                }
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(PartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    private static void doUpOne(Part item, FileModel fm, FI fi) {
        fi.fileName = item.getSubmittedFileName();
        if (fm.isError_toCheckType(item.getContentType())) {
            fi.message = fm.getFileConfig().message_error_fileType;
            return;
        }
        if (fm.isError_toCheckSuffix(fi.fileName.substring(fi.fileName.lastIndexOf(".") + 1))) {
//            System.out.println(fi.fileName.lastIndexOf("."));
            fi.message = fm.getFileConfig().message_error_fileNameSuffix;
            return;
        }
        if (fm.isError_toSize(item.getSize())) {
            fi.message = fm.getFileConfig().message_error_fileSize_max;
//            System.out.println(item.getSize()+"//"+fm.getFileConfig().fileSize_max);
            return;
        }
        fm.startFI(fi);
        if (fm.getFileConfig().fileSizeThreshold <= item.getSize()) {
            try {
                item.write(fm.getFileConfig().path_save_real + fi.saveName);
            } catch (IOException ex) {
                fi.message = fm.getFileConfig().message_error_upload;
                return;
            }
            fi.isok = true;
            fi.message = fm.getFileConfig().message_success_upload;
            return;
        }
        FileOutputStream fos = null;
        try {
            InputStream is = item.getInputStream();
            byte[] b = new byte[is.available()];
            is.read(b);
            fos = new FileOutputStream(fm.getFileConfig().path_save_real + fi.saveName);
            fos.write(b);
            fos.flush();
            fi.isok = true;
            fi.message = fm.getFileConfig().message_success_upload;
        } catch (IOException ex) {
            fi.message = fm.getFileConfig().message_error_upload;
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(PartUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
