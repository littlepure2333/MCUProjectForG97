package data;

/**
 * Entity class used to present scooters
 */
public class Scooter {
	public int id;
	public int used; // 1 - in use, 0 - not in use

	public Scooter() {

	}

	public Scooter(int id, int used) {
		this.id = id;
		this.used = used;
	}

	public int getId() {
		return this.id;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
}
