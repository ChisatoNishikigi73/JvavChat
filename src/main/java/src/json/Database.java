package src.json;

import src.info.ServerInfo;
import src.info.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<Integer, User> data = new HashMap<>();
    private int counter;

    public static void init() throws FileNotFoundException {
        File file = new File(ServerInfo.databaseFile.toURI());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new Database("init");

            System.out.println("未找到数据库文件，已重新创建");
        } else {
            System.out.println("数据库初始化成功");
        }
    }

    public Database(String usage) {
        switch (usage) {
            case "null":
                break;
            case "init":
                this.data.put(0,new User("Admin", 23333, "kawaa-qwq@github.com"));
                counter = 1;
                save();

        }
    }

    public Database(){

    }

    public void addId(){
        pullDatabase();

        data.put(counter,new User(counter));
        counter++;
        save();
    }

    public void addUser(int id, User user) {
        pullDatabase();
        data.put(id, user);
        save();
    }

    public int getCounter() {
        pullDatabase();
        return counter;
    }

    public void setUserName(int id, String username){
        pullDatabase();
        if (id >= data.size()) {
            return;
        }
        User user = data.get(id);
        user.setUserName(username);
        save();
    }


    public Map<Integer, User> getData() {
        pullDatabase();
        return data;
    }

    public void setData(Map<Integer, User> data) {
        pullDatabase();
        this.data = data;
    }

    public void setCounter(int counter) {
        pullDatabase();
        this.counter = counter;
        save();
    }


    public void save(){

        new JsonHandler().SaveDatabase(this);
    }

    public void pullDatabase() {
        try {
            Database database = new JsonHandler().ReadFromResource(new File(ServerInfo.databaseFile.toURI()));

            this.data = database.data;
            this.counter = database.counter;

        }catch (Exception ignored) {

        }

    }


}

