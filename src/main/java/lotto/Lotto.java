package lotto;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = toSortedImmutable(numbers);
    }

    private void validate(List<Integer> numbers) {
        ExceptionHandler.validate(numbers != null, "로또 번호 목록이 없습니다.");
        ExceptionHandler.validate(numbers.size() == LOTTO_SIZE, "로또 번호는 6개여야 합니다.");
        ExceptionHandler.validate(numbers.stream().allMatch(Lotto::inRange),
                "로또 번호는 1부터 45 사이여야 합니다.");
        long distinct = numbers.stream().distinct().count();
        ExceptionHandler.validate(distinct == LOTTO_SIZE, "로또 번호에 중복이 있으면 안 됩니다.");
    }

    private static boolean inRange(int n) {
        return n >= MIN && n <= MAX;
    }

    private static List<Integer> toSortedImmutable(List<Integer> numbers) {
        return numbers.stream().sorted().collect(Collectors.toUnmodifiableList());
    }

    public int matchCount(List<Integer> winning) {
        Set<Integer> win = new HashSet<>(winning);
        int count = 0;
        for (int n : numbers) {
            if (win.contains(n)) count++;
        }
        return count;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> numbers() {
        return numbers;
    }
}
