package persistence;

// Followed the sample application to model my code

import model.PasswordManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of PasswordManager to file
public class JsonWriter {
    private static final int TAB = 10;
    private PrintWriter printWriter;
    private String location;

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public JsonWriter(String location) {
        this.location = location;
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(location));
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public void write(PasswordManager pm) {
        JSONObject jsonObject = pm.toJson();
        saveToFile(jsonObject.toString(TAB));
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public void saveToFile(String json) {
        printWriter.print(json);
    }

    //REQUIRES
    //MODIFIES
    //EFFECTS
    public void close() {
        printWriter.close();
    }


}
