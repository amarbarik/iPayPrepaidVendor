package za.co.ipay.prepaid.vendor.builder;

import za.co.ipay.prepaid.vendor.domain.PayType;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class PayTypeBuilder {

    private int enable;
    private String name;
    private String description;

    public PayTypeBuilder(int enable, String name, String description) {
        this.enable = enable;
        this.name = name;
        this.description = description;
    }

    public PayType build() {
        PayType payType = new PayType();
        payType.setEnabled(enable);
        payType.setName(name);
        payType.setDescription(description);
        return payType;
    }
}
