package LogicTier.exception;

public class CartContentException extends RuntimeException {
    public CartContentException(final String message) {
        super(message);
    }

    // New constructor to accept cause (used by DAO wrappers)
    public CartContentException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
