package mcu;

import bin.UserManage;

import static mcu.Program.instructions;

class PayBranch {

    PayBranch() {
        RxTx.send(instructions.get("lcdPaid"));
        RxTx.send(instructions.get("keyBoardInit"));
        waitForInput();
    }

    private void waitForInput() {
        while (true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                // 1 - pay
                if (result == Communication.TAKE_OPTION) {
                    UserManage.payTheFine();
                    return;
                }
                // no or other - back to main
                else {
                    System.out.println("not paid, back");
                    Program.resetFlag = true;
                    return;
                }
            }
        }
    }
}

