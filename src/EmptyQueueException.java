public class EmptyQueueException extends RuntimeException {

    /**
     * generate EmptyQueueException exception
     */
    public EmptyQueueException() {}

    /**
     * generate EmptyQueueException exception with message
     * @param message
     */
    public EmptyQueueException(String message) {
        super(message);
    }

    /**
     * generate EmptyQueueException exception with message and cause
     * @param message
     * @param cause
     */
    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}

