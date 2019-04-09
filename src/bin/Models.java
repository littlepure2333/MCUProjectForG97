package bin;

import data.UserList;

public class Models {
    private UserList userList;
    public Models() {
    userList = new UserList();
    }

    public UserList getUserList() {
        return userList;
    }
}
