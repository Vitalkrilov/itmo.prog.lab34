package vitalkrilov.itmo.prog.lab34.others;

import vitalkrilov.itmo.prog.lab34.interfaces.Characteristicable;
import vitalkrilov.itmo.prog.lab34.interfaces.Nameable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Surface implements Nameable, Characteristicable {
    private String name;
    ArrayList<Characteristic> characteristics = new ArrayList<>();

    public Surface(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addCharacteristic(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }

    @Override
    public String toString() {
        return "Поверхность(name=" + this.name + ", characteristics={" + this.characteristics.stream().map(Object::toString).collect(Collectors.joining(", ")) + "})";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Surface o2 = (Surface)o;
        return Objects.equals(this.name, o2.name) && Objects.equals(this.characteristics, o2.characteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name, this.characteristics);
    }
}
