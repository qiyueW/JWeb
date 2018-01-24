package system.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import system.base.str.WhereStr;

/**
 *
 * @author fly
 */
public class StringTool {

    public final static WhereStr OpenWhereStr() {
        return new WhereStr();
    }

    /**
     * 将字段串a,b,c 变成 字段串 'a','b','c'
     *
     * @param s 默认值为null或空，默认返回''
     * @return
     */
    public static final String replaceDToDDD(String s) {
        return null == s || s.isEmpty() ? "''" : "'" + s.replace(",", "','") + "'";
    }

    public static final String downloadFileName_zhcnCode(HttpServletRequest req, String downloadFilename) {
        try {
            if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            } else {
                downloadFilename = new String(downloadFilename.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException ex) {
            return "filename";
        }
        return downloadFilename;
    }
}
