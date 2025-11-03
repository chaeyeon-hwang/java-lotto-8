package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNING = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS = "보너스 번호를 입력해 주세요.";
    private static final String COMMA = ",";

    public static int readPurchaseAmount() {
        System.out.println(ASK_MONEY);
        String raw = Console.readLine();
        int amount = parseInt(raw, "구입 금액은 숫자여야 합니다.");
        return amount;
    }

    public static WinningLotto readWinningLotto() {
        System.out.println(ASK_WINNING);
        String raw = Console.readLine();
        List<Integer> numbers = parseNumbers(raw);
        return new WinningLotto(numbers);
    }

    public static int readBonusNumber() {
        System.out.println(ASK_BONUS);
        String raw = Console.readLine();
        return parseInt(raw, "보너스 번호는 숫자여야 합니다.");
    }

    private static int parseInt(String raw, String messageIfFail) {
        ExceptionHandler.validate(raw != null && !raw.isBlank(), "입력은 비어 있을 수 없습니다.");
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw ExceptionHandler.illegalArg(messageIfFail);
        }
    }

    private static List<Integer> parseNumbers(String raw) {
        ExceptionHandler.validate(raw != null && !raw.isBlank(), "당첨 번호 입력이 비어 있습니다.");
        try {
            return Arrays.stream(raw.split(COMMA))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw ExceptionHandler.illegalArg("당첨 번호는 쉼표로 구분된 정수여야 합니다.");
        }
    }
}
