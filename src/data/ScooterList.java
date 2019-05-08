package data;

import java.util.ArrayList;

public class ScooterList extends DataIO {
    public ArrayList<Scooter> scooterList;
    private static final String FILE_LOCATION = "./statics/scooters.xml";

    public ScooterList() {
        //noinspection unchecked
        this.scooterList = (ArrayList<Scooter>)read(FILE_LOCATION);
    }

    public ArrayList<Scooter> getList() {
        return this.scooterList;
    }

    public void addScooter(Scooter scooter) {
        this.scooterList.add(scooter);
        save(scooterList, FILE_LOCATION);
    }

    public void resetList(ArrayList<Scooter> scooterList) {
        this.scooterList = scooterList;
        save(scooterList, FILE_LOCATION);
    }

    /* once a change of one scooter is occurred, this method should be applied immediately */
    public void updateList() {
        save(scooterList, FILE_LOCATION);
    }

}
