package racingcar.model;

import static racingcar.constants.Symbol.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.util.RandomGenerator;

public class Cars {
    private final List<Car> cars;

    public Cars(String rawCarsName) {
        validateCarsName(rawCarsName);
        this.cars = initCars(rawCarsName);
    }

    private void validateCarsName(String carsName) {
        String[] parts = carsName.split(COMMA);
        Set<String> uniqueNames = new HashSet<>();

        for (String name : parts) {
            validateUniqueName(uniqueNames, name);
        }
    }

    private void validateUniqueName(Set<String> uniqueNames, String name) {
        if (hasDuplicateName(uniqueNames, name)) {
            throw new IllegalArgumentException("자동차 이름에 중복된 값이 있습니다.");
        }
    }

    private boolean hasDuplicateName(Set<String> uniqueNames, String name) {
        return !uniqueNames.add(name);
    }

    private List<Car> initCars(String carsName) {
        String[] parts = carsName.split(COMMA);
        return Arrays.stream(parts)
                .map(CarName::new)
                .map(Car::new)
                .toList();
    }

    public void moveCars() {
        for (Car car : cars) {
            moveCarIfRandomlyTriggered(car);
        }
    }

    private void moveCarIfRandomlyTriggered(Car car) {
        if (RandomGenerator.Movable()) {
            car.forwardDistance();
        }
    }

    public String getCarsDistance() {
        StringBuilder result = new StringBuilder();
        for (Car car : cars) {
            result.append(car).append(NEW_LINE);
        }
        return result.toString();
    }

    public String getWinner() {
        List<String> winner = new ArrayList<>();
        Integer maxDistance = getMaxDistance();

        for (Car car : cars) {
            addWinnerIfMaxDistance(winner, car, maxDistance);
        }
        return String.join(COMMA + SPACE, winner);
    }

    private void addWinnerIfMaxDistance(List<String> winner, Car car, Integer maxDistance) {
        if (car.getDistance().equals(maxDistance)) {
            winner.add(car.getName());
        }
    }

    private Integer getMaxDistance() {
        int maxDistance = ZERO;
        for (Car car : cars) {
            maxDistance = Math.max(maxDistance, car.getDistance());
        }
        return maxDistance;
    }
}
