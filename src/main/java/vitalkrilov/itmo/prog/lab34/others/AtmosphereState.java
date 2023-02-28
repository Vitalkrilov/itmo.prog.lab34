package vitalkrilov.itmo.prog.lab34.others;

public enum AtmosphereState {
    Normal, Sparse;

    @Override
    public String toString() {
        return switch (this) {
            case Normal -> "Нормальный";
            case Sparse -> "Разреженный";
        };
    }
}
