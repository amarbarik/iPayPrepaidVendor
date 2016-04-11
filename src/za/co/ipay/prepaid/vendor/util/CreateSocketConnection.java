package za.co.ipay.prepaid.vendor.util;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by F4742443 on 2016/03/11.
 */
public class CreateSocketConnection {

    public static Socket socket;

    public static Socket getSocket() throws IOException {
        System.out.println("Inside Create Connections");
        String HOST = "www.bizswitch.net";
        socket = new Socket(HOST, 8879);
        return socket;
    }
}
