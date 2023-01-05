public class TaskAlreadyExistsException extends RuntimeException  {

    /**
     * generate TaskAlreadyExist exception
     */
        public TaskAlreadyExistsException() {}

    /**
     * generate TaskAlreadyExist exception with message
     * @param message
     */
        public TaskAlreadyExistsException(String message) {
            super(message);
        }

    /**
     * generate TaskAlreadyExist exception with message and cause
     * @param message
     * @param cause
     */
        public TaskAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }

}
