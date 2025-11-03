package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class ExceptionHandlerTest {

    @DisplayName("validate 조건이 거짓이면 IllegalArgumentException이 발생한다.")
    @Test
    void validate_거짓이면_예외발생() {
        assertThatThrownBy(() -> ExceptionHandler.validate(false, "테스트 메시지"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("validate 조건이 참이면 예외가 발생하지 않는다.")
    @Test
    void validate_참이면_정상동작() {
        assertThatCode(() -> ExceptionHandler.validate(true, "정상"))
                .doesNotThrowAnyException();
    }
}

