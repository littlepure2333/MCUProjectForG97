package data;

/**
 * Entity class used to present stations
 */
public class Station {
	public String name;
	public Scooter[] slot;
	public int slotSize;

	public Station() {

	}

	public Station(String name, int slotSize) {
		this.name = name;
		this.slotSize = slotSize;
		slot = new Scooter[slotSize];
	}

	public Scooter[] getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	int getSlotSize() {
		return this.slotSize;
	}

	/**
	 * Get the number of unoccupied slots
	 * @return the number of unoccupied slots
	 */
	private int size() {
		int count = 0;
		for (int i = 0; i < this.slotSize; i++) {
			if (slot[i] != null) {
				count++;
			}
		}
		return count;
	}

	/**
	 * load a scooter to an unoccupied slot
	 * @param scooter the scooter
	 * @param slotID the slot ID
	 */
	public void loadScooter(Scooter scooter, int slotID) {
		scooter.setUsed(0);
		this.slot[slotID] = scooter;
	}

	/**
	 * remove a scooter from a slot
	 * @param slotId the slot id
	 * @return the scooter
	 */
	public Scooter removeScooter(int slotId) {
		Scooter scooter = this.slot[slotId];
		scooter.setUsed(1);
		this.slot[slotId] = null;
		return scooter;
	}

	@Override
	public String toString() {
		return name + " " + size() + " " + (this.slotSize - size()) + " " + this.slotSize;
	}

	/**
	 * (For test) Check if the station is full.
	 * @return full or not
	 */
	private boolean isFull() {
		return this.size() == this.slotSize;
	}

	/**
	 * (For test) add a scooter to a slot
	 * @param item the scooter
	 * @return add success or failure
	 */
	boolean addScooter(Scooter item) {
		if (this.isFull()) {
			return false;
		} else {
			for (int i = 0; i < this.slotSize; i++) {
				if (slot[i] == null) {
					slot[i] = item;
					break;
				}
			}
			return true;
		}
	}
}
