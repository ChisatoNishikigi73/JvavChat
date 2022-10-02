package src.info;

import java.io.File;

public class ServerInfo {

    public static final File databaseFile = new File(System.getProperty("user.dir") + "/data.json");
    public static final String hostIp = "127.0.0.1";
    public static final int centerPort = 42000;
    public static final int hostPort = 42001;
}
