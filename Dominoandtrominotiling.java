// Problem Statement:
// You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
// Given an integer n, return the number of ways to tile a 2 x n board. Since the answer may be very large, return it modulo 10^9 + 7.
// In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

// Approach:
// This problem can be solved using dynamic programming.
// Let `dp[i]` be the number of ways to tile a 2 x `i` board.
// The tromino shape is an L-shaped tile that covers 3 squares. It can be rotated.
// The domino shape is a 2 x 1 rectangle. It can be placed horizontally (1x2 covering 2 squares) or vertically (2x1 covering 2 squares).

// Let's analyze the ways to tile a 2 x i board based on how we fill the `i`-th column(s).

// Base Cases:
// dp[0] = 1 (There's one way to tile a 2x0 board: do nothing, an empty tiling)
// dp[1] = 1 (A 2x1 board can only be tiled by a vertical 2x1 domino)
// dp[2] = 2 (A 2x2 board can be tiled by two vertical 2x1 dominoes or two horizontal 1x2 dominoes)
// dp[3] = 5 (As given in example)

// For `dp[i]`, we can consider the last placed tile(s):

// 1. Place a vertical 2x1 domino:
//    This leaves a 2 x (i-1) board. So, `dp[i-1]` ways.
//    [ | ]
//    [ | ] ... (i-1) columns ... [2x1 domino]

// 2. Place two horizontal 1x2 dominoes:
//    This fills the last two columns. This leaves a 2 x (i-2) board. So, `dp[i-2]` ways.
//    [--]
//    [--] ... (i-2) columns ... [1x2 domino]
//                               [1x2 domino]

// These two cover standard domino tiling. The trominoes introduce complexity.
// A tromino covers 3 squares. On a 2xN board, a tromino must cover one complete column and one square from an adjacent column, or vice-versa.
// This structure implies that if we use a tromino, it will always interact with a 2x(i-3) board or similar.

// Let's define additional states for "partially filled" columns.
// This is a common technique for tiling problems.
// `dp[i]` = number of ways to tile a 2 x `i` board completely.
// `dp_partial[i]` = number of ways to tile a 2 x `i` board where one cell in the `i`-th column is uncovered, forming a "concave" shape.

// Consider how to build `dp[i]`:
// - From `dp[i-1]`: Add a vertical domino. -> `dp[i-1]` ways.
// - From `dp[i-2]`: Add two horizontal dominoes. -> `dp[i-2]` ways.
// - From `dp_partial[i-1]`: Add an L-tromino that fills the remaining partial column.
//   There are two orientations for this L-tromino, one fills the top, one fills the bottom.
//   So, `2 * dp_partial[i-1]` ways.

// Now consider how to build `dp_partial[i]`:
// `dp_partial[i]` means a 2 x `i` board with one cell in column `i` uncovered.
// For example, if cell (0, i) is uncovered:
// - From `dp[i-1]`: Add an L-tromino that covers (0, i-1), (1, i-1), (0, i). This leaves (1,i) uncovered. This doesn't directly map to `dp_partial[i]` where (0,i) is uncovered.
// Let's refine `dp_partial[i]` as the number of ways to tile a 2 x `i` board such that the `i`-th column has one cell covered and one uncovered (e.g., top cell covered, bottom uncovered, or vice versa).
// Let `dp_full[i]` be the number of ways to tile a 2x`i` board fully.
// Let `dp_one_missing[i]` be the number of ways to tile a 2x`i` board such that `i`-th column has one cell remaining. (e.g., top-left corner of column `i` is empty)

// `dp_full[i]`:
// 1. Add a vertical domino: `dp_full[i-1]`
// 2. Add two horizontal dominoes: `dp_full[i-2]`
// 3. Add an L-tromino filling the gap from `dp_one_missing[i-1]`:
//    This L-tromino completes the `i-1` column and covers one cell in `i`.
//    It means we effectively extend a `dp_one_missing[i-1]` pattern. There are two orientations for this.
//    No, this is incorrect. A tromino covers 3 squares.
//    If we are at `i`, and the `i-1` column had one square missing (e.g., top missing), we can place a tromino that covers `(0,i-1), (1,i-1)` and `(0,i)`. This completes the `i-1` column and leaves `(1,i)` as the missing cell in the current configuration.

// A more standard DP state definition:
// `dp[i]` = number of ways to tile a 2 x `i` board.
// `dp[i] = dp[i-1]` (vertical domino) + `dp[i-2]` (two horizontal dominoes) + new ways with trominoes.

// Consider the last few columns:
// To tile `2 x i`:
// 1. Place a vertical domino: `dp[i-1]` ways.
// 2. Place two horizontal dominoes: `dp[i-2]` ways.
// 3. Place an L-tromino (upright or inverted):
//    This requires filling `i-1` and `i`.
//    If we place an L-tromino, it covers cells in column `i` and `i-1`.
//    Example:
//    [ X ] [ X ]  -> (0, i-1), (1, i-1), (0, i) (if top right is empty in i)
//    [ X ] [   ]
//    Or
//    [ X ] [   ]
//    [ X ] [ X ] -> (0, i-1), (1, i-1), (1, i) (if bottom right is empty in i)

// This suggests we need to track not only fully tiled boards but also boards with "dangling" parts from trominoes.
// Let `f[k]` be the number of ways to tile a `2xk` board completely.
// Let `g[k]` be the number of ways to tile a `2xk` board with the `k`-th column having one square `(0,k)` (or `(1,k)`) empty and the rest `(1,k)` (or `(0,k)`) filled, along with previous `2x(k-1)` fully filled columns.
// This is actually not `g[k]` well-defined.
// Let's refine based on the example in the hints of similar problems.

// `dp[i]` = ways to tile a 2 x `i` board completely.
// `dp[i] = dp[i-1]` (add 2x1 vertical)
//        `+ dp[i-2]` (add two 1x2 horizontal)
//        `+ 2 * ways_to_fill_with_tromino_at_end_leaving_full_board`

// The `2 * ways_to_fill_with_tromino_at_end_leaving_full_board` needs careful definition.
// A tromino always covers 3 cells.
// To use a tromino to complete the `i`-th column, it must interact with `i-1` and `i-2`.
// Consider the state `dp[i]` representing a fully tiled `2 x i` board.
// To get `dp[i]`:
// 1. Put a vertical domino at column `i`: `dp[i-1]` ways.
// 2. Put two horizontal dominoes at columns `i-1` and `i`: `dp[i-2]` ways.
// 3. Put an L-tromino that spans `i-2`, `i-1`, `i`. This means it needs to align with a certain partially filled board.
// This requires a helper DP state.

// Let `f[k]` be the number of ways to tile a `2 x k` rectangle.
// Let `p[k]` be the number of ways to tile a `2 x k` rectangle plus one additional square (forming a specific partial pattern, like top-right square missing).

// `f[k]`:
// 1. Add a vertical domino: `f[k-1]`
// 2. Add two horizontal dominoes: `f[k-2]`
// 3. Add a tromino: This is where `p[k-1]` comes in.
//    If we have `p[k-1]` (a `2 x (k-1)` board with one top cell missing), we can complete it with an L-tromino that covers the missing cell and fills the `k`-th column. There are 2 such configurations for `p[k-1]`: top cell missing or bottom cell missing.
//    No, it's easier to think about what `p[k]` generates.

// Let `dp[i]` be the number of ways to tile a `2 x i` board.
// `dp[i] = dp[i-1]` (vertical domino)
//        `+ dp[i-2]` (two horizontal dominoes)
//        `+ 2 * (dp[i-3] + dp[i-4] + ... + dp[0])` This is confusing.

// Let `full[i]` be the number of ways to tile a `2 x i` board fully.
// Let `part[i]` be the number of ways to tile a `2 x i` board where one cell in column `i` is empty.
// (e.g., `(0,i)` empty, `(1,i)` covered, and previous `i-1` columns fully covered).

// `full[i]` can be formed by:
// 1. Adding a vertical domino to `full[i-1]`.
// 2. Adding two horizontal dominoes to `full[i-2]`.
// 3. Adding a tromino (L-shape) from `part[i-1]` by extending it.
//    If `part[i-1]` has e.g. `(0, i-1)` empty, we can complete it by placing a tromino that covers `(0, i-1)` and `(0, i)` and `(1, i)`.
//    But this is not right. `part[i-1]` means column `i-1` is partially filled.
//    If `part[i-1]` was `(0, i-1)` empty, then we could add a vertical domino in `(1, i-1)` and complete it.

// Let's use the states commonly seen:
// `dp[i][0]` = number of ways to tile a `2 x i` board completely.
// `dp[i][1]` = number of ways to tile a `2 x i` board with the top-right corner of column `i` being empty.
// `dp[i][2]` = number of ways to tile a `2 x i` board with the bottom-right corner of column `i` being empty.
// Due to symmetry, `dp[i][1] == dp[i][2]`. So we can just use `dp[i][1]` for either (and multiply by 2 when needed).

// `dp[i][0]` (fully tiled `2 x i`):
// 1. From `dp[i-1][0]` (vertical domino): `dp[i-1][0]`
// 2. From `dp[i-2][0]` (two horizontal dominoes): `dp[i-2][0]`
// 3. From `dp[i-1][1]` (top-right empty in `i-1`, add tromino to fill it and column `i`): `dp[i-1][1]`
// 4. From `dp[i-1][2]` (bottom-right empty in `i-1`, add tromino to fill it and column `i`): `dp[i-1][2]`
// So, `dp[i][0] = dp[i-1][0] + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]`
// Since `dp[i][1] == dp[i][2]`, we have `dp[i][0] = dp[i-1][0] + dp[i-2][0] + 2 * dp[i-1][1]`

// `dp[i][1]` (top-right of column `i` empty):
// 1. From `dp[i-1][0]` (fully tiled `i-1`, add a tromino covering `(0,i-1), (1,i-1), (1,i)`): `dp[i-1][0]`
// 2. From `dp[i-1][2]` (bottom-right empty in `i-1`, add a horizontal domino to fill `(1,i-1)` and `(1,i)`): `dp[i-1][2]`
// So, `dp[i][1] = dp[i-1][0] + dp[i-1][2]`
// Since `dp[i][1] == dp[i][2]`, we have `dp[i][1] = dp[i-1][0] + dp[i-1][1]`

// Base cases:
// `dp[0][0] = 1` (empty board)
// `dp[0][1] = 0` (cannot have partial column for 0 length)
// `dp[0][2] = 0`

// `dp[1][0] = 1` (vertical domino)
// `dp[1][1] = 1` (2x1 with top-right empty. only one way if bottom is covered by vertical domino)
// `dp[1][2] = 1`

// Let's use the definition:
// `dp[i][0]` = ways to tile a 2x`i` board.
// `dp[i][1]` = ways to tile a 2x`i` board with row 0 of column `i-1` and `i` covered and row 1 of column `i-1` covered but `(1,i)` is empty (L-tromino type)
// This is getting confusing. Let's rely on the common recurrence for this problem.

// It is known that:
// `dp[i]` = ways to tile `2 x i` board completely.
// `dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + dp[i-4] + ... + dp[0])`
// This `2 * (...)` term comes from the trominoes.
// A tromino involves three cells. If we are placing a tromino at column `i`, it must have originated from state `dp[i-3]` (or earlier fully tiled states) and then two L-trominoes are added to fill the gap.
// This simplifies to:
// `dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + simplified_tromino_sum)`
// The `2 * (dp[i-3] + dp[i-4] + ... + dp[0])` term can be simplified.
// Let `S[k] = dp[k-3] + dp[k-4] + ... + dp[0]`.
// Then `S[k] = dp[k-3] + S[k-1]`.
// So, `dp[i] = dp[i-1] + dp[i-2] + 2 * S[i]`.
// But `S[i]` depends on `dp[i-3]`.

// A cleaner recurrence:
// Let `dp[i]` be the number of ways to tile a `2 x i` board.
// `dp[i] = dp[i-1]` (vertical domino)
//        `+ dp[i-2]` (two horizontal dominoes)
//        `+ 2 * dp[i-3]` (ways to add two specific tromino pairs that originated from `dp[i-3]`)
// This recurrence holds for `i >= 3`.

// Let's verify for `n = 3`:
// `dp[0] = 1`
// `dp[1] = 1`
// `dp[2] = 2`
// `dp[3] = dp[2] + dp[1] + 2 * dp[0]`
// `dp[3] = 2 + 1 + 2 * 1 = 5`. This matches the example.

// This simpler recurrence is generally derived by considering the unique ways trominoes are placed.
// A fully tiled 2x`i` board can be obtained from:
// 1. `2x(i-1)` board + 1 vertical domino (`dp[i-1]`)
// 2. `2x(i-2)` board + 2 horizontal dominoes (`dp[i-2]`)
// 3. A `2x(i-3)` board with one 'L' tromino filling `(i-2, i-1)` and `(i-2,i)`.
//    Then you can fill the last column `i` with another 'L' tromino or a domino.
// The key is that `dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + dp[i-4] + ... + dp[0])` can be simplified.
// Consider `dp[i-1] = dp[i-2] + dp[i-3] + 2 * (dp[i-4] + ... + dp[0])`
// Subtract `dp[i-1]` from `dp[i]`:
// `dp[i] - dp[i-1] = (dp[i-1] + dp[i-2] + 2 * S_i) - (dp[i-2] + dp[i-3] + 2 * S_{i-1})`
// where `S_k = dp[k-3] + dp[k-4] + ... + dp[0]`
// `dp[i] - dp[i-1] = dp[i-1] + 2 * (S_i - S_{i-1}) + dp[i-3]` (if there is some cancellation)
// `S_i - S_{i-1} = (dp[i-3] + dp[i-4] + ...) - (dp[i-4] + ...) = dp[i-3]`
// So, `dp[i] - dp[i-1] = dp[i-1] + dp[i-3]`
// Which means `dp[i] = 2 * dp[i-1] + dp[i-3]` for `i >= 3`.

// Let's re-verify the recurrence `dp[i] = 2 * dp[i-1] + dp[i-3]`.
// Base cases:
// `dp[0] = 1` (empty board)
// `dp[1] = 1` (1 vertical domino)
// `dp[2] = 2` (2 vertical dominoes or 2 horizontal dominoes)
// Now for `n=3`:
// `dp[3] = 2 * dp[2] + dp[0] = 2 * 2 + 1 = 4 + 1 = 5`. Matches example.

// This simpler recurrence `dp[i] = 2 * dp[i-1] + dp[i-3]` for `i >= 3` is correct for this problem.
// We need to perform modulo `10^9 + 7` at each step to prevent overflow.

// MOD = 10^9 + 7

// Time Complexity:
// O(N) because we iterate from 3 to N and perform constant time operations for each `i`.

// Space Complexity:
// O(N) for the DP array.
// Can be optimized to O(1) as `dp[i]` only depends on `dp[i-1]`, `dp[i-2]`, and `dp[i-3]`.

class Solution {
    public int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 5;
        }

        long MOD = 1_000_000_007;
        long[] dp = new long[n + 1];

        dp[0] = 1;
        dp[1] = 1; 
        dp[2] = 2; 
        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MOD;
        }

        return (int) dp[n];
    }
}