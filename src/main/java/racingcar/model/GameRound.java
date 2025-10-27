package racingcar.model;

public class GameRound {
    private final Integer round;

    public GameRound(String round) {
        this.round = validateRound(round);
    }

    private Integer validateRound(String round) {
        try {
            Integer parsedRound = Integer.parseInt(round);
            validatePositive(parsedRound);
            return parsedRound;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자만 입력해야 합니다.");
        }
    }

    private void validatePositive(Integer round) {
        if (isNotPositive(round)) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 값이어야 합니다.");
        }
    }

    private boolean isNotPositive(Integer round) {
        return round < 1;
    }

    public Integer getRound() {
        return round;
    }
}
