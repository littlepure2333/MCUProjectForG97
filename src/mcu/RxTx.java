package mcu;
import java.io.*;
import java.util.*;
import gnu.io.*;

/**
 * Based on SimpleWrite.java provided by Java Sun
 * Updated by Matthew Tang @QMUL 7 May 2017.
 * You need to have RXTX installed with Java
 * Link 1: http://rxtx.qbang.org/wiki/index.php/Main_Page
 * Link 2: http://fizzed.com/oss/rxtx-for-java
 */
public class RxTx {
    static CommPortIdentifier portId;
    static CommPort com;
    static SerialPort ser;
    static OutputStream comOut;
    static InputStream comIn;

    static final byte LED_SEND_INIT = 0x10;
    static final byte LED_TAKE_FLASH = 0x11;
    static final byte LED_RET_FLASH = 0x28;
    public static final byte KEY_RECEIVE_ID = 0x30;
    public static final byte KEY_RECEIVE_YES = 0x31;
    public static final byte KEY_RECEIVE_NO = 0x32;
    public static final byte KEY_RECEIVE_TAKE = 0x33;
    public static final byte KEY_RECEIVE_RETURN = 0x34;
    public static final byte DATA_END = 0x7F;

    public static Communication communication = new Communication();

    /**
     * Initial the port
     * @param COMPort the port
     * @return success or not
     */
    public static boolean init(String COMPort) {
        try {
            // TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier(COMPort);

            com = portId.open("MCS51COM", 2000);
            ser = (SerialPort)com;
            // Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            // Add serial listener to the port,
            // all receive information save in Communication.RECEIVE_BUFF
            RxTx.setListener(serialPortEvent -> {
                if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    byte[] data = RxTx.receive();
                    communication.addReceiveBuff(data);
//                    communication.setReceiveBuffIsChecked(false);
                    System.out.println("Receive data length: " + data.length);
                    System.out.println("Receive data content: ");
                    printData(data);
                }
            });
            return true;
        } catch (Exception e){
            e.printStackTrace(System.out);
            return false;
        }
    }

    private static void printData(byte[] data) {
        for (byte datum : data) {
            System.out.println(datum);
        }
    }

    /**
     * Wait for a while if 8051 needs time
     * @param millisecond wait time (millisecond)
     * @return success or not
     */
    static boolean wait(int millisecond) {
        try {
            Thread.sleep(millisecond);
            return true;
        } catch (InterruptedException e){
            return false;
        }
    }

    /**
     * Send data through the port
     * @param data the data
     * @return success or not
     */
    static void send(byte[] data) {
        wait(500);
        for(Byte each: data) {
            wait(200);
            try {
                comOut = ser.getOutputStream();
                comOut.write(each);
                comOut.close();
                System.out.println("send: " + each);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Receive the data
     * @return the data
     */
    public static byte[] receive() {
        byte[] data = null;
        try {
            comIn = ser.getInputStream();
            int buffLen = comIn.available(); // get data length
            while (buffLen != 0) {
                data = new byte[buffLen]; // initialize byte array
                comIn.read(data);
                buffLen = comIn.available(); // update data length
            }
            comIn.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return data;
    }

    /**
     * Close the port
     */
    public static void close() {
        ser.close();
    }

    /**
     * Set a listener to the port
     * @param listener
     */
    public static void setListener(SerialPortEventListener listener) {
        try {
            ser.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        ser.notifyOnDataAvailable(true); // port listen data
        //ser.notifyOnBreakInterrupt(true);// port listen interrupt
    }

    /**
     * Get available port names of the system
     * @return port names
     */
    public static List<String> getSystemPort(){
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }
        System.out.println("System available port list："+systemPorts);
        return systemPorts;
    }

    public static int getUnsignedByte (int data){      //将data字节型数据转换为0~255 (0xFF 即BYTE)。
        return data&0x0FF;
    }


}