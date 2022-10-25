package persistence;

import model.Account;
import model.PasswordManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonReader and JsonWriter classes
public class JsonReaderWriterTest {
    private PasswordManager pm;
    private Account a1;
    private Account a2;
    private Account a3;


    @BeforeEach
    public void setUp() {
        this.pm = new PasswordManager();
        this.a1 = new Account("IG", "sadia", "123");
        this.a2 = new Account("SC", "skd", "345");
        this.a3 = new Account("ssc", "sadia123", "password");
    }


    @Test
    public void testJsonReaderNotExistentFile() {
        JsonReader jsonReader = new JsonReader("./data/notAvailable.json");
        try {
            PasswordManager passwordManager = jsonReader.read();
            fail("Exception Expected");
        } catch (IOException e) {
            // nothing needed here: will pass
        }
    }


    @Test
    public void testJsonWriterInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriter.open();
            fail("Exception was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    public void testJsonWriterReaderEmptyPasswordManager() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/testReaderEmptyPasswordManager.json");
            jsonWriter.open();
            jsonWriter.write(pm);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            fail("Not able to read from file");
        }

        PasswordManager passwordManager = null;
        try {
            JsonReader jsonReader = new JsonReader("./data/testReaderEmptyPasswordManager.json");
            passwordManager = jsonReader.read();
        } catch (IOException e) {
            fail("Exception not thrown!");
        }
        assertEquals(pm.getCount(), passwordManager.getCount());
    }


   @Test
   public void testJsonReadOneAccountSaved() {
       pm.addAccount(a1);
       JsonWriter jsonWriter = new JsonWriter("./data/testReaderOneAccount.json");

       try {
           jsonWriter.open();
       } catch (FileNotFoundException e) {
           fail("");
       }
       jsonWriter.write(pm);
       jsonWriter.close();

       JsonReader jsonReader = new JsonReader("./data/testReaderOneAccount.json");
       PasswordManager passwordManager = null;
       try {
           passwordManager = jsonReader.read();

       } catch (IOException e) {
           fail("Unexpected exception");
       }

       Account account = passwordManager.getAccount(0);
       assertEquals(a1.getApplicationName() , account.getApplicationName());
       assertEquals(a1.getUsername(), account.getUsername());
       assertEquals(a1.getPassword(), account.getPassword());
   }


    @Test
    public void testGeneralReaderWriter() {
        pm.addAccount(a1);
        pm.addAccount(a2);
        pm.addAccount(a3);

        try {
            JsonWriter jsonWriter = new JsonWriter("./data/testGeneralReaderWriter.json");
            jsonWriter.open();
            jsonWriter.write(pm);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            fail("Exception not thrown");
        }
        PasswordManager passwordManager = null;
        try {
            JsonReader jsonReader = new JsonReader("./data/testGeneralReaderWriter.json");
            passwordManager = jsonReader.read();

        } catch (IOException e) {
            fail("Unexpected exception");
        }
        assertEquals(pm.getCount(), passwordManager.getCount());
        Account account = passwordManager.getAccount(1);
        assertEquals(a2.getApplicationName() , account.getApplicationName());
        assertEquals(a2.getUsername(), account.getUsername());
        assertEquals(a2.getPassword(), account.getPassword());
    }

}
