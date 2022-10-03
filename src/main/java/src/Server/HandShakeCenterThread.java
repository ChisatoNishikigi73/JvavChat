package src.Server;

import src.info.PackageOpcodes;
import src.info.SendTAG;
import src.info.ServerInfo;
import src.json.Database;
import src.json.JsonHandler;
import src.send.SendHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
            br = new BufferedReader(new InputStreamReader(center.getInputStream()));
            ps = new PrintStream(center.getOutputStream());

            String input = null;

            while((input = br.readLine()) != null) {
                SendTAG a = new JsonHandler().Read(input);

                System.out.println(PackageOpcodes.CLIENT + input);

                switch (a.getOpcode()) {
                    case 200: //HandShake0
                        System.out.print(PackageOpcodes.SERVER);

                        int counter = new Database().getCounter();
                        new Database().addId();
                        String handShakeRequest = new SendHandler.rspPackage().PackageRegister(counter);

                        ps.println(handShakeRequest);
                        System.out.println("HandShake0" + handShakeRequest);
                        break;
                    case 203://Register
                        SendTAG sendTAG = new JsonHandler().Read(input);

                        new Database().addUser(sendTAG.getId(), sendTAG.getUser());
                        break;
                    default:
                        System.out.println("失败");
                }

                ss.close();
                center.close();
                br.close();
                HandShakeCenterThread centerThread = new HandShakeCenterThread();
                centerThread.start();
                break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
