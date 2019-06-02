package mcu;

import bin.AppState;
import bin.StationManage;
import data.AppData;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Program {

    private static final String INPUT_STATION = "^([A-C]|[A-C]\n)$";
    private static Scanner scanner = new Scanner(System.in);
    static HashMap<String, byte[]> instructions = new HashMap<>();
    static int takeOrReturn; //1-take 0-return
    static boolean resetFlag = false;

    public static void main(String[] args) {
        //Database initialization
        new AppData();
        inputInstructions();
        //RxTx initialization
        RxTx.init("COM3");
        // 1.choose station
        while (true) {
            System.out.println("Choose a station: ");
            if (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (Pattern.matches(INPUT_STATION, str)) {
                    StationManage.chooseStation(str);
                    System.out.println(str);
                    break;
                }
            }
        }
        RxTx.send(instructions.get("lcdInit"));
        //noinspection InfiniteLoopStatement
        while (true) {
            frame();
        }
    }

    private static void frame() {
        // 2.login
        new LoginBranch();
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdHello"));return;}

        // 2b.付钱
        if (AppState.getCurrentUser().isNeedToPay().equals("true")) {
            /*TODO*/
            new PayBranch();
            if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdHello"));return;}
        }

        // 3.选借还车
        new TakeReturnBranch();
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdHello"));return;}

        // 4.给mcu发送当前的站点信息
        RxTx.communication.sendStationSlots();
        System.out.println("send msg to mcu");

        // 5.从左到右找到一个车,设定闪灯槽位并发送给mcu
        // 如果没有车/满车，给出错误提示并重开
        findScooter();
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdHello"));return;}

        // 6.confirm take/return
        new ConfirmTakeReturnBranch();
    }

    private static void findScooter() {
        //take
        if (takeOrReturn == 1) {
            for (int site = 0; site <= 7; site++) {
                if (AppState.getCurrentStation().getSlot()[site] != null) {
                    StationManage.chooseFlashSlot(site);
                    RxTx.communication.sendStationFlashSlot(site);
                    RxTx.send(instructions.get("lcdReadyToTake"));
                    System.out.println("ready to take");
                    return;
                }
            }
            //empty
            RxTx.send(instructions.get("lcdEmpty"));
            System.out.println("empty");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resetFlag = true;
        }
        //return
        else {
            for (int site = 0; site <= 7; site++) {
                if (AppState.getCurrentStation().getSlot()[site] == null) {
                    StationManage.chooseFlashSlot(site);
                    RxTx.communication.sendStationFlashSlot(site);
                    RxTx.send(instructions.get("lcdReadyToReturn"));
                    System.out.println("ready to return");
                    return;
                }
            }
            //full
            RxTx.send(instructions.get("lcdFull"));
            System.out.println("full");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resetFlag = true;
        }

    }

    private static void inputInstructions() {
        instructions.put("keyBoardInit",    new byte[]{0x12, 0x7F});
        instructions.put("lcdInit",         new byte[]{0x15, 0x7F});
        instructions.put("lcdHello",        new byte[]{0x16, 0x7F});
        instructions.put("lcdChooseAct",    new byte[]{0x17, 0x7F});
        instructions.put("lcdNotExist",     new byte[]{0x18, 0x7F});
        instructions.put("lcdInvalid",      new byte[]{0x19, 0x7F});
        instructions.put("lcdReadyToTake",  new byte[]{0x20, 0x7F});
        instructions.put("lcdEmpty",        new byte[]{0x21, 0x7F});
        instructions.put("lcdTakeDone",     new byte[]{0x22, 0x7F});
        instructions.put("lcdReadyToReturn",new byte[]{0x23, 0x7F});
        instructions.put("lcdFull",         new byte[]{0x24, 0x7F});
        instructions.put("lcdReturnDone",   new byte[]{0x25, 0x7F});
        instructions.put("lcdUseExp",       new byte[]{0x26, 0x7F});
        instructions.put("lcdPaid",         new byte[]{0x27, 0x7F});
    }
}
