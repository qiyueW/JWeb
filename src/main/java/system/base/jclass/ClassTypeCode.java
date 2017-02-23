package system.base.jclass;

/**
 *
 * @author wangchunzi
 */
final public class ClassTypeCode {
    
    public static final int STRING = 0;
    public static final int BYTE = 1;
    public static final int SHORT = 2;
    public static final int INTEGER = 3;
    public static final int LONG = 4;
    public static final int FLOAT = 5;
    public static final int DOUBLE = 6;
    public static final int BOOLEAN = 7;
    public static final int DATE = 8;
//    public static final int TIME = 9;
    /**
     * 
     * @param c
     * @return 
     */
    final public static int getTypeCode(Class c){
        if(c==String.class)return STRING;
        if(c==Byte.class||c==byte.class)return BYTE;
        if(c==Short.class||c==short.class)return SHORT;
        if(c==Integer.class||c==int.class)return INTEGER;
        if(c==Long.class||c==long.class)return LONG;
        if(c==Float.class||c==float.class)return FLOAT;
        if(c==Double.class||c==double.class)return DOUBLE;
        if(c==Boolean.class||c==boolean.class)return BOOLEAN;
        if(c.getSimpleName().equals("Date")){
            return DATE;
        }
        return 0;
    }
}
