package vitalkrilov.itmo.prog.lab34.others;

public enum Direction {
    NotStated, North, South, East, West;

    @Override
    public String toString() {
        return switch (this) {
            case NotStated -> "Не определено";
            case North -> "Север";
            case South -> "Юг";
            case West -> "Запад";
            case East -> "Восток";
        };
    }
}