import java.util.HashMap;
import java.util.Map;

class Vehicle {
    private String registrationNumber;
    private String color;
    private String type;

    public Vehicle(String registrationNumber, String color, String type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

class ParkingLot {
    Map<String, Vehicle> vehicleMap = new HashMap<String, Vehicle>();

    public void parkVehicle(String location, Vehicle v) {
        if (vehicleMap.containsKey(location)) {
            System.out.println(String.format("location %s is occupied", location));
            return;
        }
        vehicleMap.put(location, v);
    }

    public void vacateVehicle(String location) {
        if (vehicleMap.remove(location) != null) {
            System.out.println(String.format("location %s is vacated", location));
        } else {
            System.out.println(String.format("fail to vacate location %s", location));
        }
    }

    public Vehicle findVehicle(String regNumber) {
        var vehicle = vehicleMap.values().stream()
                .filter(v -> v.getRegistrationNumber().equals(regNumber))
                .findFirst();
        if (vehicle.isPresent()) return vehicle.get();
        return null;
    }

    public void display() {
        for (var k: vehicleMap.keySet()) {
            System.out.println(k + " : " + vehicleMap.get(k));
        }
    }
}

public class ParkingApplication {
    public static void main(String args[]) {
        Vehicle v1 = new Vehicle("LKG148", "silver", "Sedan");
        Vehicle v2 = new Vehicle("IBM320", "red", "RV");
        Vehicle v3 = new Vehicle("NEWZEAL", "black", "Sedan");
        Vehicle v4 = new Vehicle("LAND123", "blue", "Sparts");
        Vehicle v5 = new Vehicle("KOREA000", "silver", "RV");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.parkVehicle("1", v1);
        parkingLot.parkVehicle("2", v2);
        parkingLot.parkVehicle("3", v3);
        parkingLot.parkVehicle("2", v4);
        parkingLot.parkVehicle("5", v5);

        parkingLot.vacateVehicle("10");
        parkingLot.vacateVehicle("3");

        parkingLot.display();
    }
}
