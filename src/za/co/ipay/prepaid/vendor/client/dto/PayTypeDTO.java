package za.co.ipay.prepaid.vendor.client.dto;

import za.co.ipay.prepaid.vendor.domain.PayType;

import java.io.Serializable;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class PayTypeDTO implements Serializable {

    private int id;
    private String name;
    private String description;
    private int enabled;

    public PayTypeDTO(int id, String name, String description, int enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public PayTypeDTO(int id) {
        this.id = id;
    }

    public PayTypeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
