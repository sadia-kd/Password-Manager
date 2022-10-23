package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Account;
import org.json.*;

// Followed the sample application to model my code

import model.PasswordManager;

// Represents a reader that reads Password Manager from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructor which makes reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public PasswordManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePasswordManager(jsonObject);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }


    //REQUIRES
    //MODIFIES
    //EFFECTS: parses password manager from JSON object and returns it
    public PasswordManager parsePasswordManager(JSONObject jsonObject) {
        PasswordManager pm = new PasswordManager();
        addAccounts(pm, jsonObject);
        return pm;

    }


    // MODIFIES: pm
    // EFFECTS: parses accounts from JSON object and adds them to passwordmanager
    private void addAccounts(PasswordManager pm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addAccount(pm, nextThingy);
        }
    }


    //REQUIRES
    //MODIFIES: pm
    //EFFECTS: parses Account from JSON object and adds it to passwordmanager
    private void addAccount(PasswordManager pm, JSONObject jsonObject) {
        String application = jsonObject.getString("application");
        String user = jsonObject.getString("user");
        String pass = jsonObject.getString("pass");
        Account account = new Account(application, user, pass);
        pm.addAccount(account);
    }

}
