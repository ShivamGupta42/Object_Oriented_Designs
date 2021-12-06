package designs.ratelimiter1;

import usermanagement.UserKeyGenerator;
import usermanagement.UserResourceLimitManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFlow {

    public static void main(String[] args) {

        String userName = "testUser";
        UserKeyGenerator.generateUserId(userName);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new SampleRequest(userName));
        executorService.shutdownNow();

    }


    static class SampleRequest implements Runnable{

        private final String name;

        SampleRequest(String name){
            this.name=name;
        }

        @Override
        public void run() {

            for(int i=0;i<100;i++) {
                boolean reqResult = UserResourceLimitManager.allowRequest(name,"res1",i);
                System.out.println("Result of request on res1 at "+i+" :"+reqResult);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
