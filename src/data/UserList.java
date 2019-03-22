package data;
import java.util.ArrayList;


@SuppressWarnings("WeakerAccess")
public class UserList implements java.io.Serializable{
    public ArrayList<User> list;

    public UserList() {
        this.list = Models.readUserData();
    }

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
        Models.saveUserData(list);
    }

    public void addUser(User user) {
        this.list.add(user);
    }

    //void removeUser(UserControl user) {}


}
