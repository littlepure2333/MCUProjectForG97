package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Vector;

/**
 * Entity Class
 * 程序开始时初始化，读取文件中的数据
 * 类中所有的list和更新文件的方法updateData()供所有的manage类访问
 */
public class AppData {
    /*
    缓冲区
     */
    protected static Vector<User> users;
    protected static Vector<Station> stations;
    protected static Vector<Scooter> scooters;
    protected static Vector<Transaction> transactions;

    /*
    文件地址
     */
    private static final String USER_DIR = "./statics/users.xml";
    private static final String STATION_DIR = "./statics/stations.xml";
    private static final String SCOOTER_DIR = "./statics/scooters.xml";
    private static final String TRANSACTION_DIR = "./statics/transactions.xml";

    /**
     * 初始化读取数据
     */
    @SuppressWarnings("unchecked")
    public AppData() {
        users = (Vector<User>) read(USER_DIR);
        scooters = (Vector<Scooter>) read(SCOOTER_DIR);
        stations = (Vector<Station>) read(STATION_DIR);
        transactions = (Vector<Transaction>) read(TRANSACTION_DIR);
    }

    /**
     * 将所有程序中的数据更新到文件中
     * 如果在关键操作后没有使用该方法，下次启动程序就会缺失数据
     */
    protected static void updateData() {
        save(users, USER_DIR);
        save(stations, STATION_DIR);
        save(scooters, SCOOTER_DIR);
        save(transactions, TRANSACTION_DIR);
    }


    /**
     * 将指定数据集合保存在指定文件中，保证静态文件和程序数据的同步
     *
     * @param data     将要存入文件的数据
     * @param location 文件地址
     * @param <E>      指定数据集合
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
     * 从指定文件地址中提取数据并转换成数组
     *
     * @param location 文件地址
     * @return 从文件读取出的数组
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
