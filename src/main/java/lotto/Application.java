package lotto;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        try {
            new Application().run();
        } finally {
            camp.nextstep.edu.missionutils.Console.close();
        }
    }

    private void run() {
        int purchaseAmount = retryUntilValid(InputView::readPurchaseAmount);
        List<Lotto> lottos = LottoMachine.buy(purchaseAmount);
        OutputView.printPurchased(lottos);

        WinningLotto winningLotto = retryUntilValid(InputView::readWinningLotto);
        int bonus = retryUntilValid(InputView::readBonusNumber);
        winningLotto = winningLotto.withBonus(bonus);

        ResultCalculator.Result result = ResultCalculator.calculate(lottos, winningLotto, purchaseAmount);
        OutputView.printStatistics(result);
    }

    private <T> T retryUntilValid(SupplierWithException<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                ExceptionHandler.print(e);
            }
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get();
    }
}
