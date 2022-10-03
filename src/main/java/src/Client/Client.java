package src.Client;

import src.info.ClientInfo;
import src.info.PackageOpcodes;
import src.info.SendTAG;
import src.json.JsonHandler;
import src.send.SendHandler;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {



    public void init() {
        LoginThread loginThread = new LoginThread();
        loginThread.start();

    }
}
