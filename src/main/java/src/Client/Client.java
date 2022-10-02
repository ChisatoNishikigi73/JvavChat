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
    private final String Ip = ClientInfo.linkIp;
    private final int Port = ClientInfo.linkPort;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    private String input = null;


    public void init() {
        try {
            socket = new Socket(Ip, Port);
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));//服务器回应客户端
            SendHandler pac = new SendHandler();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            ps = new PrintStream(socket.getOutputStream());

            input = pac.PackageHandshake(SendTAG.HEAD.CLIENT, PackageOpcodes.HandShake0); //发送连接请求
            ps.println(input);


            while((input = brServer.readLine()) != null) {
                SendTAG sendTAG = new JsonHandler().Read(input);
                int opcode = sendTAG.getOpcode();

                switch (opcode) {
                    case 201:
                        int id = sendTAG.getId();



                        while(sendTAG.getUser().getUID() <= 0) {
                            JTextField xField = new JTextField(5);
                            JTextField yField = new JTextField(5);
                            JPanel myPanel = new JPanel();
                            myPanel.add(new JLabel("用户名:"));
                            myPanel.add(xField);
                            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                            myPanel.add(new JLabel("UID:"));
                            myPanel.add(yField);
                            int result = JOptionPane.showConfirmDialog(null, myPanel,
                                    "请输入用户名和UID", JOptionPane.OK_CANCEL_OPTION);
                            sendTAG.getUser().setUserName(xField.getText());
                            try {
                                sendTAG.getUser().setUID(Integer.parseInt(yField.getText()));
                            }catch (Exception ignored) {}
                        }





                        input = new SendHandler.reqPackage().PackageInfoReq(id, sendTAG.getUser());
                        System.out.println(input);
                        ps.println(input);
                }
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
