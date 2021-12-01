package designs.circulararray;

import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {
	T[] data;
	int size = 0;
	int head = 0;

	public CircularArray(int maxCapacity) {
		data = (T[]) new Object[maxCapacity];
	}

	public int convert(int index) {
		if (index < 0) {
			index += data.length;
		}

		return (head + index) % data.length;
	}

	public void rotate(int shiftRight) {
		head = convert(shiftRight);
	}

	public T get(int index) {
		return data[convert(index)];
	}

	public void set(int index, T item) {
		data[convert(index)] = item;
	}

	@Override
	public Iterator<T> iterator() {
		return new CircularArrayIterator<T>();
	}

	private class CircularArrayIterator<T1> implements Iterator<T1> {

		// Where we are at reading the current circular array
		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < data.length;
		}

		@Override
		public T1 next() {
			
			T1 item = (T1) data[convert(current)];
			++current;
			return item;
		}
	}

}
