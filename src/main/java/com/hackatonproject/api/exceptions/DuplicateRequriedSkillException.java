package com.hackatonproject.api.exceptions;

public class DuplicateRequriedSkillException extends RuntimeException {

    public DuplicateRequriedSkillException() {
    }

    public DuplicateRequriedSkillException(String message) {
        super(message);
    }

    public DuplicateRequriedSkillException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRequriedSkillException(Throwable cause) {
        super(cause);
    }

    public DuplicateRequriedSkillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
