package mcu;

import bin.StationManage;
import bin.UserManage;
import data.AppData;

public class Program {
    public static void main(String[] args) {
        //Database initialization
        new AppData();
        //RxTx initialization
        RxTx.init("COM3");


        // 1.choose station
        StationManage.chooseStation("A");


        // 2.login
        byte[] idInit = new byte[2];
        idInit[0] = 0x12;
        idInit[1] = 0x7F;
        RxTx.send(idInit);
        System.out.println("1");

        while(true) {
            RxTx.wait(100);
            System.out.println("2");
            // 如果没检查过
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                System.out.println("3");
                // 当输入九位的时候
                if (RxTx.communication.receiveQmNumber() > 0) {
                    System.out.println("4");
                    RxTx.communication.setReceiveBuffIsChecked(true);
                    // 当账号不存在的时候
                    if(!UserManage.login(RxTx.communication.receiveQmNumber())) {
                        RxTx.wait(100);
                        RxTx.send(idInit);
                        System.out.println("ID does not exist");
                        continue;
                    }
                    // 登陆成功
                    else {
                        System.out.println("Login success");
                        break;
                    }
                }
                else {
                    RxTx.communication.setReceiveBuffIsChecked(true);
                    RxTx.wait(100);
                    RxTx.send(idInit);
                    System.out.println("ID Incomplete");
                }

            }
        }
        System.out.println("3");

        // 3.选借还车
        byte[] takeOrReturn = new byte[2];
        takeOrReturn[0] = 0x13;
        takeOrReturn[1] = 0x7F;
        RxTx.wait(1000);
        RxTx.send(idInit);
        System.out.println("4");

        while (true) {
            RxTx.wait(1000);
            System.out.println("5");
            // 如果没检查过
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                System.out.println("6");
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                System.out.println("7");
                // 如果是借车
                if (result == Communication.TAKE_OPTION) {
                    System.out.println("take");
                    break;
                }
                // 如果是借车
                else if (result == Communication.RETURN_OPTION) {
                    System.out.println("return");
                    break;
                }
                else {
                    System.out.println("else");
                    RxTx.send(takeOrReturn);
                }

            }

        }

        System.out.println("8");


        while(true);

    }
}
