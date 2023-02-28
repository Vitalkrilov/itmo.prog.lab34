package vitalkrilov.itmo.prog.lab34.entities;

import vitalkrilov.itmo.prog.lab34.interfaces.WidthSizeOperable;
import vitalkrilov.itmo.prog.lab34.places.Block;
import vitalkrilov.itmo.prog.lab34.places.Fortress;
import vitalkrilov.itmo.prog.lab34.places.Location;
import vitalkrilov.itmo.prog.lab34.places.Wall;

import java.util.ArrayList;
import java.util.Objects;

public class HumanGroup extends Entity {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Location> exploredLocations = new ArrayList<>();

    public HumanGroup(String name, Location startLoc) {
        super(name, startLoc);
    }

    public void addMemberName(String name) {
        names.add(name);
    }

    public void exploreLocation(Location loc) {
        exploredLocations.add(loc);
        System.out.println("\"" + this.getName() + "\" изучила локацию \"" + loc.getName() + "\".");
    }

    public boolean isLocationExplored(Location loc) {
        return exploredLocations.contains(loc);
    }

    public void checkIfKnew(Location loc) {
        System.out.printf("\"%s\"%sзнала, что было в локации \"%s\".\n", this.getName(), this.isLocationExplored(loc) ? " " : " не ", loc.getName());
    }

    public void measureWidth(WidthSizeOperable object) {
        System.out.println("\"" + this.getName() + "\" взглянула на \"" + object.toString().replace('\n', ' ') + "\" и поняла, что его толщина " + object.getWidth() + " футов.");
    }

    public void decideWallType(Wall wall) {
        System.out.println("\"" + this.getName() + "\" взглянула на стену и понял, что её тип -- \"" + wall.getWallType() + "\".");
    }

    public void checkIfHasWallsInside(Fortress fortress) {
        String wallsInsideText;
        if (fortress.getHasWallsInside())
            wallsInsideText = "есть перегородки";
        else
            wallsInsideText = "нет перегородок";
        System.out.println("\"" + this.getName() + "\" осмотрела \"" + fortress.getName() + "\" и заметила, что в ней " + wallsInsideText + ".");
    }

    public void tryToAnalyseFortressFloor(Fortress fortress) {
        String verb, result;
        if (fortress.getReasonOfFloorCantBeSeen() == null) {
            verb = "внимательно осмотрела";
            result = "";
        } else {
            verb = "попыталась осмотреть";
            result = ", но не смогла, так как " + fortress.getReasonOfFloorCantBeSeen();
        }
        System.out.println("\"" + this.getName() + "\" " + verb + " нижнюю часть \"" + fortress.getName() + "\"" + result + ".");
    }

    @Override
    public String toString() {
        String res = "";
        res += "ГруппаЛюдей(\n";
        res += "  location=" + this.getLocation() + ",\n";
        res += "  name=" + this.getName() + ",\n";
        res += "  isInsideBuilding=" + this.isInsideBuilding() + ",\n";
        res += "  speedModifier=" + this.getSpeedModifier() + ",\n";
        for (int i = 0; i < names.size(); ++i) {
            res += "  names[" + String.valueOf(i) + "]=" + this.names.get(i);
            if (i + 1 < names.size()) res += ",\n";
        }
        res += ")";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HumanGroup o2 = (HumanGroup)o;
        return Objects.equals(this.names, o2.names) && Objects.equals(this.exploredLocations, o2.exploredLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.names, this.exploredLocations);
    }
}