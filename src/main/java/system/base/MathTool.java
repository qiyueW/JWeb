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
     * @param x
     * @param i
     * @return
     */
    final public static double scale(double x, int i) {
        return BigDecimal.valueOf(x).setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相加
     *
     * @param x
     * @param x2
     * @param scale 精确几位小数点。
     * @return
     */
    final static double add(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.add(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相减
     *
     * @param x
     * @param x2
     * @param scale 精确几位小数点。
     * @return
     */
    final static double subtract(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.subtract(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将两数相乘
     *
     * @param x
     * @param x2
     * @param scale 精确几位小数点。
     * @return
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
     * @param x
     * @param x2
     * @param scale 精确几位小数点。
     * @return
     */
    final static double divide(final double x, final double x2, final int scale) {
        BigDecimal bd = new BigDecimal(x);
        BigDecimal bd2 = new BigDecimal(x2);
        return bd.divide(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String args[]) {
        double i = 3.5;
        double i2 = 10;
        BigDecimal.valueOf(i2 / 10).setScale(2, BigDecimal.ROUND_HALF_UP);
//        System.out.println(BigDecimal.valueOf(i2 / i).setScale(6, BigDecimal.ROUND_HALF_UP));
//        MathTool.divide(i2, i, 6)
    }
}
