此文档用于描述部分关键接口。<br/>

# bin包 -- 前端与后台交互逻辑
## bin.UserManage -- 用户相关信息处理
* bin.User.registration(qmNumber, fullName, email)<br/>
将指定用户注册为一个新用户，注册成功返回true，注册失败（卡号或邮箱重复）返回false

# data包 -- 数据模型和I/O
## data.UserManage -- 单个用户信息
* data.User(qmNumber, fullName, email)<br/>
创建实例时设置用户的参数，默认others=false

## data.UserList -- 所有用户数据
* data.UserList()<br/>
创建实例时自动获得文件中的用户信息

* data.UserList.getUserList()<br/>
获得所有用户信息，返回为ArrayList<User>

* data.UserList.addUser(user)<br/>
向所有用户的信息中添加一个新用户

* data.UserList.setUserList(userList)<br/>
重新定义用户信息列表，慎用！！！

## data.Models -- 将数据写入/取出本地文件(被所有data.???List类继承)

* data.Models.save(data, location)<br/>
将数据保存至本地文件（指定位置），无返回值

* data.Models.read(location)<br/>
获得指定文件中的数据，返回为数据集合组成的数组

# views包 -- 视图和前端逻辑

## views.FormatCheck -- 格式检查
* views.FormatCheck.isID()
* views.FormatCheck.isName()
* views.FormatCheck.isAddress()
* views.FormatCheck.isCharacter()<br/>
以上方法均返回整数类型（1或0）


## views.InputPanelPrototype -- 标签+文本框
通过被JPanel继承，构建一个 包括标签和文本框的{输入panel}，只能在包内定义
```java
class InputPanel extends InputPanelPrototype {
    public InputPanel(String text) {
        super(text);
    }
}

//如果内部的文本框需要被全局访问，
//原型类提供绑定方法：
class SomeClass {
    SomeClass() {
        JTextField jTextField;
        JPanel inputPanel = new InputPanel("aaa");
        jTextfield = inputPanel.bindTextfield();
    }
}
```
