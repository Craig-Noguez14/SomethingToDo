package Models;

import java.util.Date;

public class Event {
    public String Description;

    public Date StartOn;

    public Date ExpiresOn;

    public String CreatedBy;

    public EventType Type;

    public SubCategory SubCategory;

    public Address Location;

    public Event(String description, Date startOn, Date expiresOn, String createdBy, EventType type, SubCategory subCategory, Address address) {
        this.Description = description;
        this.StartOn = startOn;
        this.ExpiresOn = expiresOn;
        this.CreatedBy = createdBy;
        this.Type = type;
        this.SubCategory = subCategory;
        this.Location = address;
    }
}
