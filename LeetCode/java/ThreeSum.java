import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static void main(String[] args) {
        List<List<Integer>> res = new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(res);
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int target = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0, n = nums.length; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] > target) break;
            if (nums[i] + nums[n - 2] + nums[n - 1] < target) continue;

            int l = i + 1, h = nums.length - 1, t = target - nums[i];

            while (l < h) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                if (nums[l] + nums[h] > t) {
                    h--;
                } else if (nums[l] + nums[h] < t) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l++], nums[h--]));
                }
            }
        }
        return res;
    }
}
