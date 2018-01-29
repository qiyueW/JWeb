package system.base.regex;

/**
 *
 * @author wangchunzi
 */
public class Regex {

    public static final String NUMBER = "(-?[0-9]+\\.{0,1}[0-9]+){0,5}";
    public static final String NUMBER_INTEGER = "-?[0-9]+";
    public static final String NUMBER_DECIMAL = "-?[0-9]+.{1}[0-9]+";
    public static final String NUMBER_L = "([0-9]|(-?[0-9]+.?[0-9]+))";
    public static final String NUMBER_INTEGER_L = "(-?[0-9]+)";
    public static final String NUMBER_DECIMAL_L = "(-?[0-9]+.{1}[0-9]+)";

    public static final String ID = "[1-9]{1}[0-9]{23}";
    public static final String TEXT_L = "[\\w\\W]";
    
    public static final String DATE = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    
//    public static void main(String args[]) {
//        String i = "9.999999";
//        System.out.println(Pattern.compile(NUMBER).matcher(i).matches());
//    }
}
