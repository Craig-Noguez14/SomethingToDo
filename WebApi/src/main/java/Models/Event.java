package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {
    public String Description;

    @SerializedName("StartOn")
    @Expose
    public Date StartOn;

    @SerializedName("ExpiresOn")
    @Expose
    public Date ExpiresOn;

    @SerializedName("CreatedBy")
    @Expose
    public String CreatedBy;

    public EventType Type;

    @SerializedName("SubCategory")
    @Expose
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
