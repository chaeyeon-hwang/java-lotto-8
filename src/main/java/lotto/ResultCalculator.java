package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {
    private static final int HUNDRED = 100;

    public static Result calculate(List<Lotto> lottos, WinningLotto winning, int purchaseAmount) {
        Map<Rank, Integer> counter = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) counter.put(r, 0);

        for (Lotto lotto : lottos) {
            int match = lotto.matchCount(winning.numbers());
            boolean bonus = match == 5 && lotto.contains(winning.bonus());
            Rank rank = Rank.of(match, bonus);
            counter.put(rank, counter.get(rank) + 1);
        }

        long totalPrize = counter.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().prize() * e.getValue())
                .sum();

        BigDecimal yield = BigDecimal.valueOf(totalPrize)
                .multiply(BigDecimal.valueOf(HUNDRED))
                .divide(BigDecimal.valueOf(purchaseAmount), 1, RoundingMode.HALF_UP);

        return new Result(counter, totalPrize, yield);
    }

    public static class Result {
        private final Map<Rank, Integer> counter;
        private final long totalPrize;
        private final BigDecimal yieldPercent;

        public Result(Map<Rank, Integer> counter, long totalPrize, BigDecimal yieldPercent) {
            this.counter = counter;
            this.totalPrize = totalPrize;
            this.yieldPercent = yieldPercent;
        }

        public int countOf(Rank rank) {
            return counter.getOrDefault(rank, 0);
        }

        public long totalPrize() {
            return totalPrize;
        }

        public BigDecimal yieldPercent() {
            return yieldPercent;
        }
    }
}
