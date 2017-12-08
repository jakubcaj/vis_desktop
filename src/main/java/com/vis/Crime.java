package com.vis;

import org.json.JSONObject;

public class Crime {

    private JSONObject crime;

    public Crime(JSONObject jsonObject) {
        this.crime = jsonObject;
    }

    @Override
    public String toString() {
        return crime.getString("shortDescription");
    }

    public JSONObject getCrime() {
        return crime;
    }

    public void setCrime(JSONObject crime) {
        this.crime = crime;
    }
}
