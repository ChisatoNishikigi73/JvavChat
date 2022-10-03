package src;


import src.Client.Client;
import src.Server.Server;
import src.json.Database;

import java.io.IOException;

public class ChatDemo {

    public enum mode {
        CLIENT_MODE, SERVER_MODE
    }
    public static void main(String[] args) throws IOException {
        System.out.print("start");
        if (1==2) {
            Database.init();
            Server server = new Server();
            server.init();
        }else {
            Client client = new Client();
            client.init();
        }

    }
}
