package persistence;

import model.Account;
import model.PasswordManager;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonReader class
public class JsonReaderTest {

    @Test
    public void testJsonReaderNotExistentFile() {
        JsonReader jsonReader = new JsonReader("./data/notAvailable.txt");
        try {
            PasswordManager pm = jsonReader.read();
            fail("Exception Expected");
        } catch (IOException e) {
            // nothing needed here
        }
    }

   @Test
   public void testJsonReadOneAccountSaved() {
       Account a1 = new Account("IG", "sadia", "123");
       PasswordManager pm = new PasswordManager();
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



//    @Test
//    public void testJsonReaderEmptyPasswordManager() {
//        JsonReader jsonReader = new JsonReader("./data/EmptyPasswordManager.txt");
//        try {
//            PasswordManager pm = jsonReader.read();
//            assertEquals(0, pm.getCount());
//        } catch (IOException e) {
//            fail("No exception needed");
//        }
//    }
//


//    @Test
//    public void testJsonReaderGeneralPasswordManager() {
//        JsonReader jsonReader = new JsonReader("");
//    }
}
