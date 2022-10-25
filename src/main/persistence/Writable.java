package persistence;

import org.json.JSONObject;

// Followed the sample application given for P2 to model my code
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();

}

