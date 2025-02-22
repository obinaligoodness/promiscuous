package africa.semicolon.promeescuous.exceptions;

public enum ExceptionMessage {
    USER_NOT_FOUND_EXCEPTION("User not found"),
    USER_WITH_EMAIL_NOT_FOUND_EXCEPTION("user with email %s not found"),

    ACCOUNT_ACTIVATION_FAILED_EXCEPTION("Account activation was not successful"),

    MEDIA_NOT_FOUND(""),
    INVALID_CREDENTIALS_EXCEPTION("Invalid authentication credentials"),
    USER_REGISTRATION_FAILED_EXCEPTION("User registration failed");

    ExceptionMessage(String message){
        this.message=message;
    }

    private final String message;

    public String getMessage(){
        return message;
    }

}
