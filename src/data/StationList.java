package data;

import java.util.ArrayList;

public class StationList extends DataIO {
	public ArrayList<Station> stationList;
	private static final String FILE_LOCATION = "./statics/stations.xml";
	
	public StationList() {
		//noinspection unchecked
		this.stationList = (ArrayList<Station>)read(FILE_LOCATION);
	}
	
	public ArrayList<Station> getList() {
		return this.stationList;
	}
	
	public void addStation(Station station) {
		this.stationList.add(station);
		save(stationList, FILE_LOCATION);
	}

	public void resetList(ArrayList<Station> stationList) {
		this.stationList = stationList;
		save(stationList, FILE_LOCATION);
	}

	/* once a change of one station is occurred, this method should be applied immediately */
	public void updateList() {
		save(stationList, FILE_LOCATION);
	}

}
