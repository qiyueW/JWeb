package system.base.str;

/**
 *
 * @author fly
 */
public class WhereStr {

    final private StringBuilder sb = new StringBuilder();

    public String getSQL() {
        return sb.toString();
    }

    public WhereStr putAND(String tj) {
        if (null == tj || tj.isEmpty()) {
            return this;
        }
        sb.append(sb.length() == 0 ? tj : " AND " + tj);
        return this;
    }

    public WhereStr putOR(String tj) {
        if (null == tj || tj.isEmpty()) {
            return this;
        }
        sb.append(sb.length() == 0 ? tj : " OR " + tj);
        return this;
    }

    public void resetObject() {
        sb.setLength(0);
    }
}
