package designs.multithreading.Algorithms;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {


    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int m = 0, n = 0, index = 0;

        while (m < left.length && n < right.length) {
            if (left[m] < right[n]) {
                res[index++] = left[m++];
            } else {
                res[index++] = right[n++];
            }
        }

        while (m < left.length) {
            res[index++] = left[m++];
        }

        while (n < right.length) {
            res[index++] = right[n++];
        }

        return res;
    }

    public static void main(String[] args) {
        int totalRuns = 1000000;

        long start =0L;
        int[] arr = new int[]{6,5,4,3,2,1};
        double[] times = new double[totalRuns];

        for(int i=0;i<totalRuns;i++){
            start = Instant.now().getNano();
            mergeSort(arr);
            times[i]=(Instant.now().getNano()-start)/1000;
        }

        System.out.printf("The average runtime of %d is %f",totalRuns, Arrays.stream(times).average().getAsDouble());

    }
}
