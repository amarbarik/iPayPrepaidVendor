package za.co.ipay.prepaid.vendor.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by F4742443 on 2016/03/08.
 */
@Entity(name = "pay_type")
public class PayType implements Serializable{

    private static final int ENABLED = 1;
    private static final int DISABLED = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_type_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column
    private String description;
    @Column
    private int enabled;
    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
