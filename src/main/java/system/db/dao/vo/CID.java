package system.db.dao.vo;

/**
 *
 * @author wangchunzi
 */
public class CID {

    public final Class c;
    public final String id;

    public CID(Class c, String id) {
        this.c = c;
        this.id = id;
    }

    public CID(Class c) {
        this.c = c;
        this.id = null;
    }
}
