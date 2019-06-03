package data;

/**
 * Entity class used to present scooters
 */
public class Scooter {
	/**
	 * the scooter's id
	 */
	public int id;
	/**
	 * if the scooter is used, when a new scooter is created, this field should be 0
	 */
	public int used;

	public Scooter() {

	}

	/**
	 * Create a scooter with it's id
	 *
	 * @param id scooter's id
	 */
	public Scooter(int id) {
		this.id = id;
		this.used = 0;
	}

	/**
	 * Get the scooter id
	 *
	 * @return scooter id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get if the scooter is used
	 *
	 * @return use state of scooter
	 */
	public int getUsed() {
		return used;
	}

	/**
	 * Change the scooter use state
	 *
	 * @param used use state of scooter
	 */
	public void setUsed(int used) {
		this.used = used;
	}
}
