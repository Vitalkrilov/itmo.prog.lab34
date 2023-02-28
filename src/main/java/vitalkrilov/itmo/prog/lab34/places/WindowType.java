package vitalkrilov.itmo.prog.lab34.places;

public enum WindowType {
    Standard, Arched;

    @Override
    public String toString() {
        return switch (this) {
            case Standard -> "Стандартная";
            case Arched -> "Сводчатые";
        };
    }
}
