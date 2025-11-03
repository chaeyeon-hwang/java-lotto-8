package lotto;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;
    private final Integer bonus;

    public WinningLotto(List<Integer> numbers) {
        this(numbers, null);
    }

    private WinningLotto(List<Integer> numbers, Integer bonus) {
        validateNumbers(numbers);
        if (bonus != null) validateBonus(numbers, bonus);
        this.numbers = List.copyOf(numbers);
        this.bonus = bonus;
    }

    public WinningLotto withBonus(int bonus) {
        return new WinningLotto(this.numbers, bonus);
    }

    private void validateNumbers(List<Integer> numbers) {
        ExceptionHandler.validate(numbers != null && numbers.size() == SIZE, "당첨 번호는 6개여야 합니다.");
        ExceptionHandler.validate(numbers.stream().allMatch(this::inRange), "당첨 번호는 1부터 45 사이여야 합니다.");
        ExceptionHandler.validate(numbers.stream().distinct().count() == SIZE, "당첨 번호에 중복이 있으면 안 됩니다.");
    }

    private void validateBonus(List<Integer> numbers, int bonus) {
        ExceptionHandler.validate(inRange(bonus), "보너스 번호는 1부터 45 사이여야 합니다.");
        Set<Integer> set = new HashSet<>(numbers);
        ExceptionHandler.validate(!set.contains(bonus), "보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    private boolean inRange(int n) {
        return n >= MIN && n <= MAX;
    }

    public int bonus() {
        return bonus;
    }

    public List<Integer> numbers() {
        return numbers;
    }
}