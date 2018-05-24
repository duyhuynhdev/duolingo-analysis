package jp.ac.jaist.Common.obj;

import com.google.gson.Gson;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:53 PM 7/3/17.
 */
public class DuolingoObject {
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
