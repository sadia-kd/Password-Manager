package persistence;

import model.PasswordManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Followed the sample application given for P2 to model my code
// Represents a writer that writes JSON representation of PasswordManager to file
public class JsonWriter {
    private static final int TAB = 10;
    private PrintWriter writer;
    private String location;


    //EFFECTS: constructor which makes writer to write to location file
    public JsonWriter(String location) {
        this.location = location;
    }


    //MODIFIES: this
    //EFFECTS: opens writer, otherwise
    //         throws FileNotFoundException if location file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(location));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of password manager to file
    public void write(PasswordManager pm) {
        JSONObject jsonObject = pm.toJson();
        saveToFile(jsonObject.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

}
