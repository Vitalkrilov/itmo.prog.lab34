package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.interfaces.HeightPositionOperable;

import java.util.Objects;

public class Window implements HeightPositionOperable {
    private int heightPosFt;
    private WindowType type;

    public Window(int heightPosFt, WindowType type) {
        this.heightPosFt = heightPosFt;
        this.type = type;
    }

    @Override
    public void setHeightPosition(int heightPos) {
        this.heightPosFt = heightPos;
    }

    @Override
    public int getHeightPosition() {
        return this.heightPosFt;
    }

    public WindowType getType() {
        return type;
    }

    public void setType(WindowType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Окно(%s, %d)", this.type.toString(), this.heightPosFt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Window o2 = (Window) o;
        return Objects.equals(this.heightPosFt, o2.heightPosFt) && Objects.equals(this.type, o2.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.heightPosFt, this.type);
    }
}
