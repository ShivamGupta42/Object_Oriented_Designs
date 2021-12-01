package designs.commentFilteringSystem;

public class Person {
	String name;
	boolean isVerifiedUser;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVerifiedUser() {
		return isVerifiedUser;
	}

	public void setVerifiedUser(boolean isVerifiedUser) {
		this.isVerifiedUser = isVerifiedUser;
	}

}
