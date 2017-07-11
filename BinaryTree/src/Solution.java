
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by linbin on 7/7/2017.
 */

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}

public class Solution {
    /**
     *  Iterative, in-order traversal of a given binary tree, return the list
     *  of the keys of nodes in the tree as it is in-order traversed.
     * @param root
     * @return
     */
    public List<Integer> inOrder(TreeNode root) {
        if (root == null)
            return null;

        List<Integer> res = new ArrayList<>();

        TreeNode curr = root.left;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.key);

            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = null;
            }
        }

        return res;
    }

    /**
     * Iterative, pre-order traversal of a given binary tree, return the list
     *  of the keys of nodes in the tree as it is pre-order traversed.
     * @param root
     * @return List<Integer>
     */
    public List<Integer> preOrder(TreeNode root) {
        if (root == null)
            return null;

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode temp = stack.pop();
            res.add(temp.key);

            if (temp.right != null) {
                stack.push(temp.right);
            }

            if (temp.left != null) {
                stack.push(temp.left);
            }
        }

        return res;
    }

    /**
     * Iterative, post-order traversal of a given binary tree, return the list
     *  of the keys of nodes in the tree as it is post-order traversed.
     * @param root
     * @return
     */
    public List<Integer> postOrder(TreeNode root) {
        if (root == null)
            return null;

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root, lastVisited = null;

        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.peek();
            if (curr.right == null || curr.right == lastVisited) {
                res.add(curr.key);
                stack.pop();
                lastVisited = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }

        return res;
    }

    /**
     * Given a binary tree, determine if it's height balanced.
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;

        if (root.left == null && root.right != null &&
                (root.right.left != null || root.right.right != null))
            return false;

        if (root.right == null && root.left != null &&
                (root.left.left != null || root.left.right != null))
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * Check if a given binary tree is symmetric
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;

        if ((root.left == null && root.right != null) ||
                (root.left != null && root.right == null))
            return false;

        if (root.left.key != root.right.key)
            return false;

        return isIdentical(root.left.right, root.right.left) &&
            isIdentical(root.left.left, root.right.right);
    }

    private boolean isIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null)
            return true;

        if (one == null || two == null)
            return false;

        if (one.key != two.key)
            return false;

        return isIdentical(one.left, two.right) && isIdentical(one.right, two.left);
    }

    /**
     * Determine whether two given binary trees are identical assuming any number of
     * "tweak" is allowed. A tweak is defined as a swap of the children of a node in the tree.
     * @param one
     * @param two
     * @return
     */
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null)
            return true;

        if (one == null && two != null)
            return false;

        if (one != null && two == null)
            return false;

        if (one.key != two.key)
            return false;

        return (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)) ||
                (isTweakedIdentical(one.right, two.left) && isTweakedIdentical(one.left, two.right));
    }

    /**
     * Determine a given binary tree is a binary search tree
     * @param root
     * @return
     */
    public boolean isBST(TreeNode root) {
        if (root == null)
            return true;

        return isValid(root.left, null, null);
    }

    private boolean isValid(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        if (root == null)
            return true;

        if ((minNode != null && minNode.key >= root.key) ||
                maxNode != null && maxNode.key <= root.key)
            return false;

        return isValid(root.left, minNode, root) && isValid(root.right, root, maxNode);
    }

    /**
     * Get the list of keys in a given binary search tree in a given range[min, max] in ascending order,
     * both min and max are inclusive.
     * @param root
     * @param min
     * @param max
     * @return
     */
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> res = new ArrayList<Integer>();

        if (root == null)
            return  res;

        if (root.key < min)
            res.addAll(getRange(root.right, min, max));

        if (root.key > max)
            res.addAll(getRange(root.left, min, max));

        if (root.key >= min && root.key <= max) {
            res.addAll(getRange(root.left, min, max));
            res.add(root.key);
            res.addAll(getRange(root.right, min, max));
        }

        return res;
    }

    public Solution() {

    }

    public static void main(String []argvs) {

    }
}
