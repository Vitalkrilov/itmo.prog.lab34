package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.interfaces.Ageable;
import vitalkrilov.itmo.prog.lab34.interfaces.LengthSizeOperable;
import vitalkrilov.itmo.prog.lab34.interfaces.PhysicalInteractable;
import vitalkrilov.itmo.prog.lab34.interfaces.WidthSizeOperable;

import java.util.Objects;

public class Block implements PhysicalInteractable, Ageable, WidthSizeOperable, LengthSizeOperable {
    private BlockMaterial material;
    private int age;
    private int lengthFt;
    private int widthFt;

    public Block(BlockMaterial material, int age, int widthFt, int lengthFt) {
        this.material = material;
        this.age = age;
        this.widthFt = widthFt;
        this.lengthFt = lengthFt;
    }

    public void setMaterial(BlockMaterial material) {
        this.material = material;
    }

    public BlockMaterial getMaterial() {
        return material;
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
    public void setLength(int length) {
        this.lengthFt = length;
    }

    @Override
    public int getLength() {
        return this.lengthFt;
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
        return String.format("Глыба(material=%s, age=%d, lengthFt=%d, widthFt=%d)", material.toString(), age, lengthFt, widthFt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Block o2 = (Block)o;
        return Objects.equals(this.material, o2.material) && Objects.equals(this.age, o2.age) && Objects.equals(this.lengthFt, o2.lengthFt) && Objects.equals(this.widthFt, o2.widthFt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.material, this.age, this.lengthFt, this.widthFt);
    }
}
