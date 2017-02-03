package system.web.file;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wangchunzi
 */
abstract class FileObject {

    private static int i = 0;

    final static boolean isError_checkType(FileConfig fc, final String contentType) {
        return (!fc.fileType_alloy.contains(contentType) && fc.fileType_alloy.size() > 0)//在非空允许集中，如果未发现此元素，返回错误true
                || fc.fileType_reject.contains(contentType);//在拒绝集合中，直接返回错误true
    }

    final static boolean isError_checkSuffix(FileConfig fc, final String suffix) {
        return (!fc.fileNameSuffix_alloy.contains(suffix) && fc.fileNameSuffix_alloy.size() > 0)//在非空允许集中，如果未发现此元素，返回错误true
                || fc.fileNameSuffix_reject.contains(suffix);//在拒绝集合中的，直接返回错误true
    }

    final static boolean isError_checkSize(FileConfig fc, final long size) {
        if (fc.fileSize_max == -1) {
            return false;
        }
        return size > fc.fileSize_max;
    }

    final static String processName(final String name) {
        return getIID() + name;
    }

    private static String getIID() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date())
                + new DecimalFormat("0000000").format(getI());
    }

    synchronized private static int getI() {
        i = i >= 9999999 ? 1 : ++i;
        return i;
    }
}
