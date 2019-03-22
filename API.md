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

* data.UserList.getList()<br/>
获得所有用户信息

* data.UserList.addUser(user)<br/>
向所有用户的信息中添加一个新用户

## data.Models -- 将数据写入/取出本地文件(仅允许内部访问)

* data.Models.saveUserData(allUser)<br/>
将用户数据保存至本地文件，无返回值

* data.Models.readUserData()<br/>
获得文件中的用户数据，返回用户数据的数组