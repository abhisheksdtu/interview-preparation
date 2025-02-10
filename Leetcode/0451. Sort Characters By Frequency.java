/*
    APPROACH - HEAP SORTING
    1. Create a frequency map to count occurrences of each character.
    2. Use a Max-Heap (PriorityQueue) to store characters based on their frequency in descending order.
    3. Add all characters to the heap.
    4. Extract characters from the heap and append them to the result string based on their frequency.
    5. Return the final sorted string.

    TIME COMPLEXITY
    - O(N log K), where N is the string length and K is the number of unique characters. 
      - Creating the frequency map takes O(N).
      - Adding elements to the heap takes O(K log K).
      - Extracting elements and constructing the result string takes O(N).

    SPACE COMPLEXITY
    - O(K), where K is the number of unique characters stored in the map and heap.
*/

class Solution {
    public String frequencySort(String s) {
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();

        // Count frequency of each character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Max-Heap to store characters by frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> {
                    int freq = b.getValue() - a.getValue(); // Sort by highest frequency
                    if (freq == 0) {
                        return a.getKey() - b.getKey(); // Sort lexicographically for equal frequency
                    }
                    return freq;
                });

        pq.addAll(map.entrySet());

        // Build result string
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> e = pq.remove();
            for (int i = 0; i < e.getValue(); i++) {
                res.append(e.getKey());
            }
        }

        return res.toString();
    }
}
