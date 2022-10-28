package persistence;

import org.json.JSONObject;

// Followed the sample application given for P2 to model my code
// Source https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();

}

