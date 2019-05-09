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
    private String menuExit;

    //messages handler
    private String messageTitle;
    private String messageUser;
    private String messageShowHostName;
    private String messageShowIPAddress;

    public ConfigFileHandler() {
    }

    public ConfigFileHandler(String menuGeneralInformations, String menuDisplay, String menuPCInfo,
                             String menuIPInfo, String menuExit, String messageTitle, String messageUser,
                             String messageShowHostName, String messageShowIPAddress) {
        this.menuGeneralInformations = menuGeneralInformations;
        this.menuDisplay = menuDisplay;
        this.menuPCInfo = menuPCInfo;
        this.menuIPInfo = menuIPInfo;
        this.menuExit = menuExit;
        this.messageTitle = messageTitle;
        this.messageUser = messageUser;
        this.messageShowHostName = messageShowHostName;
        this.messageShowIPAddress = messageShowIPAddress;
    }

    public void populateMenuOptions(Document configXMLFile){

        Element eElement;
        //System.out.println("Root element :" + configXMLFile.getDocumentElement().getNodeName());
        NodeList nList = configXMLFile.getElementsByTagName("display");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;

                //System.out.println("Display : " + eElement.getAttribute("id"));
                menuDisplay = eElement.getAttribute("id");
                menuPCInfo = eElement.getElementsByTagName("pcInfo").item(0).getTextContent();
                menuIPInfo = eElement.getElementsByTagName("ipInfo").item(0).getTextContent();
            }

            menuGeneralInformations = configXMLFile.getElementsByTagName("generalInformations").item(0).getTextContent();
            menuExit = configXMLFile.getElementsByTagName("exit").item(0).getTextContent();
        }
    }

    public void populateMessageContent(Document configXMLFile){

        Element eElement;
        //System.out.println("Root element :" + configXMLFile.getDocumentElement().getNodeName());
        NodeList nList = configXMLFile.getElementsByTagName("message");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;
                messageTitle = eElement.getElementsByTagName("messageTitle").item(0).getTextContent();
                messageUser = eElement.getElementsByTagName("messageUser").item(0).getTextContent();
                messageShowHostName = eElement.getElementsByTagName("messagePCName").item(0).getTextContent();
                messageShowIPAddress = eElement.getElementsByTagName("messageIPAddress").item(0).getTextContent();
            }
        }
    }

    public void populateWithDefaultSettings(){

        menuGeneralInformations = "About";
        menuDisplay = "Show...";
        menuPCInfo = "Host name";
        menuIPInfo = "Ip Address";
        menuExit = "Exit";

        messageTitle = "PCInfo";
        messageUser = "User";
        messageShowHostName = "Your host name";
        messageShowIPAddress = "Your IP address";
    }

    public String getMenuGeneralInformations() {
        return menuGeneralInformations;
    }

    public void setMenuGeneralInformations(String menuGeneralInformations) {
        this.menuGeneralInformations = menuGeneralInformations;
    }

    public String getMenuDisplay() {
        return menuDisplay;
    }

    public void setMenuDisplay(String menuDisplay) {
        this.menuDisplay = menuDisplay;
    }

    public String getMenuPCInfo() {
        return menuPCInfo;
    }

    public void setMenuPCInfo(String menuPCInfo) {
        this.menuPCInfo = menuPCInfo;
    }

    public String getMenuIPInfo() {
        return menuIPInfo;
    }

    public void setMenuIPInfo(String menuIPInfo) {
        this.menuIPInfo = menuIPInfo;
    }

    public String getMenuExit() {
        return menuExit;
    }

    public void setMenuExit(String menuExit) {
        this.menuExit = menuExit;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageShowHostName() {
        return messageShowHostName;
    }

    public void setMessageShowHostName(String messageShowHostName) {
        this.messageShowHostName = messageShowHostName;
    }

    public String getMessageShowIPAddress() {
        return messageShowIPAddress;
    }

    public void setMessageShowIPAddress(String messageShowIPAddress) {
        this.messageShowIPAddress = messageShowIPAddress;
    }
}
