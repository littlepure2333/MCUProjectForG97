package data;


public class Station {
	public int id;
	public Scooter[] slot;
	public int slotSize;
	
	public Station(int id) {
		this.id = id;
		slot = new Scooter[this.slotSize];
	}
	
	public int getId() {
		return this.id;
	}
	
	/* 返回station的非空slot数量 */
	public int size() {
		int count = 0;
		for(int i = 0; i < this.slotSize; i++) {
			if(slot[i] != null) {
				count++;
			}
		}
		return count;
	}
	
	/* 判断station是否slot全占满了 */
	public boolean isFull() {
		if (this.size() == this.slotSize) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* 判断station是否slot全为空 */
	public boolean isEmpty() {
		if (this.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* 添加成功返回true， 添加失败返回false */
	public boolean addScooter(Scooter item) {
		if (this.isFull()) {
			return false;
		}
		else {
			for (int i = 0; i < this.slotSize; i++) {
				if (slot[i] != null) {
					slot[i] = item;
					break;
				}
			}
			return true;
		}
	}
	
	/* remove成功返回scooter，remove失败返回null */
	public Scooter removeScooter() {
		Scooter item = null;
		if (!this.isEmpty()) {
			for (int i = 0; i < this.slotSize; i++) {
				if (slot[i] != null) {
					item = slot[i];
				}
			}
		}
		return item;
	}
	

}
