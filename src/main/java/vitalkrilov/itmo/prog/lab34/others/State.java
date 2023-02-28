package vitalkrilov.itmo.prog.lab34.others;

public enum State {
    ReverentHorror, NotBad;

    @Override
    public String toString() {
        return switch (this) {
            case ReverentHorror -> "Благовейный ужас";
            case NotBad -> "Неплохое";
        };
    }
}
