
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

    public Solution() {

    }

    public static void main(String []argvs) {

    }
}
