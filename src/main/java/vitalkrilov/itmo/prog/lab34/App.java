package vitalkrilov.itmo.prog.lab34;

import vitalkrilov.itmo.prog.lab34.entities.HumanGroup;
import vitalkrilov.itmo.prog.lab34.others.*;
import vitalkrilov.itmo.prog.lab34.places.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Entry point of App.
 *
 */
public class App {

    static class World {
        private int currentTimeHours;

        public World() {
            this(0);
        }

        public World(int presetTime) {
            this.currentTimeHours = presetTime;
        }

        public void tickFor(int timeHours) {
            this.currentTimeHours += timeHours;
            System.out.println("Прошло " + String.valueOf(timeHours) + " часов.");
        }

        @Override
        public String toString() {
            return "Мир(время: " + this.currentTimeHours + " часов)";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            World o2 = (World)o;
            return Objects.equals(this.currentTimeHours, o2.currentTimeHours);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), this.currentTimeHours);
        }
    }

    private final ArrayList<Location> locations = new ArrayList<>();
    class LocationsContainerView { // [!]: Uses super.locations as its own!
        public void addLocation(Location l) {
            locations.add(l);
        }

        public Location getLocation(int locationIdx) {
            if (locationIdx < 0 || locationIdx >= locations.size())
                throw new IndexOutOfBoundsException();
            return locations.get(locationIdx);
        }

        public int getSize() {
            return locations.size();
        }

        @Override
        public String toString() {
            String res = "Список локаций:\n";
            for (int i = 0; i < this.getSize(); ++i)
                res += "\t- " + this.getLocation(i).toString() + ";\n";
            return res;
        }

        private ArrayList<Location> getLocations() {
            return locations;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            LocationsContainerView o2 = (LocationsContainerView) o;
            return Objects.equals(locations, o2.getLocations());
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), locations);
        }
    }

    // Consts
    private static final int theAppearanceOfTheFirstManYearsAgo = 2800000;
    private static final int midJurassicPeriodYearsAgo = 173000000;
    private static final int farBeforeAboutYears = 10000; // пусть задолго -- это за 10000 лет, хм...

    public Vehicle createFlyingVehicle(Location whereCreate) {
        class FlyingVehicle extends Vehicle {
            public FlyingVehicle(String name, Location startLoc) {
                super(name, startLoc);
            }

            @Override
            public void rideTo(Location loc) {
                this.setLocation(loc);
                System.out.println("Летающее транспортное средство \"" + this.getName() + "\" летит в локацию \"" + loc.getName() + "\".");
            }

            @Override
            public String toString() {
                return "ЛетающееТранспортноеСредство(" + super.toString() + ")";
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || this.getClass() != o.getClass()) return false;
                return super.equals(o);
            }
        }
        return new FlyingVehicle("Безымянное", whereCreate);
    }

    public void run() {
        LocationsContainerView lcv = this.new LocationsContainerView();
        World w = new World(0);
        Location unknownStart = new Location("Стартовая точка");
        lcv.addLocation(unknownStart);
        HumanGroup we = new HumanGroup("Мы", unknownStart);
        we.addMemberName("Рассказчик");
        we.addMemberName("Денфорт");
        Location hrebty = new Location("Хребты");
        lcv.addLocation(hrebty);
        Surface nastPerevala = new Surface("Наст");
        nastPerevala.addCharacteristic(Characteristic.Snowy);
        nastPerevala.addCharacteristic(Characteristic.Dense);
        Location pereval = new Location("Перевал", hrebty, new Surface[]{ nastPerevala });
        pereval.setAtmosphereState(AtmosphereState.Sparse);
        lcv.addLocation(pereval);
        Vehicle flVeh = this.createFlyingVehicle(unknownStart);
        try {
            we.enterInVehicle(flVeh);
            flVeh.rideTo(pereval);
            we.exploreLocation(pereval);
            we.exitFromVehicle();
        } catch (NoPlacesInVehicleException e) {
            System.out.println("Не удалось сесть в транспорт по причине: \"" + e.getDescription() + "\".");
        }
        Feeling proximityMiracleFeeling = new Feeling("Близость чуда") {
            @Override
            public String toString() {
                return "ЧувствоБлизостиЧуда(" + this.getName() + ")";
            }
        };
        we.feel(proximityMiracleFeeling);
        w.tickFor(4);
        Wall labyrinthWall = new Wall(WallType.Standard, 1); // well, let widthFt be 1 ft.
        labyrinthWall.addCharacteristic(Characteristic.Ancient);
        labyrinthWall.setAge(theAppearanceOfTheFirstManYearsAgo + farBeforeAboutYears);
        Building labyrinth = new Building("Лабиринт", pereval, null, labyrinthWall);
        labyrinth.setDirection(Direction.West);
        labyrinth.addCharacteristic(Characteristic.Immense);
        labyrinth.addCharacteristic(Characteristic.Stone);
        lcv.addLocation(labyrinth);
        we.goDownTo(labyrinth);
        we.feel(proximityMiracleFeeling);
        we.checkIfKnew(pereval);
        we.checkIfKnew(labyrinth);
        we.beingInState(State.ReverentHorror);
        we.decide("ощущение космической аномалии", labyrinthWall.getAge() >= theAppearanceOfTheFirstManYearsAgo);
        System.out.println("Воздух был " + we.getLocation().getAtmosphereState().toString().toLowerCase() + ".");
        we.checkSpeedModifier();
        we.beingInState(State.NotBad);
        we.currentEmotion(Emotion.Enthusiasm);
        Building ruins = new Building("Руины", pereval, new Surface[]{ new Surface("Снег") }, new Wall(WallType.Standard, 2)); // well, let widthFt be 2 ft.
        lcv.addLocation(ruins);
        System.out.println(ruins.getDescription("торчит", LandingType.In));
        Block block = new Block(BlockMaterial.Limestone, midJurassicPeriodYearsAgo, 6, 8);
        Fortress fortress = new Fortress("Крепость", pereval,
                new Surface[]{ new Surface("Ледяная кора"), new Surface("Снег") },
                new Wall(WallType.CarvedOrnament, 5), 300,10, BuildingForm.FiveAnglesStar, false,
                block, false, "сокрыта под темной толщей снега и льда");
        lcv.addLocation(fortress);
        fortress.addCharacteristic(Characteristic.Huge);
        for (int i = 0; i < 5; ++i) {
            Window wnd = new Window(4, WindowType.Arched);
            fortress.addWindow(wnd);
        }
        System.out.println(fortress.getDescription(null, LandingType.On));
        we.goTo(fortress);
        we.touch(fortress.getBaseSample());
        try {
            we.goInside();
            we.measureWidth(fortress.getWallsUsed());
            we.checkIfHasWallsInside(fortress);
            we.decideWallType(fortress.getWallsUsed());
            we.tryToAnalyseFortressFloor(fortress);
        } catch (BuildingLockedException e) {
            System.out.println("Внезапно(!) не удалось войти в постройку по причине: \"" + e.getDescription() + "\"");
        }

        System.out.println();
        System.out.println("Список локаций:");
        for (int i = 0; i < lcv.getSize(); ++i) {
            System.out.println("\t- " + lcv.getLocation(i).toString() + ";");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}
