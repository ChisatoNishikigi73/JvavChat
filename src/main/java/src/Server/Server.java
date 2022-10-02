package src.Server;

import src.info.ServerInfo;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class Server {
    private static final String Ip = ServerInfo.hostIp;
    private static final int Port = ServerInfo.hostPort;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    private String message = null;


    public void init() {
        HandShakeCenterThread centerThread = new HandShakeCenterThread();
        centerThread.start();

    }
}
