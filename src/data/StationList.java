package data;

import java.util.ArrayList;

public class StationList extends DataIO {
	public ArrayList<Station> stationList;
	private static final String fileLocation = "./statics/stations.xml";
	
	public StationList() {
		//noinspection unchecked
		this.stationList = (ArrayList<Station>)read(fileLocation);
	}
	
	public ArrayList<Station> getList() {
		return this.stationList;
	}
	
	public void addStation(Station station) {
		this.stationList.add(station);
		save(stationList, fileLocation);
	}

	public void resetList(ArrayList<Station> stationList) {
		this.stationList = stationList;
		save(stationList, fileLocation);
	}

	/* once a change of one station is occurred, this method should be applied immediately */
	public void updateList() {
		save(stationList,fileLocation);
	}

}
