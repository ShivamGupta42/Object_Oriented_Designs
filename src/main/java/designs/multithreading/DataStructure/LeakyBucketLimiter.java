package designs.multithreading.DataStructure;

import java.util.Date;

/**
 * Leaky bucket algorithm to prevent huge amounts of SMS text messages from
 * being dispatched by any insane processes. Each SMS message sent adds a drop
 * to the bucket which leaks at a constant rate. Once the bucket fills, no
 * message can be sent until a drop has leaked out.
 */
public class LeakyBucketLimiter {

	private int numDropsInBucket = 0;
	private Date timeOfLastDropLeak = null;
	private final int _BUCKET_SIZE_IN_DROPS = 20;
	private final long _MS_BETWEEN_DROP_LEAKS = 1000 * 60 * 60; // 1 hour //1 Message per hour

	// You are just adding drops to the bucket
	// It is same as adding messages to the bucket
	// Which are picked on in intervals from some other thread

	public synchronized boolean addDropToBucket() {
		Date now = new Date();
		// first of all, let the bucket leak by the appropriate amount
		if (timeOfLastDropLeak != null) {
			long deltaT = now.getTime() - timeOfLastDropLeak.getTime();
			// note round down as part of integer arithmetic
			long numberToLeak = deltaT / _MS_BETWEEN_DROP_LEAKS;
			if (numberToLeak > 0) { // now go and do the leak
				if (numDropsInBucket <= numberToLeak) {
					numDropsInBucket = 0;// this means that we will leak all drops
					// You will execute all the numDropsInBucketsTask

				} else {
					numDropsInBucket -= (int) numberToLeak; // we will leak only those drops which are eligible
					// You will execute 25 tasks
				}
				timeOfLastDropLeak = now;
			}
		}

		if (numDropsInBucket < _BUCKET_SIZE_IN_DROPS) {
			numDropsInBucket++;
			return true; // drop added
		}

		return false; // overflow
	}

	public static void main(String[] args) {
		// here is how you use it
		LeakyBucketLimiter bucketLimiter = new LeakyBucketLimiter();
		if (bucketLimiter.addDropToBucket()) {
			// dispatch SMS
		}
	}
}
