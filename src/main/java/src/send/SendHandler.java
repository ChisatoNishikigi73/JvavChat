package src.send;

import com.google.gson.Gson;
import src.info.PackageOpcodes;
import src.info.SendTAG;
import src.info.User;
import src.info.Error;

public class SendHandler {

    private SendTAG sendTAG;


    public String PackageHandshake(SendTAG.HEAD head, int opcode) { // 握手
        SendTAG sendTAG = new SendTAG(head, opcode);
        return Package(sendTAG);
    }


    public static class reqPackage {
        public String PackagePublicMessage(int opcode, User u, String m) {
            SendTAG sendTAG = new SendTAG(SendTAG.HEAD.CLIENT, opcode, u, m);
            return Package(sendTAG);
        }

        public String PackageInfoReq(int id, User user) {
            SendTAG sendTAG = new SendTAG(SendTAG.HEAD.CLIENT, PackageOpcodes.HandShake_Info, id, user);
            return Package(sendTAG);
        }
    }


    public static class rspPackage {
        public String PackageRegister(int counter) {
            SendTAG sendTAG = new SendTAG(SendTAG.HEAD.SERVER, PackageOpcodes.HandShake_register, counter);
            return Package(sendTAG);
        }
    }




    public static String Package(SendTAG sendTAG){

        Gson gson = new Gson();

        try {
            return gson.toJson(sendTAG);

        } catch (Exception e) {
            System.out.println("Error");
        }
        return String.valueOf(Error.MISS_VAR);
    }






}
