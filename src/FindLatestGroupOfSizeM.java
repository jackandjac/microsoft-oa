import java.util.*;

/**
 * 1562. Find Latest Group of Size M
 * It's also named as Rose Garden in Amazon OA
 * */

public class FindLatestGroupOfSizeM {

    public int findLatestStep(int[] arr, int m) {
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int[] lens = new int[arr.length + 1];
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            int pos = arr[i];
            int ceil = sizeMap.getOrDefault(pos - 1, 0), floor = sizeMap.getOrDefault(pos + 1, 0), nWidth = ceil + floor + 1;
            if (ceil > 0) lens[ceil]--;
            if (floor > 0) lens[floor]--;
            sizeMap.put(pos - ceil, nWidth);
            sizeMap.put(pos + floor, nWidth);
            lens[nWidth]++;
            if (lens[m] > 0) result = i + 1;
        }
        return result;
    }

    // time limit exceeded v1
    public int findLatestStepTLE(int[] arr, int m) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        char[] content = new char[arr.length];
        Arrays.fill(content, '0');

        for (int i = 0; i < arr.length; i++) {
            content[arr[i] - 1] = '1';
            List<Integer> sizes = count(content);
            for (int size : sizes) {
                // map.computeIfAbsent(size, k-> new PriorityQueue<>(Collections.reverseOrder())).add(i+1);
                if (map.containsKey(size)) {
                    map.get(size).add(i + 1);
                } else {
                    PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
                    queue.add(i + 1);
                    map.put(size, queue);
                }
            }

        }
        PriorityQueue<Integer> queue = map.get(m);
        return queue == null ? -1 : queue.poll();
    }

    private List<Integer> count(char[] array) {
        List<Integer> res = new ArrayList<>();
        int counter = 0;
        int idx = 0;
        while (idx < array.length) {
            if (array[idx] == '1') {
                idx++;
                counter++;
            } else {
                if (counter != 0) {
                    res.add(counter);
                    counter = 0;
                }
                while (idx < array.length && array[idx] == '0') idx++;
            }
        }
        if (counter != 0) res.add(counter);

        return res;
    }

    // time limite exceeded v2
    public int findLatestStepTLEV2(int[] arr, int m) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        char[] content = new char[arr.length];
        Arrays.fill(content, '0');

        for (int i = 0; i < arr.length; i++) {
            content[arr[i] - 1] = '1';
            List<Integer> sizes = countV2(content.clone());
            for (int size : sizes) {
                map.computeIfAbsent(size, k -> new PriorityQueue<>(Collections.reverseOrder())).add(i + 1);
            }

        }

        PriorityQueue<Integer> queue = map.get(m);
        return queue == null ? -1 : queue.poll();
    }

    private List<Integer> countV2(char[] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '1') {
                // bfs
                int count = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int j = 0; j < size; j++) {
                        int idx = queue.poll();
                        array[idx] = '0';
                        count++;
                        int left = idx - 1;
                        int right = idx + 1;
                        if (left >= 0 && array[left] == '1') queue.offer(left);
                        if (right < array.length && array[right] == '1') queue.offer(right);
                    }
                }
                result.add(count);
            }
        }
        return result;
    }
}
