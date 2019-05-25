package data;


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

	/* 返回station的非空slot数量 */
	private int size() {
		int count = 0;
		for (int i = 0; i < this.slotSize; i++) {
			if (slot[i] != null) {
				count++;
			}
		}
		return count;
	}

	/*
	需要保证原slot为空
	 */
	public void loadScooter(Scooter scooter, int slotID) {
		scooter.setUsed(0);
		this.slot[slotID] = scooter;
	}

	/* remove成功返回scooter，remove失败返回null */
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

	//测试专用
	/* 判断station是否slot全占满了 */
	private boolean isFull() {
		return this.size() == this.slotSize;
	}

	//测试专用
	/* 添加成功返回true， 添加失败返回false */
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
