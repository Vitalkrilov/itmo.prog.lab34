package vitalkrilov.itmo.prog.lab34.entities;

import vitalkrilov.itmo.prog.lab34.interfaces.PhysicalInteractable;
import vitalkrilov.itmo.prog.lab34.others.*;
import vitalkrilov.itmo.prog.lab34.places.Building;
import vitalkrilov.itmo.prog.lab34.interfaces.Nameable;
import vitalkrilov.itmo.prog.lab34.places.Location;
import vitalkrilov.itmo.prog.lab34.others.Vehicle;

import java.util.Objects;

public abstract class Entity implements Nameable {
    private Location location;
    private boolean isInsideBuilding;
    private String name;
    private Vehicle currentlyUsingVehicle = null;
    private SpeedModifier speedModifier;

    public Entity(String name, Location startLoc) {
        this.name = name;
        this.location = startLoc;
        this.speedModifier = SpeedModifier.Normal;
        this.isInsideBuilding = false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean isInVehicle() {
        return this.currentlyUsingVehicle != null;
    }

    public void enterInVehicle(Vehicle vh) {
        if (isInVehicle()) exitFromVehicle();

        if (vh._tryToTake(this)) {
            this.currentlyUsingVehicle = vh;
            System.out.println("\"" + this.getName() + "\" зашел в тр. средство \"" + this.currentlyUsingVehicle.getName() + "\".");
        }
    }

    public void exitFromVehicle() {
        if (isInVehicle()) {
            this.currentlyUsingVehicle._letItGoAway(this);
            System.out.println("\"" + this.getName() + "\" вышел из тр. средства \"" + this.currentlyUsingVehicle.getName() + "\".");
            this.currentlyUsingVehicle = null;
        }
    }

    public void updateLocation() {
        if (isInVehicle())
            this.location = this.currentlyUsingVehicle.getLocation();

        if (this.location.getAtmosphereState() == AtmosphereState.Sparse) {
            this.speedModifier = SpeedModifier.SlowedDown;
            System.out.println("(( Тем временем скорость \"" + this.getName() + "\" понизилась, т.к. он оказался в локации, где воздух " + this.location.getAtmosphereState().toString().toLowerCase() + ". ))");
        }
    }

    public Location getLocation() {
        return location;
    }

    public SpeedModifier getSpeedModifier() {
        return speedModifier;
    }

    public void feel(Feeling f) {
        System.out.println("\"" + this.getName() + "\" ощущает \"" + f.getName() + "\".");
    }

    public void beingInState(State st) {
        System.out.println("\"" + this.getName() + "\" испытывает состояние \"" + st.toString() + "\".");
    }

    public void checkSpeedModifier() {
        System.out.println("Скорость движения у \"" + this.getName() + "\" " + this.speedModifier.toString().toLowerCase() + ".");
    }

    public void currentEmotion(Emotion emotion) {
        System.out.println("На данный момент эмоция у \"" + this.getName() + "\" -- \"" + emotion.toString() + "\".");
    }

    public void goDownTo(Location newLocation) {
        if (this.isInsideBuilding) this.goOutside();
        System.out.println("\"" + this.getName() + "\" спускается вниз по \"" + this.location.getSurfaceAt(this.location.getSurfaceLevelCount()-1).getName() + "\" к \"" + newLocation.getName() + "\".");
        this.location = newLocation;
    }

    public void goTo(Location newLocation) {
        if (this.isInsideBuilding) this.goOutside();
        System.out.println("\"" + this.getName() + "\" пошел к \"" + newLocation.getName() + "\".");
        this.location = newLocation;
    }

    public void decide(String expression, boolean result) {
        System.out.printf("\"%s\"%sиспытывала %s.\n", this.getName(), result ? " " : " не ", expression);
    }

    public void touch(PhysicalInteractable object) {
        System.out.println("\"" + this.getName() + "\" прикоснулся к объекту \"" + object.toString().replace('\n', ' ') + "\".");
    }

    public void goInside() throws BuildingLockedException {
        if (this.location instanceof Building && !this.isInsideBuilding) {
            if (((Building)this.location).isLocked())
                throw new BuildingLockedException();
            this.isInsideBuilding = true;
            System.out.println("\"" + this.getName() + "\" вошел в \"" + this.location.getName() + "\".");
        }
    }

    public void goOutside() {
        if (this.isInsideBuilding) {
            this.isInsideBuilding = false;
            System.out.println("\"" + this.getName() + "\" вышел из \"" + this.location.getName() + "\".");
        }
    }

    public boolean isInsideBuilding() {
        return isInsideBuilding;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entity o2 = (Entity) o;
        return Objects.equals(this.location, o2.location) && Objects.equals(this.isInsideBuilding, o2.isInsideBuilding)
                && Objects.equals(this.name, o2.name) && Objects.equals(this.currentlyUsingVehicle, o2.currentlyUsingVehicle)
                && Objects.equals(this.speedModifier, o2.speedModifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.location, this.isInsideBuilding, this.name, this.currentlyUsingVehicle, this.speedModifier);
    }
}