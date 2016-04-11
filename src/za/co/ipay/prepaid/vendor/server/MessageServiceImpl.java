package za.co.ipay.prepaid.vendor.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jdom.Document;
import org.jdom.transform.JDOMSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ipay.prepaid.vendor.client.MessageService;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.message.GenerateMessage;
import za.co.ipay.prepaid.vendor.message.TransformResponse;
import za.co.ipay.prepaid.vendor.util.CreateSocketConnection;
import za.co.ipay.prepaid.vendor.util.ReferenceNumberGenerator;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

    private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    public String sendMessageAndGetResponse(String meterNumber, String amount, String noOfToken,
                                            String payType, Integer lastTransNo) {
        System.out.println("Inside Send Message");
        try {
            Socket socket = CreateSocketConnection.getSocket();
            logger.info("Connection successful");
            System.out.println("Conn Successful");


            Document document =
                    new GenerateMessage(meterNumber, amount, noOfToken, payType,
                            ReferenceNumberGenerator.nextReferenceNumber(lastTransNo)).getRequestMessage();

            JDOMSource jdomSource = new JDOMSource(document);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Result result = new StreamResult(byteArrayOutputStream);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(jdomSource, result);

            byte[] bytesXml =  byteArrayOutputStream.toByteArray();

            logger.info("LENGTH >> " + bytesXml.length);
            System.out.println("LENGTH >> " + bytesXml.length);

            OutputStream out = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(out);

            dataOutputStream.writeInt(bytesXml.length);
            dataOutputStream.write(bytesXml);

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            /*OutputStream outputStream =
                    new FileOutputStream(new File("C:\\devtools\\Projects\\POC\\ipayResponse.xml"));*/


            int len = dataInputStream.readUnsignedShort();

            byte[] bytes = new byte[len];
            if(len > 0) {
                dataInputStream.readFully(bytes);
            }

            dataOutputStream.close();
            out.close();
            dataInputStream.close();
            inputStream.close();
            socket.close();

            return new String(bytes);

        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ElecTransactionDTO transferResponse(String response) {
        try {
            return new TransformResponse().transform(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------------" + e.getMessage());
            return null;
        }
    }
}