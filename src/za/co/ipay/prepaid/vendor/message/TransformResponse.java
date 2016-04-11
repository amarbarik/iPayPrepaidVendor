package za.co.ipay.prepaid.vendor.message;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.TokenDTO;
import za.co.ipay.prepaid.vendor.domain.Token;
import za.co.ipay.prepaid.vendor.util.Util;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by F4742443 on 2016/04/10.
 */
public class TransformResponse implements Serializable {

    public ElecTransactionDTO transform(String response) throws Exception {
        System.out.println(response);
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(new ByteArrayInputStream(response.getBytes()),
                    handler);
            return handler.getElecTransactionDTO();
    }
}
class SAXHandler extends DefaultHandler {

    private ElecTransactionDTO elecTransactionDTO = new ElecTransactionDTO();


    private TokenDTO stdToken;
    private TokenDTO bsstToken;

    public ElecTransactionDTO getElecTransactionDTO() {
        return elecTransactionDTO;
    }

    public void setElecTransactionDTO(ElecTransactionDTO elecTransactionDTO) {
        this.elecTransactionDTO = elecTransactionDTO;
    }

    String content = null;
    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        switch(qName){
            case "res":
                String code = attributes.getValue("code");
                System.out.println(code);
                elecTransactionDTO.setResponseCode(code);
                break;
            case "stdToken":
                stdToken = new TokenDTO();
                stdToken.setUnits(attributes.getValue("units"));
                stdToken.setReceiptNumber(attributes.getValue("rctNum"));
                stdToken.setAmount(attributes.getValue("amt"));
                stdToken.setTax(attributes.getValue("tax"));
                stdToken.setMessage(attributes.getValue("msg"));
                stdToken.setDate(Util.parseDate(attributes.getValue("trDate")));
                stdToken.setTokenType(TokenDTO.STD_TOKEN);
                break;
            case "bsstToken":
                bsstToken = new TokenDTO();
                bsstToken.setDate(Util.parseDate(attributes.getValue("trDate")));
                bsstToken.setUnits(attributes.getValue("units"));
                bsstToken.setAmount(attributes.getValue("amt"));
                bsstToken.setTax(attributes.getValue("tax"));
                bsstToken.setMessage(attributes.getValue("msg"));
                bsstToken.setTokenType(TokenDTO.BSST_TOKEN);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch(qName){
            case "elecMsg":
                System.out.println(content);
                elecTransactionDTO.getTokenDTOs().add(stdToken);
                elecTransactionDTO.getTokenDTOs().add(bsstToken);
                break;
            case "vendRes":
                System.out.println(content);
                break;
            case "ref":
                System.out.println(content);
                elecTransactionDTO.setReference(content);
                break;
            case "res":
                System.out.println(content);
                elecTransactionDTO.setResponse(content);
                break;
            case "stdToken":
                System.out.println(content);
                stdToken.setNumber(content);
                break;
            case "bsstToken":
                System.out.println(content);
                bsstToken.setNumber(content);
                break;
            case "customerMsg" :
                elecTransactionDTO.setCustomerMsg(content);
                break;
            case "rtlrMsg":
                elecTransactionDTO.setRtlrMsg(content);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}
