package cn.xpbootcamp.gilded_rose.exception;

public class ProductException extends RuntimeException {

    private int httpCode;

    public ProductException(int httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public ProductException(int httpCode, String message, Throwable pre) {
        super(message, pre);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
