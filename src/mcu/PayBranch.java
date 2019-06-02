package mcu;

import bin.UserManage;

import static mcu.Program.instructions;

class PayBranch {

    PayBranch() {
        RxTx.send(instructions.get("lcdPaid"));
        waitForInput();
    }

    private void waitForInput() {
        while (true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                System.out.println("get an input [PAY]");
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                // 按确认-付款
                if (result == Communication.TAKE_OPTION)
                    UserManage.payTheFine();
                // 按退格-回到起点
                else {
                    System.out.println("not paid, back");
                    Program.resetFlag = true;
                    return;
                }
            }
        }
    }
}

