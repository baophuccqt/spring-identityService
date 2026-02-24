package org.baophuccqt.springidentityservice.Exception;

public enum ErrorCode {
    USER_EXISTED(1001, "username has already taken"),
    USER_NOT_EXIST(1002, "user does not exist"),
    USERNAME_TOO_SHORT(1003, "username has to be at least 3-character long"),
    PASSWORD_TOO_SHORT(1004, "password should be at least 8-character long"),
    INVALID_ERROR_CODE(9999, "unknown error"),
    UNAUTHENTICATED(1005, "unauthenticated"),
    BAD_TOKEN(1006, "bad token provided"),
    BAD_PARSE(1007, "bad parse happened"),
    ;

    private int code; // by default, 1000 is succesful
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
