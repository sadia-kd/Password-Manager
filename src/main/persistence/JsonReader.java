package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Account;
import org.json.*;

import model.PasswordManager;

// Followed the sample application given for P2 to model my code
// Represents a reader that reads Password Manager from JSON data stored in file
public class JsonReader {
    private String source;


    //EFFECTS: constructor which makes reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    //EFFECTS: reads password manager from file and returns it;
    //         throws IOException if an error occurs reading data from file
    public PasswordManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePasswordManager(jsonObject);
    }


    //EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }


    //EFFECTS: parses password manager from JSON object and returns it
    public PasswordManager parsePasswordManager(JSONObject jsonObject) {
        PasswordManager pm = new PasswordManager();
        addAccounts(pm, jsonObject);
        return pm;
    }


    // MODIFIES: pm
    // EFFECTS: parses accounts from JSON object and adds them to password manager
    private void addAccounts(PasswordManager pm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(pm, nextAccount);
        }
    }


    //MODIFIES: pm
    //EFFECTS: parses Account from JSON object and adds it to password manager
    private void addAccount(PasswordManager pm, JSONObject jsonObject) {
        String application = jsonObject.getString("application");
        String user = jsonObject.getString("user");
        String pass = jsonObject.getString("pass");
        Account account = new Account(application, user, pass);
        pm.addAccount(account);
    }

}
