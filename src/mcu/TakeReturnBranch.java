package mcu;

import static mcu.Program.instructions;

/**
 * Enter the take return function
 * Exit only when the user input sth.
 */
class TakeReturnBranch {

    TakeReturnBranch() {
        RxTx.send(instructions.get("lcdChooseAct"));
        RxTx.send(instructions.get("keyBoardInit"));
        waitForInput();
    }

    /**
     * the program wait an input from the user and
     * get into different functions
     */
    private void waitForInput() {
        while (true) {
            RxTx.wait(1000);
            if (!RxTx.communication.getReceiveBuffIsChecked()) {
                RxTx.communication.setReceiveBuffIsChecked(true);
                int result = RxTx.communication.receiveTakeOrReturn();
                switch (result) {
                    // 如果是借车
                    case Communication.TAKE_OPTION:
                        System.out.println("take");
                        Program.takeOrReturn = 1;
                        return;
                    // if return
                    case Communication.RETURN_OPTION:
                        System.out.println("return");
                        Program.takeOrReturn = 0;
                        return;
                    // if not take/return
                    default:
                        System.out.println("choose error, back");
                        Program.resetFlag = true;
                        return;
                }
            }
        }
    }
}


