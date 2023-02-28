package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.interfaces.Nameable;
import vitalkrilov.itmo.prog.lab34.others.AtmosphereState;
import vitalkrilov.itmo.prog.lab34.others.Direction;
import vitalkrilov.itmo.prog.lab34.others.Surface;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Location implements Nameable {
    private String name;
    private Location superLocation;
    private Surface[] surfaceLevels;
    private Direction directionRelativelyToSuperLocation;
    private AtmosphereState atmosphereState;

    public Location(String name) {
        this(name, null, null);
    }

    //NOTE: knownLevels must have at least 1 element!
    public Location(String name, Location superLocation, Surface[] surfaceLevels) {
        this.name = name;
        this.superLocation = superLocation;
        if (surfaceLevels != null)
            this.surfaceLevels = surfaceLevels;
        else
            this.surfaceLevels = new Surface[]{ new Surface("Что-то неизвестное") };
        directionRelativelyToSuperLocation = Direction.NotStated;
        if (superLocation != null)
            atmosphereState = superLocation.getAtmosphereState();
        else
            atmosphereState = AtmosphereState.Normal;
    }

    public void setAtmosphereState(AtmosphereState atmosphereState) {
        this.atmosphereState = atmosphereState;
    }

    public AtmosphereState getAtmosphereState() {
        return this.atmosphereState;
    }

    public Location getSuperLocation() {
        return superLocation;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Direction getDirection() {
        return directionRelativelyToSuperLocation;
    }

    public void setDirection(Direction d) {
        directionRelativelyToSuperLocation = d;
    }

    public int getSurfaceLevelCount() {
        return this.surfaceLevels.length;
    }

    public Surface getSurfaceAt(int level) {
        if (level < 0 || level >= this.surfaceLevels.length) return null;
        return this.surfaceLevels[level];
    }

    @Override
    public String toString() {
        String sSuperLoc;
        if (this.superLocation != null)
            sSuperLoc = this.superLocation.toString();
        else
            sSuperLoc = "-";
        return String.format("Локация(name=%s, sSuperLoc=%s, surfaceLevels={%s}, directionRelativelyToSuperLocation=%s, atmosphereState=%s)", name, sSuperLoc, Arrays.stream(this.surfaceLevels).map(Object::toString).collect(Collectors.joining(", ")), directionRelativelyToSuperLocation.toString(), atmosphereState.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location o2 = (Location) o;
        return Objects.equals(this.name, o2.name) && Objects.equals(this.superLocation, o2.superLocation)
                && Arrays.equals(this.surfaceLevels, o2.surfaceLevels) && Objects.equals(this.directionRelativelyToSuperLocation, o2.directionRelativelyToSuperLocation)
                && Objects.equals(this.atmosphereState, o2.atmosphereState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name, this.superLocation, Arrays.hashCode(this.surfaceLevels), this.directionRelativelyToSuperLocation, this.atmosphereState);
    }
}
