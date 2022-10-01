package src.pakage.Client;

import src.pakage.JsonHandler;
import src.pakage.info.ClientInfo;
import src.pakage.info.PackageOpcodes;
import src.pakage.send.SendTAG;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String Ip = ClientInfo.linkIp;
    private final int Port = ClientInfo.linkPort;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    private String message = null;

    private int opcode = PackageOpcodes.NONE;

    public void init() {
        try {
            socket = new Socket(Ip, Port);
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));//服务器回应客户端
            JsonHandler pac = new JsonHandler();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            ps = new PrintStream(socket.getOutputStream());

            message = pac.Package(SendTAG.HEAD.CLIENT, PackageOpcodes.HandShake0); //发送连接请求
            ps.println(message);

            do {
                if ((message = brServer.readLine()) != null) {
                    JsonHandler handler = new JsonHandler();
                    SendTAG sendTAG = handler.Read(message);
                    opcode = sendTAG.getOpcode();

                    System.out.print(sendTAG.getAll());
                }
            } while (opcode == PackageOpcodes.HandShake_Request);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
