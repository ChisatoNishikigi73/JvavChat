package src;

import src.pakage.Client.Client;
import src.pakage.Server.Server;

public class ChatDemo {

    public enum mode {
        CLIENT_MODE, SERVER_MODE
    }
    public static void main(String[] args) {
        System.out.print("start");

//        Server server = new Server();
//        server.init();

        Client client = new Client();
        client.init();
    }
}
