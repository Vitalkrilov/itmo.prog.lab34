package vitalkrilov.itmo.prog.lab34.places;

import vitalkrilov.itmo.prog.lab34.entities.HumanGroup;
import vitalkrilov.itmo.prog.lab34.interfaces.HeightSizeOperable;
import vitalkrilov.itmo.prog.lab34.interfaces.WidthSizeOperable;
import vitalkrilov.itmo.prog.lab34.others.Characteristic;
import vitalkrilov.itmo.prog.lab34.others.LandingType;
import vitalkrilov.itmo.prog.lab34.others.Surface;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Fortress extends Building implements HeightSizeOperable, WidthSizeOperable {
    private int heightFt;
    private int widthFt;
    private BuildingForm form;
    private boolean hasRoof;
    private Block baseSample;
    private ArrayList<Window> wnds = new ArrayList<>();
    private boolean hasWallsInside;
    private String reasonOfFloorCantBeSeen; // if null, then it can be seen

    public Fortress(String name, Location superLocation, Surface[] knownLevels, Wall wallsUsed, int widthFt, int heightFt, BuildingForm form, boolean hasRoof, Block baseSample, boolean hasWallsInside, String reasonOfFloorCantBeSeen) {
        super(name, superLocation, knownLevels, wallsUsed);
        this.widthFt = widthFt;
        this.heightFt = heightFt;
        this.form = form;
        this.hasRoof = hasRoof;
        this.baseSample = baseSample;
        this.hasWallsInside = hasWallsInside;
        this.reasonOfFloorCantBeSeen = reasonOfFloorCantBeSeen;
    }

    @Override
    public void setHeight(int height) {
        this.heightFt = height;
    }

    @Override
    public int getHeight() {
        return this.heightFt;
    }

    public void setForm(BuildingForm form) {
        this.form = form;
    }

    public BuildingForm getForm() {
        return this.form;
    }

    public void setHasRoof(boolean hasRoof) {
        this.hasRoof = hasRoof;
    }

    public boolean getHasRoof() {
        return this.hasRoof;
    }

    public void setBaseSample(Block baseSample) {
        this.baseSample = baseSample;
    }

    public Block getBaseSample() {
        return this.baseSample;
    }

    @Override
    public String getDescription(String verb, LandingType lt) {
        if (verb == null)
            verb = "находится";
        String res = "";
        for (Characteristic characteristic : this.characteristics) {
            res += characteristic.toString() + " ";
        }
        String roofStatus;
        if (this.hasRoof)
            roofStatus = "и крышу";
        else
            roofStatus = "и не имеющая крышу";
        res += "\"" + this.getName() + "\" шириной " + String.valueOf(this.widthFt) + " футов и высотой " + String.valueOf(this.heightFt) + " футов, имеющая форму \"" + this.form.toString() + "\" " + roofStatus + ", " + verb + " " + lt.toString().toLowerCase() + " " + this.getSurfaceAt(getSurfaceLevelCount()-1).getName();
        if (this.getSuperLocation() != null)
            res += " около локации \"" + this.getSuperLocation().getName() + "\"";
        return res + ".";
    }

    @Override
    public void setWidth(int width) {
        this.widthFt = width;
    }

    @Override
    public int getWidth() {
        return this.widthFt;
    }

    public void addWindow(Window wnd) {
        this.wnds.add(wnd);
    }

    public int getWindowsCount() {
        return this.wnds.size();
    }

    public boolean getHasWallsInside() {
        return hasWallsInside;
    }

    public void setHasWallsInside(boolean hasWallsInside) {
        this.hasWallsInside = hasWallsInside;
    }

    public String getReasonOfFloorCantBeSeen() {
        return reasonOfFloorCantBeSeen;
    }

    public void setReasonOfFloorCantBeSeen(String reasonOfFloorCantBeSeen) {
        this.reasonOfFloorCantBeSeen = reasonOfFloorCantBeSeen;
    }

    @Override
    public String toString() {
        return String.format("Крепость(super=%s, heightFt=%d, widthFt=%d, form=%s, hasRoof=%b, baseSample=%s, wnds={%s}, hasWallsInside=%b, reasonOfFloorCantBeSeen=%S)", super.toString(), heightFt, widthFt, form.toString(), hasRoof, baseSample.toString(), this.characteristics.stream().map(Object::toString).collect(Collectors.joining(", ")), hasWallsInside, reasonOfFloorCantBeSeen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fortress o2 = (Fortress) o;
        return Objects.equals(this.heightFt, o2.heightFt) && Objects.equals(this.widthFt, o2.widthFt)
                && Objects.equals(this.form, o2.form) && Objects.equals(this.hasRoof, o2.hasRoof)
                && Objects.equals(this.baseSample, o2.baseSample) && Objects.equals(this.wnds, o2.wnds)
                && Objects.equals(this.hasWallsInside, o2.hasWallsInside) && Objects.equals(this.reasonOfFloorCantBeSeen, o2.reasonOfFloorCantBeSeen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.heightFt, this.widthFt, this.form, this.hasRoof, this.baseSample, this.wnds, this.hasWallsInside, this.reasonOfFloorCantBeSeen);
    }
}