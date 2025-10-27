package racingcar.controller;

import static racingcar.constants.Symbol.ZERO;

import racingcar.model.Cars;
import racingcar.model.GameRound;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String rawCarsName = inputView.inputCarNames();
        Cars cars = new Cars(rawCarsName);
        String count = inputView.inputTrialCount();
        GameRound gameRound = new GameRound(count);
        startRacingGame(cars, gameRound);
        outputView.printWinner(cars);
    }

    private void startRacingGame(Cars cars, GameRound gameRound) {
        outputView.printResultNotice();
        for (int i = ZERO; i < gameRound.getRound(); i++) {
            cars.moveCars();
            outputView.printOneRoundResult(cars.getCarsDistance());
        }
    }
}