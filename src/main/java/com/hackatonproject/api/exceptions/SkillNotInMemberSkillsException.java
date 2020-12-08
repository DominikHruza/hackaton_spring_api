package com.hackatonproject.api.exceptions;


import lombok.Data;

@Data
public class SkillNotInMemberSkillsException extends RuntimeException {

    public SkillNotInMemberSkillsException() {
    }

    public SkillNotInMemberSkillsException(String message) {
        super(message);
    }

    public SkillNotInMemberSkillsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkillNotInMemberSkillsException(Throwable cause) {
        super(cause);
    }

    public SkillNotInMemberSkillsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
