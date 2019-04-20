# QM Scooter System v1.1
## general
* 程序的入口修改为Entrance
* 所有gui类的main方法已经移除，如果需要测试单独界面，使用views.TestPanel。（因为技术问题暂时不能使用JUnit进行测试）
## bin
* 新增State用于保存程序中当前的用户和站点。
## data
* 原Models更名为DataIO，被所有的数据集合类所继承，实现数据的读取和写入。
## view
* 为了适配界面切换的功能，所有原先的JFrame被替换为JPanel，JPanel内的布局和原先的JFrame保持一致。
* 只保留一个JFrame用于显示窗体，采用BorderLayout
* 界面切换：每次进入新的界面时会替换掉窗体内的JPanel，但除主页外，界面左上角的返回按钮不会改变。
* 所有的界面都需要在views.Windows进行实例化，需要按照节点层级从下往上排列。[（见图）](media/GUIHierarchy.jpg)
* 每个界面需要定义它们的下一状态（通向哪些界面），并且需要在构造器中传入状态（对应的JPanel），状态可以作为传递给GotoButton的参数。
* GotoButton用于切换界面，接受按钮文本和目标界面，如果需要修改点击事件（判断切换页面的条件，或修改全局状态），可以继承并重写GotoButton的ActionListener。