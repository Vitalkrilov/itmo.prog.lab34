package vitalkrilov.itmo.prog.lab34.others;

import vitalkrilov.itmo.prog.lab34.entities.Entity;
import vitalkrilov.itmo.prog.lab34.interfaces.Nameable;

import java.util.Objects;

public class Feeling implements Nameable {
    private String name;

    public Feeling(String name) {
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
    public String toString() {
        return "Чувство(\"" + this.name + "\")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Feeling o2 = (Feeling) o;
        return Objects.equals(this.name, o2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name);
    }
}
