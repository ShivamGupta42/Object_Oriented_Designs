package designs.hitCounter;

import java.util.Arrays;
import java.util.Objects;

public class HitCounterSingleMachine {

	final Long machineId;
	int[] hits; //t-> 1038 (50), t-> 2309 (100), 
	int[] timeStamps;  //t->1038 , t->2309, t->37
	int timeRangeInSeconds;

	//300 seconds
	public HitCounterSingleMachine(int timeRangeInSeconds, long machineId) {
		hits = new int[timeRangeInSeconds];
		timeStamps = new int[timeRangeInSeconds];
		this.timeRangeInSeconds = timeRangeInSeconds;
		this.machineId = machineId;
	}

	/*
	 * Let's say timeRange is 300 seconds Then we can track timeStamp as 37Seconds
	 * since start -> 337 seconds since start -> 667 seconds since start All of the
	 * above values will map to same id -> 37 Our timeStamp[id] will keep track of
	 * which timeStamp we are keeping track of right now
	 * 
	 * S1) If say we are tracking t -37 seconds, suddenly we recieve a request of t
	 * 337 seconds then we start tracking that timeStamp, by increasing the hits
	 * 
	 * 
	 * Note: -> This approach requires the hit reporting systems, to work linearly
	 * means to report 37 seconds hits only when (0-300) Since system startup and
	 * not during (300-600) or further intervals, Since it basically defeats the
	 * purpose
	 * 
	 * -> I have made another impl which only records newer timestamps than current
	 * ones
	 * 
	 */
	
	
	// timStamp array to see which timeStamp we are currently monitoring
	// Hits array to monitor hits on that timestamp 
	
	// timeStap[37]-> 37, hits[37]->10 
	// timeStamp[37] -> 337, hits[37]->1
	
	synchronized void hit(int timeStamp) {
		int id = timeStamp % timeRangeInSeconds;
		if (timeStamps[id] != timeStamp) {
			timeStamps[id] = timeStamp;
			hits[id] = 1;
		} else {
			hits[id] += 1;
		}
	}

	synchronized void hit2(int timeStamp) {
		int id = timeStamp % timeRangeInSeconds;
		if (timeStamps[id] < timeStamp) {
			timeStamps[id] = timeStamp;
			hits[id] = 1;
		} else {
			hits[id]++;
		}
	}

	/*
	 * You provide a timeStamp like t0 -> 435 seconds, t->867 seconds since start of
	 * website I look into my timeStamps array and if the timeStamps, stored there
	 * are in the range Of what we are reliably offering then we return the result
	 * to you
	 * 
	 */
	
	
   // t-> 450 seconds 
   // timestamp[1] -> 1
	synchronized int getHits(int timeStamp) {
		int result = 0;

		for (int i = 0; i < timeStamps.length; i++) {
			if (timeStamp - timeStamps[i] < timeRangeInSeconds) {
				result += hits[i];
			}
		}

		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.machineId.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		HitCounterSingleMachine other = (HitCounterSingleMachine) obj;

		return Objects.deepEquals(this.machineId, other.machineId);
	}

}
