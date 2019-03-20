package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Models {

    public static void saveUserData(ArrayList<User> saveList) {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("../data/user.xml"));
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(saveList);
            encoder.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> readUserData() {
        try {
            XMLDecoder d=new XMLDecoder(new BufferedInputStream(new FileInputStream("../data/user.xml")));
            @SuppressWarnings("unchecked")
            ArrayList<User> p = (ArrayList<User>) d.readObject();
            d.close();
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
