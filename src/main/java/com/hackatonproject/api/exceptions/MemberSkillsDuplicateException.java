package com.hackatonproject.api.exceptions;

import lombok.Data;

@Data
public class MemberSkillsDuplicateException extends RuntimeException{
    public MemberSkillsDuplicateException() {
    }

    public MemberSkillsDuplicateException(String message) {
        super(message);
    }

    public MemberSkillsDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberSkillsDuplicateException(Throwable cause) {
        super(cause);
    }

    public MemberSkillsDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
