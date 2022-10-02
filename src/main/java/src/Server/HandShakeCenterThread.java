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
            ServerThread handShakeCenter = new ServerThread(center);
            br = new BufferedReader(new InputStreamReader(center.getInputStream()));
            ps = new PrintStream(center.getOutputStream());

            String input = null;

            while((input = br.readLine()) != null) {
                SendTAG a = new JsonHandler().Read(input);
                System.out.println(PackageOpcodes.CLIENT + input);

                switch (a.getOpcode()) {
                    default:
                        System.out.print(PackageOpcodes.SERVER);
                    case 200: //HandShake0
                        int counter = new Database().getCounter();
                        new Database().addId();
                        System.out.println(counter);
                        String handShakeRequest = new SendHandler.rspPackage().PackageRegister(counter);

                        ps.println(handShakeRequest);
                        System.out.println(handShakeRequest);

                        break;

                    case 203://Register
                        System.out.println(PackageOpcodes.SERVER + "Register: " + input);
                        SendTAG sendTAG = new JsonHandler().Read(input);


                        new Database().setUserName(sendTAG.getUser().getUID(), sendTAG.getUser().getUserName());
                        break;
                }

                ss.close();
                HandShakeCenterThread centerThread = new HandShakeCenterThread();
                centerThread.start();
                break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
