    /**
     * Problem: There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
     * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
     * Note that multiple kids can have the greatest number of candies.
     *
     * Approach:
     * 1. Find the maximum number of candies among all the kids in the input array `candies`.
     * 2. Create a new boolean list `result` of the same length as the `candies` array.
     * 3. Iterate through the `candies` array. For each kid's candy count:
     * a. Add `extraCandies` to their current candy count.
     * b. Compare this new total with the maximum number of candies found in step 1.
     * c. If the new total is greater than or equal to the maximum, add `true` to the `result` list.
     * d. Otherwise, add `false` to the `result` list.
     * 4. Return the `result` list.
     *
     * Time Complexity: O(n), where n is the number of kids (length of the `candies` array), as we iterate through the array twice (once to find the maximum and once to build the result list).
     * Space Complexity: O(n), as we create a new boolean list of size n to store the result.
     *
     * Optimal Solution: The described approach is optimal in terms of time complexity as we need to examine each kid's candy count at least once. The space complexity of O(n) for the result list is necessary to store the output.
     */

     class Solution {
      public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        for (int candyCount : candies) {
            maxCandies = Math.max(maxCandies, candyCount);
        }

        List<Boolean> result = new ArrayList<>();
        for (int candyCount : candies) {
            result.add(candyCount + extraCandies >= maxCandies);
        }
       return result;
    }
}