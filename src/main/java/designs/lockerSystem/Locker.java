package designs.lockerSystem;

import java.util.Optional;

public class Locker implements Comparable<Locker> {
	int size;
	int lockerId;
	int locationId;
	Package packageInside;

	public Locker(int size, int lockerId, int location) {
		super();
		this.size = size;
		this.lockerId = lockerId;
		this.locationId = location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLockerId() {
		return lockerId;
	}

	public void setLockerId(int lockerId) {
		this.lockerId = lockerId;
	}

	public int getLocation() {
		return locationId;
	}

	public void setLocation(int location) {
		this.locationId = location;
	}

	public boolean storePackage(Package p) {
		if (this.size >= p.getSize()) {
			packageInside = p;
			return true;
		}

		return false;
	}

	public Optional<Package> getPackage() {
		return Optional.ofNullable(packageInside);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + locationId;
		result = prime * result + lockerId;
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locker other = (Locker) obj;
		if (locationId != other.locationId)
			return false;
		if (lockerId != other.lockerId)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public int compareTo(Locker o) {
		return this.size - o.size;
	}

}
