package com.demo;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.*;

public class PCInfo {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("System t ray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("/images/settings.png", "PCInfo"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Network Settings
        NetworkProperties netProperties = new NetworkProperties();
        netProperties.initNetworkSettings();

        // prepare a config handler
        ConfigFileHandler configHandler = new ConfigFileHandler();

        // Read config file
        File confFile = new File("src/config/config.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        Document configXMLFile = null;

        // Trying to open the config file
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            configXMLFile = documentBuilder.parse(confFile);
            configXMLFile.getDocumentElement().normalize();

            configHandler.populateMenuOptions(configXMLFile);
            configHandler.populateMessageContent(configXMLFile);

        // If configuration file is missing or malformed
        // Will be loaded default settings
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            configHandler.populateWithDefaultSettings();
        } catch (SAXException e) {
            e.printStackTrace();
            configHandler.populateWithDefaultSettings();
        } catch (IOException e) {
            e.printStackTrace();
            configHandler.populateWithDefaultSettings();
        }


        /** Menu section **/
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem(configHandler.getMenuGeneralInformations());
        Menu displayMenu = new Menu(configHandler.getMenuDisplay());
        MenuItem pcNameInfo = new MenuItem(configHandler.getMenuPCInfo());
        MenuItem IPInfo = new MenuItem(configHandler.getMenuIPInfo());
        MenuItem exitItem = new MenuItem(configHandler.getMenuExit());
        MenuItem moreInfoItem = new MenuItem(configHandler.getMenuMoreInformations());

        Menu cmdItem = new Menu(configHandler.getMenuCMDName());
        MenuItem policyUpdate = new MenuItem(configHandler.getMenuPolicyUpdate());
        MenuItem stopShutdowm = new MenuItem(configHandler.getMenuStopShutdown());
        MenuItem ipconfigAll = new MenuItem(configHandler.getMenuIPconfigAll());
        MenuItem ipconfigFLushDNS = new MenuItem(configHandler.getMenuIPconfigFlushDNS());
        MenuItem ipconfigRenew = new MenuItem(configHandler.getMenuIPconfigRenew());
        MenuItem openCMD = new MenuItem(configHandler.getMenuOpenCMD());


        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(displayMenu);
            displayMenu.add(pcNameInfo);
            displayMenu.add(IPInfo);
            displayMenu.addSeparator();
            displayMenu.add(moreInfoItem);
        popup.add(cmdItem);
            cmdItem.add(policyUpdate);
            cmdItem.add(stopShutdowm);
            cmdItem.add(ipconfigAll);
            cmdItem.add(ipconfigFLushDNS);
            cmdItem.add(ipconfigRenew);
            cmdItem.addSeparator();
            cmdItem.add(openCMD);
        popup.addSeparator();
        popup.add(exitItem);

        // Tray icon's options
        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("PCInfo" + "\n"
                        + configHandler.getMessageShowHostName()
                        + ": " + netProperties.getHostName());
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        // Show message dialog after double clicking on tray icon
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                netProperties.initNetworkSettings();

                JOptionPane.showMessageDialog(null,
                                                configHandler.displayMessage(netProperties),
                                                configHandler.getMessageTitle(),
                                                JOptionPane.INFORMATION_MESSAGE);
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                netProperties.initNetworkSettings();

                JOptionPane.showMessageDialog(null,
                                                configHandler.displayMessage(netProperties),
                                                configHandler.getMessageTitle(),
                                                JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MenuItem item = (MenuItem)e.getSource();
                String labelOption = item.getLabel();

                netProperties.initNetworkSettings();

                // Promnt Hostname
                if(labelOption.equals(configHandler.getMenuPCInfo())){
                    trayIcon.displayMessage(configHandler.getMessageShowHostName(),
                            "" + netProperties.getHostName(), TrayIcon.MessageType.NONE);
                }

                // Promnt IP address
                if (labelOption.equals(configHandler.getMenuIPInfo())){
                        trayIcon.displayMessage(configHandler.getMessageShowIPAddress(),
                                "" + netProperties.getIPAddress(), TrayIcon.MessageType.NONE);
                }
            }
        };

        policyUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand("gpupdate /force");
            }
        });

        stopShutdowm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand("shutdown /a");
            }
        });

        ipconfigAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand("ipconfig /all");
            }
        });

        ipconfigFLushDNS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand("ipconfig /flushdns");
            }
        });

        ipconfigRenew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand("ipconfig /renew");
            }
        });

        openCMD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.runCmdCommand();
            }
        });

        moreInfoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdHandler.executeCmdCommand("control /name Microsoft.System");
            }
        });

        pcNameInfo.addActionListener(listener);
        IPInfo.addActionListener(listener);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = PCInfo.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
