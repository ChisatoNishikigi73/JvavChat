package src.data;

import src.info.User;
import src.json.Database;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserMap {

    private Database database;

    public Map<Integer, User> map = Collections.synchronizedMap(new HashMap<Integer,User>());

    public void addUser(int id, User user) {
        map.put(id, user);
    }

    

    public static boolean fileExists (String path){
        return new File(path).exists();
    }

    public void save(Database database) {
        this.database = database;

    }

    public Database getDatabase() {
        return database;
    }
    
    
}