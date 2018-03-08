package system.base;

import java.math.BigDecimal;

/**
 *
 * @author wangchunzi
 */
final public class MathTool {

    /**
     * 对x进行小数点精确。
     *
     * @param x double
     * @param i int
     * @return double
     */
    final public static double scale(double x, int i) {
        return BigDecimal.valueOf(x).setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相加
     *
     * @param x double
     * @param x2 double
     * @param scale int 精确几位小数点。
     * @return double
     */
    final static double add(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.add(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相减
     *
     * @param x double
     * @param x2 double
     * @param scale int 精确几位小数点。
     * @return double
     */
    final static double subtract(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.subtract(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相乘
     *
     * @param x double
     * @param x2 double
     * @param scale int 精确几位小数点。
     * @return double
     */
    final static double multiply(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        BigDecimal m = bd.multiply(bd2);
        return m.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相除
     *
     * @param x double
     * @param x2 double
     * @param scale int 精确几位小数点。
     * @return double
     */
    final static double divide(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.divide(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
