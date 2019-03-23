package data;
import java.util.ArrayList;


@SuppressWarnings("WeakerAccess")
public class UserList implements java.io.Serializable{
    public ArrayList<User> userList;

    public UserList() {
        this.userList = Models.readUserData();
    }

    public ArrayList<User> getList() {
        return userList;
    }

    public void setList(ArrayList<User> userList) {
        this.userList = userList;
        Models.saveUserData(userList);
    }

    public void addUser(User user) {
        this.userList.add(user);
        Models.saveUserData(userList);
    }

    //void removeUser(UserControl user) {}


}
