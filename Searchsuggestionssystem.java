// Problem Statement:
// You are given an array of strings products and a string searchWord.
// Design a system that suggests at most three product names from products after each character of searchWord is typed.
// Suggested products should have a common prefix with searchWord.
// If there are more than three products with a common prefix, return the three lexicographically minimum products.
// Return a list of lists of the suggested products after each character of searchWord is typed.

// Approach:
// The problem requires us to suggest up to three lexicographically smallest product names that share a common prefix with the typed part of the search word. This needs to be done for each character typed in the search word.
// A straightforward approach involves sorting the `products` array lexicographically. This will ensure that when we iterate and find matching products, the first three we encounter will be the lexicographically smallest ones.

// For each prefix of `searchWord` (from length 1 to `searchWord.length()`):
// 1. Create the current prefix.
// 2. Iterate through the sorted `products` array.
// 3. For each product, check if it starts with the current prefix.
// 4. If it does, add it to a temporary list of suggestions.
// 5. Stop adding to the temporary list once 3 suggestions have been found.
// 6. Add this temporary list of suggestions to the final result list.

// This approach works because after sorting `products`, any product that matches a prefix will appear before other matching products that are lexicographically larger.

// Time Complexity:
// 1. Sorting `products`: O(N * L log N), where N is the number of products and L is the average length of a product (or maximum length, depending on sort implementation and string comparison).
// 2. Iterating through `searchWord`: `searchWord.length()` times. Let M be the length of `searchWord`.
// 3. Inside the loop, iterating through `products`: At most N products are checked. String.startsWith() takes O(P) time where P is the length of the prefix. In the worst case, this is O(M).
// So, the total time for finding suggestions for each prefix is O(M * N * M) = O(M^2 * N).
// Overall time complexity: O(N * L log N + M^2 * N). Given the constraints, M is up to 1000 and N is up to 1000. M^2 * N can be 10^3 * 10^3 = 10^9, which is too slow.

// Optimization:
// We can optimize the search for products using a two-pointer approach after sorting.
// After sorting `products`, for a given prefix, the matching products will form a contiguous subarray.
// As the prefix grows (i.e., we type more characters of `searchWord`), the range of matching products will shrink or stay the same.
// We can maintain a `left` and `right` pointer that define the current window of products that match the previous prefix.
// When a new character is typed, we adjust `left` and `right` to narrow down the window to only include products that match the new, longer prefix.

// Detailed Optimized Approach:
// 1. Sort the `products` array lexicographically.
// 2. Initialize an empty list of lists `result` to store the suggestions.
// 3. Initialize `left = 0` and `right = products.length - 1`. These pointers define the current search range in `products`.
// 4. Iterate through `searchWord` character by character, from index `i = 0` to `searchWord.length() - 1`.
//    a. Get the current character `c = searchWord.charAt(i)`.
//    b. While `left <= right` and (`products[left].length() <= i` or `products[left].charAt(i) != c`):
//       Increment `left`. (Shrink window from left if product doesn't match or is too short)
//    c. While `left <= right` and (`products[right].length() <= i` or `products[right].charAt(i) != c`):
//       Decrement `right`. (Shrink window from right if product doesn't match or is too short)
//    d. Create a new list `currentSuggestions` for this prefix.
//    e. Iterate from `j = left` to `min(left + 2, right)` (to get at most 3 suggestions):
//       Add `products[j]` to `currentSuggestions`.
//    f. Add `currentSuggestions` to `result`.

// Time Complexity (Optimized):
// 1. Sorting `products`: O(N * L log N).
// 2. Iterating through `searchWord` (length M):
//    In each step, `left` and `right` pointers move. In total, `left` can move at most N times, and `right` can move at most N times across all M iterations of the outer loop.
//    Comparing characters takes O(1).
//    Adding to `currentSuggestions` takes O(1) for each product (at most 3 products).
//    The overall iteration and pointer adjustments: O(M * (right - left + 1)). In the worst case, the total movement of pointers is O(N).
//    So, the dominant part is O(M * N) for the while loops in the worst case where pointers move for every char.
//    However, a better analysis: the total number of character comparisons across all prefixes is bounded. Each product is checked at most `M` times for its characters. So, a total of O(N * M) character comparisons for maintaining the `left` and `right` pointers.
//    Adding products to suggestions takes O(M * 3) = O(M).
// Overall time complexity: O(N * L log N + N * M). Given constraints, N * L log N is dominant if L is large.
// N = 1000, L = 3000, M = 1000.
// N * L log N = 1000 * 3000 * log(1000) approx 3 * 10^6 * 10 = 3 * 10^7.
// N * M = 1000 * 1000 = 10^6.
// This optimized approach is efficient enough.

// Space Complexity:
// O(N * L) for storing the sorted `products` (if a copy is made during sorting, or if the input `products` is not modified and a new sorted array is created).
// O(M * 3 * L) for storing the `result` list of lists. In the worst case, M * 3 products of length L are stored.
// Overall: O(N * L + M * L).

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        java.util.Arrays.sort(products);

        List<List<String>> result = new java.util.ArrayList<>();
        int left = 0;
        int right = products.length - 1;

        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            List<String> currentSuggestions = new java.util.ArrayList<>();
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }
            for (int j = left; j <= right && currentSuggestions.size() < 3; j++) {
                currentSuggestions.add(products[j]);
            }
            result.add(currentSuggestions);
        }

        return result;
    }
}