package designs.ratelimiter1.ratelimiter;

public interface RateLimiter {
    boolean accept(int curTimeStampInSeconds);
}
