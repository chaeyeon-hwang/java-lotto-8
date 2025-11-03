package lotto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class OutputView {
    private static final String PURCHASED_COUNT_FORMAT = "%d개를 구매했습니다.";
    private static final String STAT_HEADER = "당첨 통계\n---";
    private static final NumberFormat COMMA = NumberFormat.getInstance(Locale.KOREA);
    private static final DecimalFormat ONE_DECIMAL = new DecimalFormat("0.0");

    public static void printPurchased(List<Lotto> lottos) {
        System.out.println(String.format(PURCHASED_COUNT_FORMAT, lottos.size()));
        for (Lotto lotto : lottos) {
            List<Integer> numbers = new ArrayList<>(lotto.numbers());
            numbers.sort(Comparator.naturalOrder());
            System.out.println(numbers);
        }
    }

    public static void printStatistics(ResultCalculator.Result result) {
        System.out.println();
        System.out.println(STAT_HEADER);
        for (Rank rank : Rank.printOrder()) {
            int count = result.countOf(rank);
            String line = rank.printLine(COMMA, count);
            System.out.println(line);
        }

        BigDecimal rate = result.yieldPercent();
        String rateText = ONE_DECIMAL.format(rate);
        System.out.println("총 수익률은 " + rateText + "%입니다.");
    }
}
