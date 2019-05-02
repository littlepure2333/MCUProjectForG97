此文档用于描述部分关键接口。<br/>

# bin包 -- 前端与后台交互逻辑
## bin.UserManage -- 用户相关信息处理
* bin.UserManage.registration(qmNumber, fullName, email)<br/>
将指定用户注册为一个新用户，注册成功返回true，注册失败（卡号或邮箱重复）返回false
## bin.StationManage -- 站点相关信息处理
* bin.StationManage.chooseStation(int stationId)  
  选择当前的站点
* bin.StationManage.findStationById(int stationId)  
  根据站点id返回指定的站点数据
## bin.ScooterManage -- 单车相关信息处理
* bin.ScooterManage.takeScooter(int stationId, int scooterId, int qmNumber)  
  借车操作，车从station拿出来，放到user里
* bin.ScooterManage.returnScooter(int qmNumber, int scooterId, int stationId)  
  还车操作，车从user拿出来，放到station里

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

* data.UserList.resetList(userList)<br/>
重新定义用户信息列表，慎用！！！

## data.DataIO -- 将数据写入/取出本地文件(被所有data.???List类继承)

* data.DataIO.save(data, location)<br/>
将数据保存至本地文件（指定位置），无返回值

* data.DataIO.read(location)<br/>
获得指定文件中的数据，返回为数据集合组成的数组



