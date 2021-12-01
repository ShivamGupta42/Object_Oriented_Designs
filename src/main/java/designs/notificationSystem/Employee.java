package designs.notificationSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Employee implements Observer {
	Boolean isManager;
	Employee Manager;
	List<Message> messages;
	List<Employee> subOrdinates;
	String name;

	public Employee(String name, Employee manager) {
		this.name = name;
		this.Manager = manager;
		isManager = false;
		messages = new LinkedList<Message>();
		subOrdinates = new ArrayList<Employee>();
	}

	@Override
	public void getNotified(Message msg) {
		messages.add(msg);
		if (isManager) {
			subOrdinates.parallelStream().forEach(e -> e.getNotified(new Message(msg)));
		}
	}

	public void notifyPeers(Message msg) {
		Manager.getSubOrdinates().parallelStream().forEach(e -> e.getNotified(new Message(msg)));
	}

	public Employee getManager() {
		return Manager;
	}

	public void setManager(Employee manager) {
		Manager = manager;
	}

	public List<Employee> getSubOrdinates() {
		return subOrdinates;
	}

	public void addSubOrdinates(Employee subOrdinate) {
		isManager = true;
		subOrdinates.add(subOrdinate);
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Emp: " + name + "\n");
		for (Message m : messages) {
			sb.append(m.getMessage() + "\n");
		}
		return sb.toString();
	}

}
