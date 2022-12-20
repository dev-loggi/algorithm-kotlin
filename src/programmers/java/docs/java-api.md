## Java docs

```java
import java.util.*;

class Main {

    void aboutPrimaryType() {
        // Integer
        int a = 20;
        Integer.parseInt("20");
        Integer.parseInt("20", 2);
        Integer.toBinaryString(123);
        Integer.MIN_VALUE;
        Integer.MAX_VALUE;

        // String
        String.valueOf(20);

        String str = "abcd";
        str.length();
        str.charAt(0);
        str.toCharArray();
        str.replace("a", "b");
        str.split(",");
    }

    void aboutArray() {
        int[] arr;
        arr = new int[10];
        arr = new int[]{1, 2, 3};
        arr = {1, 2, 3};

        Arrays.sort(arr);
        Arrays.fill(arr, 10);
        Arrays.toString(arr);
        Arrays.binarySearch(arr, 10);
        Arrays.copyOf(arr, arr.length);
        Arrays.copyOfRange(arr, 2, 5);
    }

    void aboutCollections() {
        // java.util.ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.size();
        arrayList.isEmpty();
        arrayList.clear();
        arrayList.sort(Comparator.naturalOrder());
        arrayList.sort(Comparator.reverseOrder());

        // java.util.PriorityQueue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        minHeap.size();
        minHeap.offer(10);
        minHeap.poll();
        minHeap.peek();

        //
        Deque<Integer> queue = new ArrayDeque<>();
        queue.isEmpty();
        queue.size();
        queue.offer(1);
        queue.poll();
        queue.peek();
    }
}
```