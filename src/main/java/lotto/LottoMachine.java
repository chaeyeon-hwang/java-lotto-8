package lotto;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int PRICE = 1_000;
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int SIZE = 6;

    public static List<Lotto> buy(int money) {
        validateMoney(money);
        int count = money / PRICE;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> nums = Randoms.pickUniqueNumbersInRange(MIN, MAX, SIZE);
            lottos.add(new Lotto(nums));
        }
        return lottos;
    }

    private static void validateMoney(int money) {
        ExceptionHandler.validate(money > 0, "구입 금액은 0보다 커야 합니다.");
        ExceptionHandler.validate(money % PRICE == 0, "구입 금액은 1,000원 단위여야 합니다.");
    }
}