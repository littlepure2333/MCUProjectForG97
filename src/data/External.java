package data;

import java.util.ArrayList;

public class External {
	public ArrayList<Station> stations;
	public String fileLocation;
	
	public External(String fileLocation) {
		stations = new ArrayList<Station>();
		this.fileLocation = fileLocation;
	}
	
	public ArrayList<Station> getAllStations() {
		return this.stations;
	}
	
	public void addNewStation(int stationId) {
		Station newStation = new Station(stationId);
		this.stations.add(newStation);
		
	}

}
