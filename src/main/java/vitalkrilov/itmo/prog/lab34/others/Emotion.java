package vitalkrilov.itmo.prog.lab34.others;

public enum Emotion {
    Enthusiasm;

    @Override
    public String toString() {
        return switch (this) {
            case Enthusiasm -> "Энтузиазм";
        };
    }
}
