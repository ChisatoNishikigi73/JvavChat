package src.pakage.Server;

import com.sun.source.tree.PackageTree;
import src.pakage.JsonHandler;
import src.pakage.info.PackageOpcodes;
import src.pakage.info.ServerInfo;
import src.pakage.send.SendTAG;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HandShakeCenterThread extends Thread{
    BufferedReader br = null;
    private PrintStream ps;

    @Override
    public void run() {
        super.run();

        try {
            ServerSocket ss = new ServerSocket(ServerInfo.centerPort) ;
            Socket center = ss.accept();
            ServerThread handShakeCenter = new ServerThread(center);
            br = new BufferedReader(new InputStreamReader(center.getInputStream()));
            ps = new PrintStream(center.getOutputStream());

            String input = null;

            while((input = br.readLine()) != null) {
                SendTAG a = new JsonHandler().Read(input);

                if (a.getOpcode() == PackageOpcodes.HandShake0 && a.getHead().equals(SendTAG.HEAD.CLIENT)) {
                    JsonHandler handler = new JsonHandler();
                    String handShakeRequest = handler.Package(SendTAG.HEAD.SERVER, PackageOpcodes.HandShake_Request);

                    ps.println(handShakeRequest);
                    System.out.println(a.getAll());

                    ss.close();
                    HandShakeCenterThread centerThread = new HandShakeCenterThread();
                    centerThread.start();

                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
