package system.web.filter.chain;

import system.web.JWeb;

/**
 *
 * @author wangchunzi
 */
public final class FilterEngine {

    /**
     * 至少有一个过滤对象。
     *
     * @param jw
     * @param fm
     * @return
     */
    public final boolean doEngine_error(JWeb jw, FilterModel[] fm) {
        int topindex = 0;
        switch (fm.length) {
            case 20:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 19:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 18:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 17:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 16:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 15:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 14:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 13:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 12:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 11:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 10:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 9:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 8:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 7:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 6:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 5:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 4:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 3:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 2:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
            case 1:
                if (jw.isEndFilter()) {
                    return true;
                }
                fm[topindex++].doFilter(jw);
                return false;
            case 0:
                return false;
            default: {
                for (; topindex < fm.length; topindex++) {
                    if (jw.isEndFilter()) {
                        return true;
                    }
                    fm[topindex++].doFilter(jw);
                }
                return false;
            }
        }
    }
}
