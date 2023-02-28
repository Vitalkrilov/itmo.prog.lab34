package vitalkrilov.itmo.prog.lab34.others;

public enum Characteristic {
    Snowy, Dense, Immense, Stone, Ancient, Huge;

    @Override
    public String toString() {
        return switch (this) {
            case Snowy -> "Снежный";
            case Dense -> "Плотный";
            case Immense -> "Необъятный";
            case Stone -> "Каменный";
            case Ancient -> "Древний";
            case Huge -> "Огромный";
        };
    }
}
