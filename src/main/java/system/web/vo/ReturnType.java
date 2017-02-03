package system.web.vo;


public interface ReturnType {
    public enum TYPE {
        JSON,
        CHAR,
        STREAM,
        FILE,
        ERROR,
        SUCCESS,
        OK,
        TEXT
    }
    
}
