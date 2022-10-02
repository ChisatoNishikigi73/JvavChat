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
            case "init":
                this.data.put(0,new User("Admin", 23333, "kawaa-qwq@github.com"));
                counter = 1;
                save(this);
        }
    }
    public Database() throws FileNotFoundException { // init

        try {
            Database database = new JsonHandler().ReadFromResource(new File(ServerInfo.databaseFile.toURI()));

            this.data = database.getData();
            this.counter = database.getCounter();

        }catch (Exception ignored) {

        }

    }

    public void addId(){
        data.put(counter,new User(counter));
        counter++;
        save(this);
    }

    public void addUser(int id, User user) {
        data.put(id, user);
        save(this);
    }

    public int getCounter() {
        return counter;
    }

    public void setUserName(int id, String username){
        if (id >= data.size()) {
            return;
        }
        User user = data.get(id);
        user.setUserName(username);
        save(this);
    }


    public Map<Integer, User> getData() {
        return data;
    }

    public void setData(Map<Integer, User> data) {
        this.data = data;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        save(this);
    }


    public void save(Database database) {
        this.data = database.getData();
        this.counter = database.getCounter();
        new JsonHandler().SaveDatabase(this);

    }


}

