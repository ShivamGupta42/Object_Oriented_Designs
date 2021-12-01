package designs.circulararray;

public class CiccularArrayTest {

	public static void main(String[] args) {

		CircularArray<Integer> array = new CircularArray<>(5);
		array.set(0, 1);
		array.set(1, 2);
		array.set(2, 3);
		array.set(3, 4);
		array.set(4, 5);

		for (Integer i : array) {
			System.out.print(i + " ");
		}

		array.rotate(2);
		System.out.println();

		for (Integer i : array) {
			System.out.print(i + " ");
		}
	}

}
