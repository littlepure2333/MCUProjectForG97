package mcu;

import bin.UserManage;

import static mcu.Program.instructions;

class LoginBranch {
    private byte[] keyPadInit = instructions.get("keyBoardInit");

    LoginBranch() {
        RxTx.wait(1000);
        RxTx.send(instructions.get("lcdHello"));
        RxTx.wait(500);
        RxTx.send(keyPadInit);
        waitForInput();
    }

    private void waitForInput() {
        while(true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveQmNumber();
                // 当输入九位的时候
                if (result > 0) {
                    // 当账号不存在的时候
                    if(!UserManage.login(result)) {
                        RxTx.send(instructions.get("lcdNotExist"));
                        RxTx.wait(500);
                        RxTx.send(keyPadInit);
                        System.out.println("ID does not exist");
                    }
                    // 登陆成功
                    else {
                        System.out.println("Login success");
                        return;
                    }
                }
                // invalid account
                else {
                    RxTx.send(instructions.get("lcdInvalid"));
                    RxTx.wait(500);
                    RxTx.send(keyPadInit);
                    System.out.println("ID Incomplete");
                }
            }
        }
    }
}
