package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 6개 미만이면 예외가 발생한다")
    @Test
    void 번호6개미만() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("1~45 범위를 벗어나면 예외가 발생한다")
    @Test
    void 범위벗어나면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(0,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 번호로 생성 시 정상적으로 로또가 생성된다.")
    @Test
    void 올바른번호_정상생성() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        assertThat(lotto.numbers()).containsExactly(1,2,3,4,5,6);
    }
}
