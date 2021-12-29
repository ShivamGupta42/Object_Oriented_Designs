package designs.multithreading.Problems;

import java.util.concurrent.Semaphore;

public class UnisexBathRoom {

	static class Females implements Runnable {
		int FemaleId;
		UnisexBathRoom bathRoom;

		public Females(int id, UnisexBathRoom bathRoom) {
			FemaleId = id;
			this.bathRoom = bathRoom;
		}

		@Override
		public void run() {

			try {
				System.out.println("Female " + FemaleId + " trying to get in the bathroom");
				bathRoom.femalesGettingIn();
				System.out.println("Female " + FemaleId + " is using the bathroom");
				Thread.sleep(3000);
				bathRoom.femalesGettingOut();
				System.out.println("Female " + FemaleId + " is getting out of the bathroom");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	static class Males implements Runnable {
		int MaleId;
		UnisexBathRoom bathRoom;

		public Males(int id, UnisexBathRoom bathRoom) {
			MaleId = id;
			this.bathRoom = bathRoom;
		}

		@Override
		public void run() {

			try {
				System.out.println("Male " + MaleId + " trying to get in the bathroom");
				bathRoom.malesGettingIn();
				System.out.println("Male " + MaleId + " is using the bathroom");
				Thread.sleep(3000);
				bathRoom.malesGettingOut();
				System.out.println("Male " + MaleId + " is getting out of the bathroom");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	Semaphore bathRoomUsers;
	int malesPresent = 0;
	int femalesPresent = 0;
	boolean isNonePresent = true;
	int bathRoomSize;

	public UnisexBathRoom(int bathRoomSize) {
		bathRoomUsers = new Semaphore(bathRoomSize);
	}

	public synchronized void femalesGettingIn() throws InterruptedException {
		while (malesPresent != 0) {
			wait();
		}
		bathRoomUsers.acquire();
		femalesPresent++;
	}
	public synchronized void femalesGettingOut() throws InterruptedException {
		bathRoomUsers.release();
		femalesPresent--;
		notifyAll();
	}
	public synchronized void malesGettingIn() throws InterruptedException {
		while (femalesPresent != 0) {
			wait();
		}
		bathRoomUsers.acquire();
		malesPresent++;
	}
	public synchronized void malesGettingOut() throws InterruptedException {
		bathRoomUsers.release();
		malesPresent--;
		notifyAll();
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		UnisexBathRoom bathRoom = new UnisexBathRoom(3);
		Thread F1 = new Thread(new Females(1, bathRoom));
		Thread F2 = new Thread(new Females(2, bathRoom));
		Thread F3 = new Thread(new Females(3, bathRoom));

		Thread M1 = new Thread(new Males(1, bathRoom));
		Thread M2 = new Thread(new Males(2, bathRoom));
		Thread M3 = new Thread(new Males(3, bathRoom));

		F1.start();
		F2.start();
		M1.start();
		F3.start();
		M2.start();
		M3.start();

		F1.join();
		F2.join();
		F3.join();
		M1.join();
		M2.join();
		M3.join();

	}

}
