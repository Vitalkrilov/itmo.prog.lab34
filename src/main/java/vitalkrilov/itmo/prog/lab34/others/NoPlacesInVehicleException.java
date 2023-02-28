package vitalkrilov.itmo.prog.lab34.others;

public class NoPlacesInVehicleException extends RuntimeException {
    public String getDescription() {
        return "Текущее транспортное средство, увы, уже занято.";
    }
}