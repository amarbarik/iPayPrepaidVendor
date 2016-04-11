package za.co.ipay.prepaid.vendor.client.dto;

import za.co.ipay.prepaid.vendor.domain.Meter;

import java.io.Serializable;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class MeterDTO implements Serializable {

    private int id;
    private String meterNumber;
    private String address;
    private int enabled;

    public MeterDTO(int id, String meterNumber, String address, int enabled) {
        this.id = id;
        this.meterNumber = meterNumber;
        this.address = address;
        this.enabled = enabled;
    }

    public MeterDTO(int id) {
        this.id = id;
    }

    public MeterDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
