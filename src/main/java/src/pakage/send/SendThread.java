package src.pakage.send;

import src.pakage.info.User;
import src.pakage.info.ServerInfo;

import java.io.*;
import java.net.Socket;

public class SendThread extends Thread{


    private User user;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    String message = null;
    private SendTAG.HEAD HEAD;

    public SendThread(SendTAG.HEAD head) {
        this.HEAD = head;
    }

    public void Super(SendTAG.HEAD head) {
        this.HEAD = head;
    }

    @Override
    public void run() {
        super.run();




        if (HEAD == SendTAG.HEAD.CLIENT) {
            try {
                socket = new Socket(ServerInfo.hostIp, ServerInfo.hostPort);
                ps = new PrintStream(socket.getOutputStream());

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                while(true){
                    message = br.readLine();
                    //Packager pac = new Packager(message);

                    pw.println("小一说："+br.readLine());
                    //System.out.print([]);
                    pw.flush();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }





    }
}
