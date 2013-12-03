package data;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Building implements Serializable {
	
	private static final long serialVersionUID = -9145615530465522920L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String key;
	
	@Persistent
	private String address;
	@Persistent
	private Long floors;
	
	@Persistent
    private List<Room> rooms;
	
	@Override
	public String toString() {
		String roomsString = "";
		if(rooms != null) {
			for(Room room : rooms) {
				roomsString += room.toString()+","; 
			}
		}
		
		return key + " " + address + " " + floors + " rooms: ["+ roomsString +"]";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getFloors() {
		return floors;
	}

	public void setFloors(Long floors) {
		this.floors = floors;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	
	
	
}
