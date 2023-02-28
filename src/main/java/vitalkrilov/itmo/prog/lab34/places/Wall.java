package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.interfaces.Ageable;
import vitalkrilov.itmo.prog.lab34.interfaces.Characteristicable;
import vitalkrilov.itmo.prog.lab34.interfaces.PhysicalInteractable;
import vitalkrilov.itmo.prog.lab34.interfaces.WidthSizeOperable;
import vitalkrilov.itmo.prog.lab34.others.Characteristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Wall implements Characteristicable, Ageable, PhysicalInteractable, WidthSizeOperable {
    private ArrayList<Characteristic> characteristics = new ArrayList<>();
    private WallType wallType;
    private int age;
    private int widthFt;

    public Wall(WallType wallType, int widthFt) {
        this.wallType = wallType;
        this.widthFt = widthFt;
        this.age = 0;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public WallType getWallType() {
        return this.wallType;
    }

    @Override
    public void addCharacteristic(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setWidth(int width) {
        this.widthFt = width;
    }

    @Override
    public int getWidth() {
        return this.widthFt;
    }

    @Override
    public String toString() {
        return String.format("Стена(wallType=%s, age=%d, widthFt=%d characteristics={%s})", wallType.toString(), age, widthFt, this.characteristics.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wall o2 = (Wall) o;
        return Objects.equals(this.characteristics, o2.characteristics) && Objects.equals(this.wallType, o2.wallType)
                && Objects.equals(this.age, o2.age) && Objects.equals(this.widthFt, o2.widthFt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.characteristics, this.wallType, this.age, this.widthFt);
    }
}
