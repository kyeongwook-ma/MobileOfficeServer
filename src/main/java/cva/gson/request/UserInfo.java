package cva.gson.request;

import java.util.List;

/**
 * Created by ko on 2015-02-12.
 */
public class UserInfo {
	String id;
	String name;
	String location;
	String job;
	int start_time;
	int end_time;
	List<Preferece> prefereces;

	public UserInfo(String id, String name, String location, String job,
			int start_time, int end_time, List<Preferece> prefereces) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.job = job;
		this.start_time = start_time;
		this.end_time = end_time;
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

	public int getEnd_time() {
		return end_time;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
}
