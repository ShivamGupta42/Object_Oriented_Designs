package designs.ratelimiter1.ratelimiter;

public class TokenBucketRateLimiter implements RateLimiter{
    private long timeOfLastRequest = 0L;
    private final int maxTokensPerMinute;
    private final Object tokenLock;
    private final Integer[] timeStamps;
    private final Integer[] hits;

    public TokenBucketRateLimiter(int tokensPerMinute){
        maxTokensPerMinute = tokensPerMinute;
        this.tokenLock = new Object();
        timeStamps = new Integer[60];
        hits = new Integer[60];

    }

    public boolean accept(int curTimeStampInSeconds){

        synchronized (tokenLock){
                return calculateTokenGeneration(curTimeStampInSeconds);
        }
    }

    private boolean calculateTokenGeneration(int curTimeStamp) {
        int totalHits = getHits(curTimeStamp);

        if(totalHits>=maxTokensPerMinute){
            return false;
        }

        //Update the hits counts
        int timeIndex = curTimeStamp%60;

        if(timeStamps[timeIndex]==null||timeStamps[timeIndex]!=curTimeStamp){
            timeStamps[timeIndex]=curTimeStamp;
            hits[timeIndex]=1;
        }else{
            hits[timeIndex]++;
        }

        return true;
    }

    private int getHits(int curTimeStamp) {

        int sum=0;
        for(int i=0;i<60;i++){
            if((timeStamps[i]!=null)&&(curTimeStamp-timeStamps[i]<60)){
                sum+=hits[i];
            }
        }
        return sum;
    }

}
