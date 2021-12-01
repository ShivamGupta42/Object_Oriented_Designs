package designs.notificationSystem;

public class Main {

	public static void main(String[] args) {
		Employee ceo = new Employee("ceo", null);

		Employee manager1 = new Employee("m1", ceo);
		Employee manager2 = new Employee("m2", ceo);
		ceo.addSubOrdinates(manager1);
		ceo.addSubOrdinates(manager2);

		Employee manager3 = new Employee("m3", manager1);
		manager1.addSubOrdinates(manager3);
		Employee emp1 = new Employee("e1", manager3);
		Employee emp2 = new Employee("e2", manager3);
		Employee emp3 = new Employee("e3", manager3);
		manager3.addSubOrdinates(emp1);
		manager3.addSubOrdinates(emp2);
		manager3.addSubOrdinates(emp3);

		Employee manager4 = new Employee("m4", manager2);
		manager2.addSubOrdinates(manager4);
		Employee emp4 = new Employee("e4", manager4);
		Employee emp5 = new Employee("e5", manager4);
		Employee emp6 = new Employee("e6", manager4);
		manager4.addSubOrdinates(emp4);
		manager4.addSubOrdinates(emp5);
		manager4.addSubOrdinates(emp6);

		ceo.getNotified(new Message("This is hi from ceo"));
		manager1.getNotified(new Message("Hellow from manager1"));
		manager4.getNotified(new Message("Hellow from manager4"));

		///////
		System.out.println(emp1.toString());
		System.out.println(emp2.toString());
		System.out.println(emp3.toString());
		System.out.println(emp4.toString());
		System.out.println(emp5.toString());
		System.out.println(emp6.toString());

	}

}
