package src.pakage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import name.finsterwalder.fileutils.FileUtils;
import src.pakage.info.Error;
import src.pakage.info.User;
import src.pakage.send.SendTAG;

import java.io.IOException;

public class JsonHandler {

    private SendTAG sendTAG;



    public String Package(SendTAG.HEAD head, int opcode) {
        SendTAG sendTAG = new SendTAG(head, opcode);
        return Package(sendTAG);
    }

    public String Package(SendTAG.HEAD h, int opcode, User u, String m) {
        SendTAG sendTAG = new SendTAG(h, opcode, u, m);
        return Package(sendTAG);
    }
    private String Package(SendTAG sendTAG){

        Gson gson = new Gson();

        try {
            return gson.toJson(sendTAG);

        } catch (Exception e) {
            System.out.println("Error");
        }
        return String.valueOf(Error.MISS_VAR);
    }


    public SendTAG Read(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, SendTAG.class);
    }

}
