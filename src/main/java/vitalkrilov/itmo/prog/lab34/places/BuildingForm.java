package vitalkrilov.itmo.prog.lab34.places;

public enum BuildingForm {
    FiveAnglesStar;

    @Override
    public String toString() {
        return switch (this) {
            case FiveAnglesStar -> "Пятиугольная звезда";
        };
    }
}
