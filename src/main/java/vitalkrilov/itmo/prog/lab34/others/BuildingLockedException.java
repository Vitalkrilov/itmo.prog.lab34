package vitalkrilov.itmo.prog.lab34.others;

public class BuildingLockedException extends Exception {
    public String getDescription() {
        return "Вы не можете войти в закрытую постройку!";
    }
}
