/**
 *
 * This class helps to handle network and pc settings
 * it is use for display pc name and internet address
 *
 */

package com.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkProperties {

    private String IPAddress;
    private String hostName;
    private String userName;

    public NetworkProperties() {
    }

    public void initNetworkSettings(){

        // Clear previous data about network settings
        IPAddress = "";
        hostName = "";
        userName = "";

        // Trying to get network settings
        try {
            // Using InetAddress class we can get pc's IP and domain name
            InetAddress IP = InetAddress.getLocalHost();
            hostName += IP.getHostName();

            // Better way to get IP address - socket method
            // We are using network interface which obtain connection "outside"
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80));

            IPAddress += socket.getLocalAddress();
            IPAddress = IPAddress.replace("/","");

            // Get current user's logon name
            userName = System.getProperty("user.name");

        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public String getUserName() {
        return userName;
    }

}
