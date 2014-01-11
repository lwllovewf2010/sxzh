package com.sanxian.sxzhuanhuan.http;

/**
 * An exception class that will be thrown when WeiboAPI calls are failed.<br>
 * In case the Weibo server returned HTTP error code, you can get the HTTP status code using getStatusCode() method.
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class PostException extends Exception {
    private int statusCode = -1;
    private static final long serialVersionUID = -2623309261327598087L;

    public PostException(String msg) {
        super(msg);
    }

    public PostException(Exception cause) {
        super(cause);
    }

    public PostException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;

    }

    public PostException(String msg, Exception cause) {
        super(msg, cause);
    }

    public PostException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;

    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
