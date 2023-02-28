package vitalkrilov.itmo.prog.lab34.places;

public enum WallType {
    Standard, CarvedOrnament;

    @Override
    public String toString() {
        return switch (this) {
            case Standard -> "Стандартная";
            case CarvedOrnament -> "Резной орнамент";
        };
    }
}
