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
        // 2. login
        new LoginBranch();
        RxTx.wait(500);

        // 2b. pay the payment
        if (AppState.getCurrentUser().isNeedToPay().equals("true")) {
            new PayBranch();
            if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdInit"));return;}
        }
        RxTx.wait(500);


        // 3. take/return
        new TakeReturnBranch();
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdInit"));return;}
        RxTx.wait(500);

        /*
        4 .display take/return message
        if user cannot take/return, a reply will be received, then return to the main.
         */
        if (takeOrReturn == 1) {
//            if (AppState.getCurrentUser().getScooter() != null) {
//                RxTx.send(instructions.get("lcdHave"));
//                RxTx.wait(1500);
//                resetFlag = true;
//            }
//            else
                RxTx.send(instructions.get("lcdReadyToTake"));
        }
        else {
//            if (AppState.getCurrentUser().getScooter() == null) {
//                RxTx.send(instructions.get("lcdNotHave"));
//                RxTx.wait(1500);
//                resetFlag = true;
//            }
//            else
                RxTx.send(instructions.get("lcdReadyToReturn"));
        }
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdInit"));return;}

        // 5. send current station information to mcu
        RxTx.communication.sendStationSlots();
        System.out.println("send msg to mcu");
        RxTx.wait(500);

        /*
        6. find a scooter/slot
        if empty/full remind and go to login
         */
        findScooter();
        if (resetFlag) {resetFlag = false;RxTx.send(instructions.get("lcdInit"));return;}

        // 7.confirm take/return
        new ConfirmTakeReturnBranch();
    }

    private static void findScooter() {
        //take
        if (takeOrReturn == 1) {
            for (int site = 0; site <= 7; site++) {
                if (AppState.getCurrentStation().getSlot()[site] != null) {
                    StationManage.chooseFlashSlot(site);
                    RxTx.communication.sendTakeFlashSlot(site);
                    System.out.println("ready to take");
                    return;
                }
            }
            //empty
            RxTx.send(instructions.get("lcdEmpty"));
            System.out.println("empty");
            RxTx.wait(1500);
            resetFlag = true;
        }
        //return
        else {
            for (int site = 0; site <= 7; site++) {
                if (AppState.getCurrentStation().getSlot()[site] == null) {
                    StationManage.chooseFlashSlot(site);
                    RxTx.communication.sendReturnFlashSlot(site);
                    System.out.println("ready to return");
                    return;
                }
            }
            //full
            RxTx.send(instructions.get("lcdFull"));
            System.out.println("full");
            RxTx.wait(1500);
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
        instructions.put("lcdHave",         new byte[]{0x28, 0x7F});
        instructions.put("lcdNotHave",      new byte[]{0x29, 0x7F});
    }
}
