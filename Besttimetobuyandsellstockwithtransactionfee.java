// Problem Statement:
// You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
// Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again). The transaction fee is only charged once for each stock purchase and sale.

// Approach:
// This is a dynamic programming problem, similar to other "Best Time to Buy and Sell Stock" problems, but with a transaction fee.
// We can solve this by keeping track of two states at each day:
// 1. `buy`: The maximum profit we can have if we are currently holding a stock (or equivalently, the minimum cost to hold a stock).
// 2. `sell`: The maximum profit we can have if we are currently not holding a stock.

// Let `buy[i]` be the maximum profit ending on day `i` if we hold a stock.
// Let `sell[i]` be the maximum profit ending on day `i` if we do not hold a stock.

// On day `i`:
// To be in `buy` state (holding a stock):
//   a. We bought the stock on day `i`: This means our profit would be `sell[i-1] - prices[i]`. (We had some profit from previous transactions, and now we buy a new stock).
//   b. We were already holding a stock on day `i-1`: Our profit remains `buy[i-1]`.
//   So, `buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i])`.

// To be in `sell` state (not holding a stock):
//   a. We sold the stock on day `i`: This means our profit would be `buy[i-1] + prices[i] - fee`. (We had profit from holding, now we sell and pay the fee).
//   b. We were already not holding a stock on day `i-1`: Our profit remains `sell[i-1]`.
//   So, `sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee)`.

// Base Cases:
// On day 0:
// `buy[0] = -prices[0]` (We buy the stock, so our initial profit is negative the price).
// `sell[0] = 0` (We have no stock, no profit).

// The final answer will be `sell[n-1]`, as we want to maximize profit by selling the stock.

// Space Optimization:
// Notice that `buy[i]` and `sell[i]` only depend on `buy[i-1]` and `sell[i-1]`.
// This means we can optimize space from O(N) to O(1) by just keeping track of the previous `buy` and `sell` values.

// Let `prevBuy` represent `buy[i-1]` and `prevSell` represent `sell[i-1]`.
// On current day `i`:
// `currentBuy = Math.max(prevBuy, prevSell - prices[i])`
// `currentSell = Math.max(prevSell, prevBuy + prices[i] - fee)`
// Then update `prevBuy = currentBuy` and `prevSell = currentSell` for the next iteration.

// Initial values for the optimized approach:
// `prevBuy = -prices[0]`
// `prevSell = 0`

// Time Complexity:
// O(N), where N is the number of days (length of `prices` array), because we iterate through the prices array once.

// Space Complexity:
// O(1), as we only use a few constant variables to store the states.

class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < prices.length; i++) {
            int prevBuy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, prevBuy + prices[i] - fee);
        }
        return sell;
    }
}