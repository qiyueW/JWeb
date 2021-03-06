package system.web.power.session;

/**
 * power in session data
 *
 * @author wangchunzi
 * @param <T>
 */
public class PISD<T> {

    public final T obj;
    public final String power[];

    public PISD(T obj, String power[]) {
        this.obj = obj;
        this.power = power;
    }

    public PISD(T obj, String powers) {
        this.obj = obj;
        this.power = powers == null || powers.isEmpty() ? new String[]{} : powers.split(",");
    }
}
