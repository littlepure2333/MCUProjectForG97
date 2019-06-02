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
                if (Pattern.matches(INPUT_STATION, str)){
                    StationManage.chooseStation(str);
                    System.out.println(str);
                    break;
                }
            }
        }
        RxTx.send(instructions.get("lcdInit"));
        // 2.login
        new LoginBranch();
        // 3.选借还车
        new TakeReturnBranch();
        RxTx.communication.sendStationSlots();
        //从左到右找到一个车
        for (int site = 0; site <= 7; site++) {
            if (AppState.getCurrentStation().getSlot()[site] != null) {
                StationManage.chooseFlashSlot(site);
                RxTx.communication.sendStationFlashSlot(site);
                break;
            }
        }
        // 4.confirm take/return
        new ConfirmTakeReturnBranch();
    }

    private static void inputInstructions() {
        instructions.put("keyBoardInit", new byte[]{0x12, 0x7F});
        instructions.put("lcdInit", new byte[]{0x15, 0x7F});
        instructions.put("lcdHello", new byte[]{0x16, 0x7F});
        instructions.put("lcdChooseAct", new byte[]{0x17, 0x7F});
        instructions.put("lcdNotExist", new byte[]{0x18, 0x7F});
        instructions.put("lcdInvalid", new byte[]{0x19, 0x7F});
    }
}
