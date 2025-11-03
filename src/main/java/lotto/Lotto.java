package lotto;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6) {
            throw ExceptionHandler.illegalArgument("로또 번호는 6개여야 합니다.");
        }

        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw ExceptionHandler.illegalArgument("로또 번호는 중복될 수 없습니다.");
        }

        boolean allInRange = numbers.stream().allMatch(n -> n >= 1 && n <= 45);
        if (!allInRange) {
            throw ExceptionHandler.illegalArgument("로또 번호는 1부터 45 사이여야 합니다.");
        }

    }
}
