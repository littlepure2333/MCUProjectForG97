package data;
import java.util.ArrayList;


@SuppressWarnings("WeakerAccess")
public class UserList extends DataIO{
    public ArrayList<User> userList;
    private static final String fileLocation = "./statics/user.xml";

    public UserList() {
        //noinspection unchecked
        this.userList = (ArrayList<User>)read(fileLocation);
    }

    public ArrayList<User> getList() {
        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
        save(userList, fileLocation);
    }


    public void resetList(ArrayList<User> userList) {
        this.userList = userList;
        save(userList, fileLocation);
    }


}
