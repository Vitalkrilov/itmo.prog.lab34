package vitalkrilov.itmo.prog.lab34.others;

public enum LandingType {
    On, In;

    @Override
    public String toString() {
        return switch (this) {
            case On -> "На";
            case In -> "В";
        };
    }
}
