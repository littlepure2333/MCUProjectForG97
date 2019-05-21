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
        // 对数据进行修改后，立即更新XML
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
                        .returnScooter(), slotId);
        // 对数据进行更改后，立即更新XML
        updateData();
    }

    /**
     * 根据单车id返回指定的单车数据
     *
     * @param scooterId 指定的单车id
     * @return 单车数据对象
     */
    static Scooter findScooterById(int scooterId) {
        for (Scooter scooter : scooters) {
            if (scooter.getId() == scooterId)
                return scooter;
        }
        return null;
    }

}
