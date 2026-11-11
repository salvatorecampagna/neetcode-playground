# Best Time to Buy and Sell Stock

**Topic:** Sliding Window · **Difficulty:** Easy

## Summary

Given the price of an asset on each day, return the largest profit from
buying on one day and selling on a later day, or 0 if no such pair of days
makes a profit.

## Approach

- **`maxProfitSinglePass`:** scan the prices once, keeping the lowest price
  seen so far, `minSoFar`. On each day, update `minSoFar` and compute the
  profit of selling that day at that price; keep the largest profit.
  `maxProfit` delegates to this version.
- **`maxProfitNestedLoop`:** for every buy day `i` and every later sell day
  `j > i`, compute `prices[j] - prices[i]` and keep the largest, starting
  from 0.

## Correctness

- **`maxProfitSinglePass`:** after the update on day `i`, `minSoFar` is the
  lowest price from day 0 to day `i` inclusive. For a fixed sell day `i`, the
  best buy is the cheapest day up to `i`, so `prices[i] - minSoFar` is the
  best profit when selling on day `i`. The best trade sells on some day, so
  the maximum over all days includes it. When day `i` sets a new minimum, the
  profit that day is 0, which never beats a real profit, and the result starts
  at 0, so no transaction is the answer when prices never rise.
- **`maxProfitNestedLoop`:** it checks every pair of days with the sale after
  the purchase, so it sees the best one; starting from 0 covers the case of no
  transaction.

## Complexity

`n` is the number of days.

| Method | Time | Extra space | Why |
|---|---|---|---|
| `maxProfitSinglePass` | O(n) | O(1) | One pass with a constant amount of work per day. |
| `maxProfitNestedLoop` | O(n²) | O(1) | n(n - 1)/2 pairs of days. |

<!--
## Follow-ups

Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
