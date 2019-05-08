package data;

import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public class UserList extends DataIO{
    public ArrayList<User> userList;
    private static final String FILE_LOCATION = "./statics/user.xml";

    public UserList() {
        //noinspection unchecked
        this.userList = (ArrayList<User>)read(FILE_LOCATION);
    }

    public ArrayList<User> getList() {
        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
        save(userList, FILE_LOCATION);
    }

    public void resetList(ArrayList<User> userList) {
        this.userList = userList;
        save(userList, FILE_LOCATION);
    }

    /* once a change of one user is occurred, this method should be applied immediately */
    public void updateList() {
        save(userList, FILE_LOCATION);
    }

}
