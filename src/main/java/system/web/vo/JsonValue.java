package system.web.vo;

public interface JsonValue {

    enum ReturnType {

        ARRAY,
        OBJECT,
        STRING,
        NUMBER,
        TRUE,
        FALSE,
        NULL
    }
}
