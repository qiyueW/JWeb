package system.web.hm;

import system.base.annotation.M;

/**
 *
 * @author wangchunzi
 */
public abstract class HMTool {
    public String getMValueOrURL(M m) {
        return m.url().isEmpty() ? m.value() : m.url();
    }

    /**
     * 请求的后续。如果没有，则不设。如果有
     *
     * @param hz
     * @return
     */
    public String requestURL(String hurl, String murl, String hz) {
        if (!hurl.isEmpty()) {
            hurl = hurl.startsWith("/") ? hurl : "/" + hurl;
            hurl = hurl.endsWith("/") ? hurl.substring(0, hurl.length() - 1) : hurl;
        }
        murl = murl.startsWith("/") ? murl : "/" + murl;
        murl = murl.endsWith("/") ? murl.substring(0, murl.length() - 1) : murl;
        return hurl + murl + (null == hz ? "" : hz);
    }
}
