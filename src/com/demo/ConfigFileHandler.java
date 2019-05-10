/**
 *
 * This class is responsible for handle names in menu
 * names are in config.xml file
 *
 */

package com.demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigFileHandler {

    // Menu handler
    private String menuGeneralInformations;
    private String menuDisplay;
    private String menuPCInfo;
    private String menuIPInfo;
    private String menuMoreInformations;
    private String menuExit;

    // cmd options in menu
    private String menuCMDName;
    private String menuPolicyUpdate;
    private String menuStopShutdown;
    private String menuIPconfigAll;
    private String menuIPconfigFlushDNS;
    private String menuIPconfigRenew;
    private String menuOpenCMD;

    //messages handler
    private String messageTitle;
    private String messageUser;
    private String messageShowHostName;
    private String messageShowIPAddress;

    public ConfigFileHandler() {
    }

    public void populateMenuOptions(Document configXMLFile){

        Element eElement;
        NodeList nList = configXMLFile.getElementsByTagName("display");

        // Populate Main Menu section
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;

                menuDisplay = eElement.getAttribute("id");
                menuPCInfo = eElement.getElementsByTagName("pcInfo").item(0).getTextContent();
                menuIPInfo = eElement.getElementsByTagName("ipInfo").item(0).getTextContent();
                menuMoreInformations = eElement.getElementsByTagName("moreInformations").item(0).getTextContent();
            }

            menuGeneralInformations = configXMLFile.getElementsByTagName("generalInformations").item(0).getTextContent();
            menuExit = configXMLFile.getElementsByTagName("exit").item(0).getTextContent();
        }

        // Populate CMD section
        nList = configXMLFile.getElementsByTagName("cmd");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;

                menuCMDName = eElement.getAttribute("id");
                menuPolicyUpdate = eElement.getElementsByTagName("policyUpdate").item(0).getTextContent();
                menuStopShutdown = eElement.getElementsByTagName("stopShutdown").item(0).getTextContent();
                menuIPconfigAll = eElement.getElementsByTagName("ipconfigAll").item(0).getTextContent();
                menuIPconfigFlushDNS = eElement.getElementsByTagName("ipconfigFlushdns").item(0).getTextContent();
                menuIPconfigRenew = eElement.getElementsByTagName("ipconfigRenew").item(0).getTextContent();
                menuOpenCMD = eElement.getElementsByTagName("openCmd").item(0).getTextContent();
            }
        }
    }

    public void populateMessageContent(Document configXMLFile){

        Element eElement;
        NodeList nList = configXMLFile.getElementsByTagName("message");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;
                messageTitle = eElement.getElementsByTagName("messageTitle").item(0).getTextContent();
                messageUser = eElement.getElementsByTagName("messageUser").item(0).getTextContent();
                messageShowHostName = eElement.getElementsByTagName("messagePCName").item(0).getTextContent();
                messageShowIPAddress = eElement.getElementsByTagName("messageIPAddress").item(0).getTextContent();
            }
        }
    }

    public String displayMessage(NetworkProperties netProperties){

        String message = "<html>" + "<b>"+ messageUser + ": </b>" + netProperties.getUserName()
                + "<br/>" + "<b>"+ messageShowHostName + ": </b>" + netProperties.getHostName()
                + "<br/>" + "<b>"+ messageShowIPAddress + ": </b>"
                + netProperties.getIPAddress() + "</html>";

        return message;
    }

    public void populateWithDefaultSettings(){

        menuGeneralInformations = "About";
        menuDisplay = "Show...";
        menuPCInfo = "Host name";
        menuIPInfo = "Ip Address";
        menuMoreInformations = "More...";
        menuExit = "Exit";

        messageTitle = "PCInfo";
        messageUser = "User";
        messageShowHostName = "Your host name";
        messageShowIPAddress = "Your IP address";

        menuCMDName = "CMD...";
        menuPolicyUpdate = "Group policy update";
        menuStopShutdown = "Stop PC restart";
        menuIPconfigAll = "Get all network settings";
        menuIPconfigFlushDNS = "Flush DNS";
        menuIPconfigRenew = "Renew IP";
        menuOpenCMD = "Open CMD console";
    }

    public String getMenuGeneralInformations() {
        return menuGeneralInformations;
    }

    public String getMenuDisplay() {
        return menuDisplay;
    }

    public String getMenuPCInfo() {
        return menuPCInfo;
    }

    public String getMenuIPInfo() {
        return menuIPInfo;
    }

    public String getMenuExit() {
        return menuExit;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public String getMessageShowHostName() {
        return messageShowHostName;
    }

    public String getMessageShowIPAddress() {
        return messageShowIPAddress;
    }

    public String getMenuCMDName() {
        return menuCMDName;
    }

    public String getMenuPolicyUpdate() {
        return menuPolicyUpdate;
    }

    public String getMenuStopShutdown() {
        return menuStopShutdown;
    }

    public String getMenuIPconfigAll() {
        return menuIPconfigAll;
    }

    public String getMenuIPconfigFlushDNS() {
        return menuIPconfigFlushDNS;
    }

    public String getMenuIPconfigRenew() {
        return menuIPconfigRenew;
    }

    public String getMenuOpenCMD() {
        return menuOpenCMD;
    }

    public String getMenuMoreInformations() {
        return menuMoreInformations;
    }
}
