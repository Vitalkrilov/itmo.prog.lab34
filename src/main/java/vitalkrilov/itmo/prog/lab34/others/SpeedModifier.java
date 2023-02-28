package vitalkrilov.itmo.prog.lab34.others;

public enum SpeedModifier {
    Normal, SlowedDown;

    @Override
    public String toString() {
        return switch (this) {
            case Normal -> "Обычная";
            case SlowedDown -> "Пониженная";
        };
    }
}
