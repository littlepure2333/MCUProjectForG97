package mcu;

import bin.UserManage;

import static mcu.Program.instructions;

class LoginBranch {

    LoginBranch() {
        RxTx.send(instructions.get("lcdHello"));
        RxTx.send(instructions.get("keyBoardInit"));
        waitForInput();
    }


    private void waitForInput() {
        while(true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                RxTx.communication.setReceiveBuffIsChecked(true);
                // 9 digits
                if (RxTx.communication.receiveQmNumber() > 0) {
                    // if not exist
                    if(!UserManage.login(RxTx.communication.receiveQmNumber())) {
                        RxTx.send(instructions.get("lcdNotExist"));
                        RxTx.send(instructions.get("keyBoardInit"));
                        System.out.println("ID does not exist");
                    }
                    // success
                    else {
                        System.out.println("Login success");
                        return;
                    }
                }
                // invalid account
                else {
                    RxTx.send(instructions.get("lcdInvalid"));
                    RxTx.send(instructions.get("keyBoardInit"));
                    System.out.println("ID Incomplete");
                }
            }
        }
    }
}
