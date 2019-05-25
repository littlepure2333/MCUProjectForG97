package bin;

import data.AppData;
import data.Scooter;

/**
 * 单车管理操作
 */
public class ScooterManage extends AppData {
    /**
     * 直接从State里面获取当前用户，当前站点，当前车槽
     * 进行借车操作，车从station拿出来，放到user里
     * 没有检查State数据是否正确，所以使用时一定确保数据正确
     */
    public static void takeScooter() {
        AppState.getCurrentUser()
                .takeScooter(AppState.getCurrentStation()
                        .removeScooter(AppState.getCurrentSlot()));
        TransactionManage.generateTransaction("take");
        findScooterById(AppState.getCurrentUser().getScooter().getId()).setUsed(1);
        updateData();
    }

    /**
     * 直接从State里面获取当前用户，当前站点，当前车槽
     * 进行还车操作，车从user拿出来，放到station里
     * 没有检查State数据是否正确，所以使用时一定确保数据正确
     */
    public static void returnScooter() {
        TransactionManage.generateTransaction("return");
        int slotId = AppState.getCurrentSlot();
        AppState.getCurrentStation()
                .loadScooter(AppState.getCurrentUser()
                                .returnScooter(),
                        slotId);
        findScooterById(AppState.getCurrentStation().getSlot()[AppState.getCurrentSlot()].getId()).setUsed(0);
        updateData();
    }

    /**
     * Check that how many scooters are in used state
     * @return the number of scooters being used
     */
    public static int getUsedCount() {
        int count = 0;
        for (Scooter scooter : scooters) {
            if (scooter.getUsed() == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 根据单车id返回指定的单车数据
     *
     * @param scooterId 指定的单车id
     * @return 单车数据对象
     */
    private static Scooter findScooterById(int scooterId) {
        for (Scooter scooter : scooters) {
            if (scooter.getId() == scooterId)
                return scooter;
        }
        return null;
    }
}
