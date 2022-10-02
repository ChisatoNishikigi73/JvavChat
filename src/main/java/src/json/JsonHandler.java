package src.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import src.info.SendTAG;
import src.info.ServerInfo;

import java.io.*;

public class JsonHandler {
    public SendTAG Read(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, SendTAG.class);
    }

    public Database ReadFromResource(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file));

        Database database = gson.fromJson(reader, Database.class);
        return database;
    }

    public void SaveDatabase(Database database) {
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(ServerInfo.databaseFile)) {
            String jsonObject = gson.toJson(database);
            writer.write(jsonObject);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean fileExists (String path){
        return new File(path).exists();
    }
}
