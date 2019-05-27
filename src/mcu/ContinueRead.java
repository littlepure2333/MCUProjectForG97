package mcu;
/**RxtxAPI 的核心是抽象的CommPort类(用于描述一个被底层系统支持的端口的抽象类，
 * 它包含一些高层的IO控制方法，
 * CommPort,这是用于描写叙述一个被底层系统支持的port的抽象类。
 * 它包括一些高层的IO控制方法，这些方法对于全部不同的通讯port来说是通用的。
 * 两个子类，SerialPort类和ParallePort类。
 * 其中，SerialPort类是用于串口通信的类,通过它，
 * 用户能够直接对串口进行读、写及设置工作。
 * ParallePort类是用于并行口通信的类。
 * CommPort类还提供了常规的通信模式和方法，
 * 例如:getInputStream( )方法和getOutputStream( )方法，专用于与端口上的设备进行通信。
 * CommPortIdentifier，这个类主要用于对串口进行管理和设置，是对串口进行訪问控制的核心类。
 * 主要包含下面方法
 *   确定是否有可用的通信port
 *   为IO操作打开通信port
 *   决定port的全部权
 *   处理port全部权的争用
 *   管理port全部权变化引发的事件（Event）
 */

/**使用javax.comm进行串口通信大概分为以下几个步骤：
 * 1、选择一个可利用串口如COM1，得到一个CommPortIdentifier类。
 * 2、设置初始化参数（波特率、数据位、停止位、校验位），
 * 利用CommPortIdentifier.open()方法得到一个SerialPort。
 * 3、利用SerialPort.getOutputStream得到串口输出流，向串口写入数据。
 * 4、利用SerialPort.addEventListener(SerialPortEventListener listener)为串口添加监听事件，
 * 当串口返回数据时，在SerialPortEventListener监听器的
 * public void serialEvent(SerialPortEvent arg0)方法中，
 * 通过SerialPort.getInputStream得到串口输入流来读取响应数据。
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import gnu.io.*;

public class ContinueRead extends Thread implements SerialPortEventListener { // SerialPortEventListener


    // 监听器,我的理解是独立开辟一个线程监听串口数据
    static CommPortIdentifier portId; // 串口通信管理类
    static Enumeration<?> portList; // 有效连接上的端口的枚举
    InputStream inputStream; // 从串口来的输入流
    static OutputStream outputStream;// 向串口输出的流
    static SerialPort serialPort; // 串口的引用
    // 堵塞队列用来存放读到的数据
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
    static String portnum;
    String portname;

    @Override
    /**
     * SerialPort EventListene 的方法,持续监听端口上是否有数据流
     */
    public void serialEvent(SerialPortEvent event) {//

        switch (event.getEventType()) {
            //对以下内容进行判断并操作
        /*
         BI -通讯中断
    　         　 CD -载波检测
    　   　       CTS -清除发送
               　　DATA_AVAILABLE -有数据到达
    　         　DSR -数据设备准备好
    　         　FE -帧错误
    　         　OE -溢位错误
    　　          OUTPUT_BUFFER_EMPTY -输出缓冲区已清空
    　           　PE -奇偶校验错
         RI -　振铃指示
        */
            //switch多个，if单个
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            // 当有可用数据时读取数据，串口有数据到达事件。也就是说当串口有数据到达时，你能够在serialEvent中接收并处理所收到的数据。

            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];
                try {
                    int numBytes = -1;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);

                        if (numBytes > 0) {
                            msgQueue.add( new String(readBuffer));
                            readBuffer = new byte[20];// 重新构造缓冲对象，否则有可能会影响接下来接收的数据
                        } else {
                            msgQueue.add("额------没有读到数据");
                        }
                    }
                } catch (IOException e) {
                }
                break;
        }
    }

    /**
     *
     * 通过程序打开COM4串口，设置监听器以及相关的参数
     *
     * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
     */
    public int startComPort() {
        // 通过串口通信管理类获得当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();
        Enumeration<CommPortIdentifier> em = CommPortIdentifier.getPortIdentifiers();
        while (em.hasMoreElements()) {
            portname = em.nextElement().getName();
            System.out.println(portname);
        }

        while (portList.hasMoreElements()) {

            // 获取相应串口对象
            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型：--->" + portId.getPortType());
            System.out.println("设备名称：---->" + portId.getName());
            // 判断端口类型是否为串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 判断如果COM4串口存在，就打开该串口
                if (portId.getName().equals(portname)) {
                    try {
                        // 打开串口名字为COM_4(名字任意),延迟为2毫秒
                        serialPort = (SerialPort) portId.open(portname, 2000);

                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置当前串口的输入输出流
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 给当前串口添加一个监听器
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置监听器生效，即：当有数据时通知
                    serialPort.notifyOnDataAvailable(true);

                    // 设置串口的一些读写参数
                    try {
                        // 比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }

                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("--------------任务处理线程运行了--------------");
            while (true) {
                // 如果堵塞队列中存在数据就将其输出
                if (msgQueue.size() > 0) {
                    System.out.println(msgQueue.take());
                    portnum = msgQueue.take();
                    //将一个字符串转化为数字,从端口传输过来的数为字符串
                    System.out.println(portnum);

                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static  String getPortnum() {
        // TODO 自动生成的方法存根
        return   portnum;
    }
    public static void main(String[] args) {
        System.out.println(portnum);


        ContinueRead cRead = new ContinueRead();
        int i = cRead.startComPort();
        if (i == 1) {
            // 启动线程来处理收到的数据
            cRead.start();
            try {
                String st = "A";
                System.out.println("发出字节数：" + st.getBytes("gbk"    ).length);
                outputStream.write(st.getBytes("gbk"), 0,
                        st.getBytes("gbk").length);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return;
        }


    }
}
