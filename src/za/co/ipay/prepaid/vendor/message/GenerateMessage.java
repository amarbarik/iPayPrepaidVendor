package za.co.ipay.prepaid.vendor.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import za.co.ipay.prepaid.vendor.util.DeploymentInfo;
import za.co.ipay.prepaid.vendor.util.Util;

/**
 * Created by F4742443 on 2016/03/11.
 */
public class GenerateMessage implements Serializable {

    private Document doc;
    private DeploymentInfo deploymentInfo;

    public GenerateMessage() {
    }

    private String meterNumber;
    private String amount;
    private String noOfToken;
    private String payType;
    private String reference;

    public GenerateMessage(String meterNumber, String amount, String noOfToken, String payType, String reference) {
        this.meterNumber = meterNumber;
        this.amount = amount;
        this.noOfToken = noOfToken;
        this.payType = payType;
        this.reference = reference;
    }

    public Document getRequestMessage() {
        deploymentInfo = new DeploymentInfo();

        System.out.println("Inside Create Requests");

        Element ipayMsg = new Element("ipayMsg");
        ipayMsg.setAttribute(new Attribute("time", Util.formatToiPay(new Date())));
        ipayMsg.setAttribute(new Attribute("seqNum", deploymentInfo.getSequenceNum()));
        ipayMsg.setAttribute(new Attribute("term", deploymentInfo.getTerm()));
        ipayMsg.setAttribute(new Attribute("client", deploymentInfo.getClient()));

        doc = new Document(ipayMsg);

        Element elecMsg = new Element("elecMsg");
        elecMsg.setAttribute(new Attribute("ver", deploymentInfo.getVersion()));

        doc.getRootElement().addContent(elecMsg);

        Element vendReq = new Element("vendReq");
        vendReq.setAttribute(new Attribute("useAdv", deploymentInfo.getUseAdv()));

        vendReq.addContent(new Element("ref").setText(reference));
        Element amt = new Element("amt");
        amt.setAttribute("cur", deploymentInfo.getCurrency());
        vendReq.addContent(amt.setText(amount));
        vendReq.addContent(new Element("numTokens").setText(noOfToken));
        vendReq.addContent(new Element("meter").setText(meterNumber));
        vendReq.addContent(new Element("payType").setText(payType));

        elecMsg.addContent(vendReq);

        return doc;
    }
}
