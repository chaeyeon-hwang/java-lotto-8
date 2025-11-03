package lotto;

public class ExceptionHandler {
    private static final String PREFIX = "[ERROR] ";

    public static void print(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.isBlank()) {
            System.out.println(PREFIX + "오류가 발생했습니다.");
            return;
        }
        if (msg.startsWith(PREFIX)) {
            System.out.println(msg);
            return;
        }
        System.out.println(PREFIX + msg);
    }

    public static void validate(boolean condition, String message) {
        if (!condition) {
            throw illegalArg(message);
        }
    }

    public static IllegalArgumentException illegalArg(String message) {
        return new IllegalArgumentException(PREFIX + message);
    }
}
