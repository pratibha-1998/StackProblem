class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length; 
        int maxArea = 0; // Store maximum rectangle area

        // Iterate over each bar and assume it's the smallest height
        for (int i = 0; i < n; i++) {
            int minHeight = heights[i];  // Start with current bar height

            // Expand to the right to check for possible larger rectangles
            for (int j = i; j < n; j++) {
                minHeight = Math.min(minHeight, heights[j]); // Update min height in this range
                int width = j - i + 1; // Calculate width
                int area = minHeight * width; // Compute area
                maxArea = Math.max(maxArea, area); // Update maximum area
            }
        }

        return maxArea; // Return largest found rectangle
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + sol.largestRectangleArea(heights));
    }
}
