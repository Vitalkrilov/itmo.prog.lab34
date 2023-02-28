package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.interfaces.Characteristicable;
import vitalkrilov.itmo.prog.lab34.others.Characteristic;
import vitalkrilov.itmo.prog.lab34.others.LandingType;
import vitalkrilov.itmo.prog.lab34.others.Surface;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Building extends Location implements Characteristicable {
    private Wall wallsUsed;
    protected ArrayList<Characteristic> characteristics = new ArrayList<>();
    private boolean locked = false;

    public Building(String name, Location superLocation, Surface[] knownLevels, Wall wallsUsed) {
        super(name, superLocation, knownLevels);
        this.wallsUsed = wallsUsed;
    }

    @Override
    public void addCharacteristic(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }

    public String getDescription(String verb, LandingType lt) {
        if (verb == null)
            verb = "находится";
        String res = "";
        for (Characteristic characteristic : this.characteristics) {
            res += characteristic.toString() + " ";
        }
        res += "\"" + this.getName() + "\" " + verb + " " + lt.toString().toLowerCase() + " " + this.getSurfaceAt(getSurfaceLevelCount()-1).getName();
        if (this.getSuperLocation() != null)
            res += " около локации \"" + this.getSuperLocation().getName() + "\"";
        return res + ".";
    }

    public Wall getWallsUsed() {
        return wallsUsed;
    }

    public void setWallsUsed(Wall wallsUsed) {
        this.wallsUsed = wallsUsed;
    }

    @Override
    public String toString() {
        return "Постройка(wallsUsed=" + this.wallsUsed.toString() + ", characteristics={" + this.characteristics.stream().map(Object::toString).collect(Collectors.joining(", ")) + "})";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Building o2 = (Building)o;
        return Objects.equals(this.characteristics, o2.characteristics) && Objects.equals(this.wallsUsed, o2.wallsUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.characteristics, this.wallsUsed);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
