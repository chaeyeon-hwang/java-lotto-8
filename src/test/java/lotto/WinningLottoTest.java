package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    @DisplayName("당첨 번호가 중복되면 예외가 발생한다")
    @Test
    void 당첨번호중복_예외() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1,1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void 보너스번호중복_예외() {
        WinningLotto lotto = new WinningLotto(List.of(1,2,3,4,5,6));
        assertThatThrownBy(() -> lotto.withBonus(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다")
    @Test
    void 보너스번호범위_예외() {
        WinningLotto lotto = new WinningLotto(List.of(1,2,3,4,5,6));
        assertThatThrownBy(() -> lotto.withBonus(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> lotto.withBonus(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상 입력 시 보너스 번호 설정이 정상 동작한다.")
    @Test
    void 보너스정상입력() {
        WinningLotto lotto = new WinningLotto(List.of(1,2,3,4,5,6)).withBonus(7);
        assertThat(lotto.bonus()).isEqualTo(7);
    }
}
