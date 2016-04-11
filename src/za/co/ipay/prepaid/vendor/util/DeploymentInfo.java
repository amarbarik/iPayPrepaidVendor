package za.co.ipay.prepaid.vendor.util;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by F4742443 on 2016/04/10.
 */
public class DeploymentInfo {

    private String version;
    private String client;
    private String currency;
    private String useAdv;
    private String  sequenceNum;
    private String term;

    public DeploymentInfo() {
        try {
            init();
        } catch (IOException e) {
            System.out.println("Error While initializing Properties: " + e.getMessage());
        }
    }

    public void init() throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        if (in == null) {
            throw new IllegalStateException("config.properties resource not found");
        }

        Properties props = new Properties();
        props.load(in);
        version = props.getProperty("version", "(missing)");
        client = props.getProperty("client", "(missing)");
        currency = props.getProperty("currency", "(missing)");
        useAdv = props.getProperty("useAdv", "(missing)");
        sequenceNum = props.getProperty("sequenceNum", "(missing)");
        term = props.getProperty("term", "(missing)");
        in.close();

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUseAdv() {
        return useAdv;
    }

    public void setUseAdv(String useAdv) {
        this.useAdv = useAdv;
    }

    public String getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
