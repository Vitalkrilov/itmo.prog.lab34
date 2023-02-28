package vitalkrilov.itmo.prog.lab34.others;

import vitalkrilov.itmo.prog.lab34.entities.Entity;
import vitalkrilov.itmo.prog.lab34.places.Location;
import vitalkrilov.itmo.prog.lab34.interfaces.Nameable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Vehicle implements Nameable {
    private String name;
    private Location loc;
    private ArrayList<Entity> vehicleUsers = new ArrayList<>();
    private int vehicleUsersMaxCount = 4;

    public Vehicle(String name, Location startLoc) {
        this.name = name;
        this.loc = startLoc;
    }

    public Location getLocation() {
        return this.loc;
    }

    protected void setLocation(Location loc) {
        this.loc = loc;
        for (Entity vehicleUser : this.vehicleUsers) {
            vehicleUser.updateLocation();
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    // It may have size so let it be here
    //NOTE: Do not call it by hand
    public boolean _tryToTake(Entity e) {
        if (vehicleUsers.size() >= vehicleUsersMaxCount)
            throw new NoPlacesInVehicleException();
        vehicleUsers.add(e);
        return true;
    }

    //NOTE: Do not call it by hand
    public void _letItGoAway(Entity e) {
        vehicleUsers.remove(e);
    }

    public abstract void rideTo(Location loc);

    @Override
    public String toString() {
        return String.format("Транспорт(name=%s, loc=%s, characteristics={%s})", name, loc.toString(), this.vehicleUsers.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vehicle o2 = (Vehicle) o;
        return Objects.equals(this.name, o2.name) && Objects.equals(this.loc, o2.loc) && Objects.equals(this.vehicleUsers, o2.vehicleUsers);
    }

    public void setVehicleUsersMaxCount(int count) {
        vehicleUsersMaxCount = count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name, this.loc, this.vehicleUsers);
    }
}
