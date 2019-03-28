package data;
import java.util.ArrayList;



public class UserList extends Models {
    public ArrayList<User> userList;
    private static final String fileLocation = "./statics/user.xml";

    @SuppressWarnings("unchecked")
	public UserList() {
        this.userList = (ArrayList<User>)read(fileLocation);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setList(ArrayList<User> userList) {
        this.userList = userList;
        save(userList, fileLocation);
    }

    public void add(User user) {
        this.userList.add(user);
        save(userList, fileLocation);
    }


}
