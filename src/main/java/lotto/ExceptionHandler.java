package lotto;

public class ExceptionHandler {
    private static final String PREFIX = "[ERROR] ";

    private ExceptionHandler() {}

    public static IllegalArgumentException illegalArgument(String message) {
        return new IllegalArgumentException(PREFIX + message);
    }

    public static IllegalStateException illegalState(String message) {
        return new IllegalStateException(PREFIX + message);
    }
}
