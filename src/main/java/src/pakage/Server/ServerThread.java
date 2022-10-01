package src.pakage.Server;

import src.pakage.JsonHandler;
import src.pakage.send.SendTAG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //一个客户端的输出流对象
            ps = new PrintStream(socket.getOutputStream());
            String input = null;
            while((input = br.readLine()) != null) {
                SendTAG a = new JsonHandler().Read(input);
                System.out.println(a.getAll());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
