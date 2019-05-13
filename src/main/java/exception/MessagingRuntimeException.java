package exception;

import javax.mail.MessagingException;

public class MessagingRuntimeException extends RuntimeException {
    public MessagingRuntimeException(MessagingException messageException) {
        super(messageException);
    }
}
