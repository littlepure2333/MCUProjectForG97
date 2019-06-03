package mcu;

import bin.AppState;
import bin.ScooterManage;
import bin.TransactionManage;

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
                // pressed - confirmed
                if (result == Communication.TAKE_OPTION) {
                    // take
                    if (Program.takeOrReturn == 1) {
                        System.out.println("confirm take");
                        // take a scooter
                        ScooterManage.takeScooter();
                        //update station data
                        RxTx.send(instructions.get("lcdTakeDone"));
                    }
                    // return
                    else {
                        System.out.println("confirm ret");
                        // return a scooter
                        ScooterManage.returnScooter();
                        TransactionManage.checkIfExpired();
                        // update station data
                        RxTx.send(instructions.get("lcdReturnDone"));
                        RxTx.wait(500);
                    }

                    // judge if expired, then return to login
                    if (AppState.getCurrentUser().isNeedToPay().equals("true"))
                        RxTx.send(instructions.get("lcdUseExp"));
                    RxTx.wait(2000);
                    return;
                }

                // expired-back to login
                else {
                    System.out.println("give up, back");
                    return;
                }
            }
        }
    }



}
