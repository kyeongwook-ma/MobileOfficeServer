package cva.gson.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ko on 2015-02-12.
 */
public class UserInfo {
    String id;
    String name;
    String location;
    String job;
    List<Preferece> prefereces;
    public UserInfo(String id, String name, String location, String job, List<Preferece> prefereces)
    {
        this.id = id;
        this.name =name;
        this.location = location;
        this.job = job;
        this.prefereces = prefereces;
    }

    public String getName() {
        return name;
    }

    public List<Preferece> getPrefereces() {
        return prefereces;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getLocation() {
        return location;
    }

}
