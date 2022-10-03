package src.info;

import com.google.gson.annotations.SerializedName;

public class SendTAG{
    public enum HEAD {
        CLIENT, SERVER
    }

    private HEAD head = null;
    private int opcode = PackageOpcodes.NONE;
    private int id;
    @SerializedName("USER")
    private User user = new User();
    @SerializedName("TAR")
    private User targetUser = null;

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
        this.user = u;
        this.message = m;

    }

    public SendTAG(HEAD head, int opcode, int counter) { // 服务器分配id
        this.head = head;
        this.opcode = opcode;
        this.id = counter;
    }

    public SendTAG(HEAD head, int opcode) {
        sendTime = System.currentTimeMillis();
        this.head = head;
        this.opcode = opcode;
    }

    public SendTAG(HEAD head, int opcode, User user, User target, String message) {
        sendTime = System.currentTimeMillis();

    }

    public SendTAG(HEAD head, int opcode, User user, int id) {
        sendTime = System.currentTimeMillis();
        this.head = head;
        this.opcode = opcode;
        this.user = user;
        this.id = id;
    }

    public SendTAG(HEAD head, int opcode, int id, User user) {
        sendTime = System.currentTimeMillis();
        this.head = head;
        this.opcode = opcode;
        this.id = id;
        this.user = user;

    }

    @Deprecated
    public String getAll() {
        String a = String.format("head: %s, tag: %s, username: %s, targetUsername: %s, message: %s, time: %s ",head, opcode, user, targetUser, message, sendTime);
        return a;
    }

    public HEAD getHead() {
        return head;
    }

    public int getOpcode() {
        return opcode;
    }

    public User getUser() {
        return user;
    }

    public void setHead(HEAD head) {
        this.head = head;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.user.setUserName(username);
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public String getMessage() {
        return message;
    }

    public long getSendTime() {
        return sendTime;
    }

    public int getId() {
        return id;
    }
}
