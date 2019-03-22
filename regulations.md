## 本项目代码规范
1> 类和成员的命名方法均采用驼峰命名法（camelCase） <br/>

2> 所有数据类型的集合（如：所有用户信息，所有的dock station信息）统一使用ArrayList<>格式实现 <br/>

3> 关于大括号的格式（如下所示） <br/>
```java
class ClassName {
    private void someMethod(String argumentName) {        
        /*
        for(int i=1;i>0;i++) {
            //do something
        }
        */
    }
}
```
4> 每个方法的代码长度（不算括号）尽量不超过30行，分支数量（if-else/switch）尽量不超过4个。如果超过，尝试提取构造新的方法，或者将方法改造为对象。 <br/>

5> 除了在循环内用于计数的变量，所有成员的命名必须具有语义（由单词组成） <br/>

6> 设计新方法时应该考虑到该方法被调用的范围，不会被外界（其他package）访问的方法不要使用public修饰符。<br/>