package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("구입 금액이 0 이하이면 예외가 발생한다.")
    @Test
    void 구입금액이_0_이하이면_예외() {
        assertThatThrownBy(() -> LottoMachine.buy(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0보다 커야");
    }

    @DisplayName("구입 금액이 1,000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입금액이_1000원단위가_아니면_예외() {
        assertThatThrownBy(() -> LottoMachine.buy(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1,000원 단위");
    }

    @DisplayName("정상적인 금액 입력 시 로또 개수가 정확히 발행된다.")
    @Test
    void 정상금액입력시_로또개수정상() {
        assertThat(LottoMachine.buy(5000)).hasSize(5);
    }
}
