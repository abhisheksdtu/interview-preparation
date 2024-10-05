/*
    APPROACH - BRUTE
    1. Sort the given array in ascending order.
    2. The k-th largest element in the sorted array will be at the position nums.length - k.
    3. Return the element at that position.

    TIME COMPLEXITY
    - O(n log n), where n is the number of elements in the array. Sorting the array takes O(n log n) time.

    SPACE COMPLEXITY
    - O(1), since we are sorting the array in place and using no additional space.
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);  // Sort the array in ascending order
        return nums[nums.length - k];  // Return the k-th largest element
    }
}

/*
    APPROACH - BETTER
    1. Use a min-heap (PriorityQueue) to keep track of the k largest elements.
    2. Traverse through the array. For the first k elements, simply add them to the heap.
    3. For the remaining elements, if an element is larger than the root of the heap (smallest of the k largest elements), remove the root and add the new element.
    4. The root of the heap will always be the kth largest element after traversing the array.

    TIME COMPLEXITY
    - O(n log k), where n is the number of elements in the array, and k is the size of the heap. Inserting into the heap takes O(log k) time.

    SPACE COMPLEXITY
    - O(k), as we are maintaining a heap of size k.

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-heap to store the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                // Add first k elements to the heap
                minHeap.add(nums[i]);
            } else {
                // For remaining elements, maintain the k largest elements in the heap
                if (nums[i] > minHeap.peek()) {
                    minHeap.remove();  // Remove the smallest element
                    minHeap.add(nums[i]);  // Add the current element if it's larger
                }
            }
        }
        
        // The root of the heap is the kth largest element
        return minHeap.peek();
    }
}


/*
    APPROACH - BETTER
    1. Use a Min-Heap (PriorityQueue) to keep track of the top k largest elements in the array.
    2. Traverse the array and add elements to the PriorityQueue.
    3. If the size of the PriorityQueue exceeds k, remove the smallest element. This ensures that the PriorityQueue contains only the k largest elements.
    4. At the end of the traversal, the root of the PriorityQueue (the smallest element in the heap) will be the k-th largest element.
    5. Return the root of the PriorityQueue.

    TIME COMPLEXITY
    - O(n log k), where n is the number of elements in the array. Adding an element to the PriorityQueue takes O(log k), and we do this for all n elements.

    SPACE COMPLEXITY
    - O(k), since the PriorityQueue stores only k elements.

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap to store the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Traverse the array and maintain the k largest elements in the heap
        for (int num : nums) {
            minHeap.add(num);

            // If the size of the heap exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.remove();
    }
}
