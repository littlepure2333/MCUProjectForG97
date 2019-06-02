package mcu;

public class ConfirmTakeReturnBranch {
    private byte[] keyPadInit = {0x12, 0x7F};
    ConfirmTakeReturnBranch() {
        RxTx.wait(1000);
        RxTx.send(keyPadInit);
        waitForInput();
    }

    private void waitForInput() {
        while (true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                // if confirmed
                if (result == Communication.TAKE_OPTION) {
                    System.out.println("confirm");
                    return;
                } else {
                    //
                    System.out.println("else");
                    RxTx.send(keyPadInit);
                }
            }
        }
    }

    private void takeOrReturnAction() {
        
    }
}
