package data;

/**
 * Entity class used to present stations
 */
public class Station {
	/**
	 * station name
	 */
	public String name;
	/**
	 * station slot for holding scooters
	 */
	public Scooter[] slot;
	/**
	 * slot size of the staiton
	 */
	public int slotSize;

	public Station() {

	}

	/**
	 * Create a station with its name and its slot size
	 *
	 * @param name     station name
	 * @param slotSize slot size of the station
	 */
	public Station(String name, int slotSize) {
		this.name = name;
		this.slotSize = slotSize;
		slot = new Scooter[slotSize];
	}

	/**
	 * Get the slot data of the station
	 *
	 * @return slot data of the station
	 */
	public Scooter[] getSlot() {
		return slot;
	}

	/**
	 * Get the name of the station
	 *
	 * @return station name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the size of the station's slot
	 *
	 * @return slot size of the station
	 */
	int getSlotSize() {
		return this.slotSize;
	}

	/**
	 * Get the number of unoccupied slots
	 *
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
	 *
	 * @param scooter the scooter
	 * @param slotID  the slot ID
	 */
	public void loadScooter(Scooter scooter, int slotID) {
		scooter.setUsed(0);
		this.slot[slotID] = scooter;
	}

	/**
	 * remove a scooter from a slot
	 *
	 * @param slotId the slot id
	 * @return the scooter
	 */
	public Scooter removeScooter(int slotId) {
		Scooter scooter = this.slot[slotId];
		scooter.setUsed(1);
		this.slot[slotId] = null;
		return scooter;
	}

	/**
	 * Output the station data
	 *
	 * @return station data
	 */
	@Override
	public String toString() {
		return name + " " + size() + " " + (this.slotSize - size()) + " " + this.slotSize;
	}

	/**
	 * Check if the station is full.
	 *
	 * @return full or not full
	 */
	private boolean isFull() {
		return this.size() == this.slotSize;
	}

	/**
	 * Add a scooter to a slot
	 *
	 * @param item the scooter
	 * @return add success or failure
	 */
	public boolean addScooter(Scooter item) {
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
