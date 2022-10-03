package src.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import src.info.SendTAG;
import src.info.ServerInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonHandler {
    public SendTAG Read(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, SendTAG.class);
    }

    public Database ReadFromResource(File file) throws IOException {
        Gson gson = new Gson();

        Charset cs = StandardCharsets.UTF_8;
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, cs);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while((line = br.readLine()) != null){
            br.close();
            return gson.fromJson(line, Database.class);
        }
        br.close();

        return null;


    }

    public void SaveDatabase(Database database) {
        Gson gson = new Gson();

        if(!fileExists(String.valueOf(ServerInfo.databaseFile))) {
            try{
                FileWriter writer = new FileWriter(ServerInfo.databaseFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            FileWriter writer = new FileWriter(ServerInfo.databaseFile);
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
