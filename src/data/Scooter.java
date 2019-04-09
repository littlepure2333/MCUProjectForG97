package data;

public class Scooter {
	public int id;
	public int used; // 1为已被用，0为未被用
	
	public Scooter(int id, int used) {
		this.id = id;
		this.used = used;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int ifUsed() {
		return used == 1 ? 1 : 0;
	}

}
