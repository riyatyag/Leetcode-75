// Problem Statement:
// Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
// The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
//
// For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
// Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
//
// Implement the StockSpanner class:
// StockSpanner() Initializes the object of the class.
// int next(int price) Returns the span of the stock's price given that today's price is price.

// Approach:
// This problem can be efficiently solved using a monotonic stack. We need to find the number of consecutive days
// (including today) for which the stock price was less than or equal to today's price. This is equivalent to
// finding the first previous day with a price strictly greater than today's price. The span will be the number
// of days from that previous day (exclusive) up to today (inclusive).
//
// We can use a stack to store pairs of (price, span) or (price, index). A more direct approach is to store
// (price, count), where count is the accumulated span for that price if it were the current price.
// When a new price comes in:
// 1. Initialize its span to 1.
// 2. While the stack is not empty and the top element's price is less than or equal to the current price,
//    pop elements from the stack and add their accumulated span to the current price's span. This is because
//    these popped elements are "covered" by the current price, and their span contributes to the current price's span.
// 3. Push the current (price, calculated_span) onto the stack.
// 4. Return the calculated_span.
//
// This way, the stack always maintains a decreasing order of prices. When a new price arrives, we pop all
// elements that are less than or equal to it. The span of the new price will be 1 (for itself) plus the
// spans of all the elements popped. The element at the top of the stack after popping will be the first
// element to the left that is strictly greater than the current price (or the stack will be empty).

// Time Complexity:
// The time complexity for each `next` call is amortized O(1). Although in the worst case, we might iterate
// through all previous elements on the stack, each element is pushed onto the stack exactly once and popped
// from the stack at most once. Therefore, over a sequence of `N` calls to `next`, the total number of push
// and pop operations will be proportional to `N`. So, the average time complexity per call is O(1).

// Space Complexity:
// The space complexity is O(N) in the worst case, where `N` is the number of calls to `next`. This happens
// when the prices are in strictly decreasing order (e.g., [100, 90, 80, ...]), in which case all prices
// will remain on the stack.
import java.util.Stack;

class StockSpanner {
    private Stack<int[]> stack; 

    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }
}
