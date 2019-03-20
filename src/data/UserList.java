package data;
import java.util.ArrayList;


@SuppressWarnings("WeakerAccess")
public class UserList implements java.io.Serializable{
    public ArrayList<User> list;

    public UserList() {
        this.list = Models.readUserData();
    }

    ArrayList<User> getList() {
        return list;
    }

    void setList(ArrayList<User> list) {
        this.list = list;
    }

    void addUser(User user) {
        this.list.add(user);
    }

    //void removeUser(UserControl user) {}


}
