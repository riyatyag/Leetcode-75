    /**
     * Problem: You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
     *
     * Approach:
     * 1. Iterate through the flowerbed array.
     * 2. For each empty plot (represented by 0), check if its adjacent plots are also empty.
     * 3. The adjacent plots are the one to its left and the one to its right. Handle the edge cases where the empty plot is at the beginning or the end of the flowerbed.
     * 4. If an empty plot has no adjacent flowers, plant a flower in it (by changing the 0 to 1) and decrement the count of flowers to be planted (n).
     * 5. Continue iterating until either all the required flowers are planted (n becomes 0) or the end of the flowerbed is reached.
     * 6. Finally, return true if n is 0 (all flowers could be planted), and false otherwise.
     *
     * Time Complexity: O(m), where m is the length of the flowerbed array, as we iterate through the array once.
     * Space Complexity: O(1), as we are modifying the input array in-place and using a constant amount of extra space.
     *
     * Optimal Solution: The described approach is optimal as it iterates through the flowerbed once to determine the maximum number of flowers that can be planted.
     */
    class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 0) {
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                boolean rightEmpty = (i == m - 1) || (flowerbed[i + 1] == 0);

                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }
}