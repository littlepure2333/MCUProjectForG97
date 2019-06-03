package mcu;

import bin.AppState;
import bin.ScooterManage;

import static mcu.Program.instructions;

class ConfirmTakeReturnBranch {

    ConfirmTakeReturnBranch() {
        waitForInput();
    }

    private void waitForInput() {
        while (true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                System.out.println("get an input [6]");
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                // 按确认-确定要借
                if (result == Communication.TAKE_OPTION) {
                    //借车操作
                    if (Program.takeOrReturn == 1) {
                        System.out.println("confirm take");
                        //take
                        ScooterManage.takeScooter();
                        //update station data
                        RxTx.send(instructions.get("lcdTakeDone"));
                    }
                    //还车操作
                    else {
                        System.out.println("confirm ret");
                        //return
                        ScooterManage.returnScooter();
                        //update station data
                        RxTx.send(instructions.get("lcdReturnDone"));
                    }

                    //完成操作后先判断是否超时，再等待一段时间后回到开始
                    if (AppState.getCurrentUser().isNeedToPay().equals("true"))
                        RxTx.send(instructions.get("lcdUseExp"));
                    RxTx.wait(2000);
                    return;
                }

                // 按？/超时-回到开始
                else {
                    System.out.println("give up, back");
                    return;
                }
            }
        }
    }



}
