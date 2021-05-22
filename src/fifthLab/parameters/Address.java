package fifthLab.parameters;

public class Address {
    private String zipCode; //Поле может быть null
    private Location town; //Поле может быть null

    public String getZipCode() {
        return zipCode;
    }

    public Location getLocation() {
        return town;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setLocation(Location location) {
        this.town = town;
    }
}
