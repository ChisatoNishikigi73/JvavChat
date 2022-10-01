package src.pakage.send;

import com.google.gson.annotations.SerializedName;
import src.pakage.info.PackageOpcodes;
import src.pakage.info.User;

public class SendTAG{
    public enum HEAD {
        CLIENT, SERVER
    }

    private HEAD head = null;
    private int opcode = PackageOpcodes.NONE;
    @SerializedName("USER")
    private User username = null;
    @SerializedName("TAR")
    private User targetUsername = null;
    @SerializedName("MSG")
    private String message = null;
    @SerializedName("TIME")
    private long sendTime = 0;

    public SendTAG() {
        sendTime = System.currentTimeMillis();
    }

    public SendTAG(HEAD h, int opcode, User u, String m) {
        sendTime = System.currentTimeMillis();
        this.head = h;
        this.opcode = opcode;
        this.username = u;
        this.message = m;

    }

    public SendTAG(HEAD head, int opcode) {
        sendTime = System.currentTimeMillis();
        this.head = head;
        this.opcode = opcode;
    }

    public SendTAG(HEAD head, int opcode, User user, User target, String message) {
        sendTime = System.currentTimeMillis();

    }

    public String getAll() {
        String a = String.format("head: %s, tag: %s, username: %s, targetUsername: %s, message: %s, time: %s ",head, opcode, username, targetUsername, message, sendTime);
        return a;
    }

    public HEAD getHead() {
        return head;
    }

    public int getOpcode() {
        return opcode;
    }

    public User getUsername() {
        return username;
    }

    public User getTargetUsername() {
        return targetUsername;
    }

    public String getMessage() {
        return message;
    }

    public long getSendTime() {
        return sendTime;
    }
}
