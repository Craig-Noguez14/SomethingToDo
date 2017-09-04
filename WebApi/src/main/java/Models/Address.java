package Models;

public class Address {
    public int Id;
    public Double Longitude;
    public Double Latitude;
    public String Address;
    public String City;
    public String State;
    public String ZipCode;

    public Address(double longitude, double latitude, String address, String city, String state, String zip) {
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.Address = address;
        this.State = state;
        this.City = city;
        this.ZipCode = zip;
    }
}
