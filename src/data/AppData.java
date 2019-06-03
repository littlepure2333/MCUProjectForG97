package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Vector;

/**
 * Entity class used to store all data.
 * Initialize when the program start, read data from local files.
 * All lists and methods in this class is provided to all manage classes
 */
public class AppData {
    /**
     * all user data in program
     */
    protected static Vector<User> users;
    /**
     * all station data in program
     */
    protected static Vector<Station> stations;
    /**
     * all scooter data in program
     */
    protected static Vector<Scooter> scooters;
    /**
     * all transaction data in program
     */
    protected static Vector<Transaction> transactions;

    private static final String USER_DIR = "./statics/users.xml";
    private static final String STATION_DIR = "./statics/stations.xml";
    private static final String SCOOTER_DIR = "./statics/scooters.xml";
    private static final String TRANSACTION_DIR = "./statics/transactions.xml";

    /**
     * Initialize to read data from local files
     */
    public AppData() {
        users = (Vector<User>) read(USER_DIR);
        scooters = (Vector<Scooter>) read(SCOOTER_DIR);
        stations = (Vector<Station>) read(STATION_DIR);
        transactions = (Vector<Transaction>) read(TRANSACTION_DIR);
    }

    /**
     * Update all data to local files.
     * Must be called after changing something, unless data will not be saved in local files
     */
    protected static void updateData() {
        save(users, USER_DIR);
        save(stations, STATION_DIR);
        save(scooters, SCOOTER_DIR);
        save(transactions, TRANSACTION_DIR);
    }


    /**
     * Save the specified data to the specified file.
     * To ensure the data in static files is as same as the running program data
     *
     * @param data     the data want to save
     * @param location the file address
     * @param <E>      data set type
     */
    private static <E extends Vector> void save(E data, String location) {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(location));
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(data);
            encoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read data from specified file
     *
     * @param location the file address
     * @return the data set read from local file
     */
    private Vector<?> read(String location) {
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(location)));
            Vector<?> p = (Vector<?>) d.readObject();
            d.close();
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Vector<>();
    }
}
