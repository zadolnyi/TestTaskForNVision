package vit.model;

import javax.xml.bind.annotation.*;

/**
 * Created by zadolnyi on 18.01.2019.
 */
@XmlRootElement(name = "job")
public class InputJob {

    private String id;
    private String type;
    private String user;
    private String device;
    private int amount;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    @XmlElement
    public String getUser() {
        return user;
    }

    @XmlElement
    public String getDevice() {
        return device;
    }

    @XmlElement
    public int getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}