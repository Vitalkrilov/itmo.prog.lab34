package vitalkrilov.itmo.prog.lab34.places;

public enum BlockMaterial {
    Limestone;

    @Override
    public String toString() {
        return switch (this) {
            case Limestone -> "Известняковый";
        };
    }
}
