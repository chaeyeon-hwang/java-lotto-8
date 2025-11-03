package lotto;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private static final Set<Rank> PRINT_ORDER = new LinkedHashSet<>(Arrays.asList(
            FIFTH, FOURTH, THIRD, SECOND, FIRST
    ));

    private final int matchCount;
    private final boolean requiresBonus;
    private final int prize;

    Rank(int matchCount, boolean requiresBonus, int prize) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean bonusMatched) {
        for (Rank r : EnumSet.allOf(Rank.class)) {
            if (r == MISS) continue;
            if (r.matchCount == matchCount && (!r.requiresBonus || bonusMatched)) {
                return r;
            }
        }
        return MISS;
    }

    public int prize() {
        return prize;
    }

    public String printLine(NumberFormat comma, int count) {
        String base = matchCount + "개 일치";
        if (this == SECOND) base = "5개 일치, 보너스 볼 일치";
        String money = "(" + comma.format(prize) + "원)";
        return base + " " + money + " - " + count + "개";
    }

    public static Iterable<Rank> printOrder() {
        return PRINT_ORDER;
    }
}
