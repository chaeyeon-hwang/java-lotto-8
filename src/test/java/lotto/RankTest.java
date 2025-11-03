package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 등수가 매핑된다.")
    @Test
    void 등수매핑검증() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
    }
}
