package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

class Models {

    static void saveUserData(ArrayList<User> saveList) {
        String location = "./statics/user.xml";
        save(saveList, location);
    }

    static ArrayList<User> readUserData() {
        String location = "./statics/user.xml";
        //noinspection unchecked
        return (ArrayList<User>) read(location);
    }

    private static < E > void save(E data, String location) {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(location));
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(data);
            encoder.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<?> read(String location) {
        try {
            XMLDecoder d=new XMLDecoder(new BufferedInputStream(new FileInputStream(location)));
            ArrayList<?> p = (ArrayList<?>) d.readObject();
            d.close();
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
