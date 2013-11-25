package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Room implements Serializable{
	
	private static final long serialVersionUID = 6950517797162955042L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private Integer number;
	
	@Persistent
	private String function;
	
	@Persistent
    private ArrayList<Activity> activities;
	
	@Override
	public String toString() {
		String activitiesString = "";
		if(activities != null && activities.size() > 0) {
			for(Activity a : activities) {
				activitiesString += a.toString()+",";
			}
			
		}
		return "num: " + number + ", func: "+ function + ", activities: [" + activitiesString+"]";
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}
	
	

}
