package designs.lockerSystem;

public class Package {
	int id;
	int size;
	String description;

	public Package(int id, int size, String description) {
		super();
		this.id = id;
		this.size = size;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
