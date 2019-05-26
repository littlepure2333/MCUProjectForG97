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
public class SimpleCommRxTx {
    static CommPortIdentifier portId;
    static CommPort com;
    static SerialPort ser;

    public static void main(String[] args) {
        try {
			// TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier("COM3");

            com = portId.open("MCS51COM", 2000);
            ser = (SerialPort)com;
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }

		/*
		// Wait for 1 second if 8051 needs time to initialise
        try { 
            Thread.sleep(1000);
        } catch (InterruptedException e){}
		*/

        try {
			// Test TX: send out chars 'D', 'O', 'G', 'S'
            OutputStream comOut = ser.getOutputStream();
            comOut.write('D');
            comOut.write('O');
            comOut.write('G');
            comOut.write('S');

			// Test RX: display first 4 chars received
            InputStream comIn = ser.getInputStream();
            for (int i = 0; i < 4; i++){
                while (comIn.available() == 0);
                char c = (char)comIn.read();
                System.out.println(c);
            }

			// close the streams
            comIn.close();
            comOut.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
		// close the port
        ser.close(); 
    }
}