package bin;

import data.AppData;
import data.Scooter;

/**
 * Control Class
 * Manage the transmission, statistics and quires of scooter data.
 */
public class ScooterManage extends AppData {
    /**
     * Acquire current user, station and slot from the state directly,
     * remove the scooter from the slot, then let the user holds it.
     */
    public static void takeScooter() {
        AppState.getCurrentUser().takeScooter(
                        AppState.getCurrentStation()
                                .removeScooter(AppState.getCurrentSlot()));
        TransactionManage.generateTransaction("take");
        findScooterById(AppState.getCurrentUser().getScooter().getId()).setUsed(1);
        updateData();
    }

    /**
     * Acquire current user, station and slot from the state directly,
     * remove the scooter from the user, then let the slot holds that scooter.
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
     * Find the information of a scooter by its id
     * @param scooterId scooter id
     * @return the information of the scooter
     */
    private static Scooter findScooterById(int scooterId) {
        for (Scooter scooter : scooters) {
            if (scooter.getId() == scooterId)
                return scooter;
        }
        return null;
    }
}
