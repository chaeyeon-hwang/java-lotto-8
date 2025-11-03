package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultCalculatorTest {

    @DisplayName("등급별 개수와 총 상금이 정확히 계산된다.")
    @Test
    void 당첨결과_총상금계산() {
        List<Lotto> purchased = List.of(
                new Lotto(List.of(1,2,3,4,5,6)),
                new Lotto(List.of(1,2,3,4,5,7)),
                new Lotto(List.of(1,2,3,4,5,8))
        );
        WinningLotto winning = new WinningLotto(List.of(1,2,3,4,5,6)).withBonus(7);
        ResultCalculator.Result result = ResultCalculator.calculate(purchased, winning, 3000);

        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(1);

        long totalPrize = Rank.FIRST.prize() + Rank.SECOND.prize() + Rank.THIRD.prize();
        assertThat(result.totalPrize()).isEqualTo(totalPrize);
    }

    @DisplayName("수익률 계산이 소수 첫째 자리에서 반올림되어 출력된다.")
    @Test
    void 수익률_계산_반올림() {
        List<Lotto> purchased = List.of(
                new Lotto(List.of(1,2,3,4,5,6))
        );
        WinningLotto winning = new WinningLotto(List.of(1,2,3,4,5,6)).withBonus(7);
        ResultCalculator.Result result = ResultCalculator.calculate(purchased, winning, 1000);
        assertThat(result.yieldPercent().doubleValue()).isEqualTo(200000000.0);
    }
}
