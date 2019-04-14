package data;

import java.util.ArrayList;

public class ScooterList extends DataIO {
    public ArrayList<Scooter> scooterList;
    private static final String fileLocation = "./statics/scooters.xml";

    public ScooterList() {
        //noinspection unchecked
        this.scooterList = (ArrayList<Scooter>)read(fileLocation);
    }

    public ArrayList<Scooter> getList() {
        return scooterList;
    }

    public void addScooter(Scooter scooter) {
        this.scooterList.add(scooter);
        save(scooterList, fileLocation);
    }
}
