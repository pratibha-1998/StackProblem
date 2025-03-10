class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // Stack to store indexes of bars
        Stack<Integer> st = new Stack<>();

        // Arrays to store the index of the nearest smaller elements on left & right
        int leftSmall[] = new int[n];  
        int rightSmall[] = new int[n];

        // STEP 1: Find Nearest Smaller to Left (NSL) for each bar
        for (int i = 0; i < n; i++) {
            // Remove elements from stack that are greater than or equal to current bar
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            // If stack is empty, no smaller element exists → left boundary = 0
            if (st.isEmpty()) 
                leftSmall[i] = 0;
            else 
                leftSmall[i] = st.peek() + 1;  // Next valid position for rectangle to start

            st.push(i);  // Push current index to stack
        }

        // Clear the stack before reusing
        while (!st.isEmpty()) 
            st.pop();

        // STEP 2: Find Nearest Smaller to Right (NSR) for each bar
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements from stack that are greater than or equal to current bar
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            // If stack is empty, no smaller element exists → right boundary = last index
            if (st.isEmpty()) 
                rightSmall[i] = n - 1;
            else 
                rightSmall[i] = st.peek() - 1;  // Rectangle stops before next smaller element

            st.push(i);  // Push current index to stack
        }

        // STEP 3: Calculate Maximum Area
        int maxA = 0;
        for (int i = 0; i < n; i++) {
            int width = rightSmall[i] - leftSmall[i] + 1;  // Width of the rectangle
            int area = heights[i] * width;  // Area = height × width
            maxA = Math.max(maxA, area);  // Update max area
        }

        return maxA;  // Return the largest rectangle area
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + sol.largestRectangleArea(heights));
    }
}

.......................................Explenation---------------------------------

  🔹 Step-by-Step Breakdown
1️⃣ Finding Nearest Smaller to Left (NSL)
We traverse from left to right.
Maintain a monotonic increasing stack (smallest values at the bottom).
For each bar:
Remove larger elements from the stack.
If the stack is empty, set leftSmall[i] = 0 (start from the beginning).
Otherwise, set leftSmall[i] = st.peek() + 1 (start after the nearest smaller).
Push the current index to the stack.
2️⃣ Finding Nearest Smaller to Right (NSR)
We traverse from right to left.
Maintain a monotonic increasing stack (smallest values at the bottom).
For each bar:
Remove larger elements from the stack.
If the stack is empty, set rightSmall[i] = n - 1 (extend to the last index).
Otherwise, set rightSmall[i] = st.peek() - 1 (stop before the nearest smaller).
Push the current index to the stack.
3️⃣ Calculate Maximum Rectangle Area
Iterate over all bars.
Compute width using:
java
Copy
Edit
width = rightSmall[i] - leftSmall[i] + 1;
Compute area:
java
Copy
Edit
area = heights[i] * width;
Keep track of the maximum area found.
🔹 Example Walkthrough
Input:
java
Copy
Edit
heights = [2, 1, 5, 6, 2, 3]
Left Smallest (leftSmall[i])
Index	Height	leftSmall[i]	Reason
0	2	0	No smaller left
1	1	0	No smaller left
2	5	1 + 1 = 2	Nearest smaller left = 1
3	6	2 + 1 = 3	Nearest smaller left = 2
4	2	1 + 1 = 2	Nearest smaller left = 1
5	3	4 + 1 = 5	Nearest smaller left = 4
Right Smallest (rightSmall[i])
Index	Height	rightSmall[i]	Reason
5	3	5	No smaller right
4	2	5 - 1 = 4	Nearest smaller right = 5
3	6	4 - 1 = 3	Nearest smaller right = 4
2	5	4 - 1 = 3	Nearest smaller right = 4
1	1	5	No smaller right
0	2	1 - 1 = 0	Nearest smaller right = 1
Final Calculation
Index	Height	Width (rightSmall - leftSmall + 1)	Area
0	2	1 - 0 + 1 = 2	2 × 2 = 4
1	1	5 - 0 + 1 = 6	1 × 6 = 6
2	5	3 - 2 + 1 = 2	5 × 2 = 10
3	6	3 - 3 + 1 = 1	6 × 1 = 6
4	2	4 - 2 + 1 = 3	2 × 3 = 6
5	3	5 - 5 + 1 = 1	3 × 1 = 3
👉 Maximum Area = 10 ✅

🔹 Complexity Analysis
Operation	Complexity
Compute leftSmall[]	O(n)
Compute rightSmall[]	O(n)
Compute maxA	O(n)
Total Complexity	O(n)
🚀 Efficient: We process each element at most twice using a stack.

🔹 Final Notes
Monotonic Stack: Helps track nearest smaller elements in O(n).
leftSmall[i] + 1 and rightSmall[i] - 1: Ensure correct rectangle width.
maxA = Math.max(maxA, area): Keeps track of the largest rectangle.

