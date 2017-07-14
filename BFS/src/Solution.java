import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Created by linbin on 7/13/2017.
 */

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}

 class GraphNode {
   public int key;
   public List<GraphNode> neighbors;
   public GraphNode(int key) {
     this.key = key;
     this.neighbors = new ArrayList<GraphNode>();
   }
 }

public class Solution {
    private final int white = 0;
    private final int black = 1;

    /**
     * Check if a given binary tree is completed. A complete binary tree is one in which every level of
     * the binary tree is completely filled except possibly the last level. Furthermore, all nodes are
     * as far left as possible.
     * @param root
     * @return
     */
    public boolean isCompleted(TreeNode root) {
        // Write your solution here.
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; ++i){
                TreeNode temp = queue.element();
                queue.remove();

                if (temp.left == null && temp.right != null) {
                    return false;
                }

                if (temp.left != null) {
                    queue.add(temp.left);
                }

                if (temp.right != null) {
                    queue.add(temp.right);
                } else {
                    int count = i;
                    Iterator<TreeNode> it = queue.iterator();
                    while (count <= size && it.hasNext()) {
                        TreeNode tmp = it.next();
                        if (tmp.left != null || tmp.right != null)
                            return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into
     * two groups such that no nodes have direct edges to other nodes in the same group.
     * Assumptions: the graph is represented by a list of nodes, and the list of nodes is not null.
     * @param graph
     * @return
     */
    public boolean isBipartite(List<GraphNode> graph) {
        // Use white and black to denote two groups
        // The map maintains each node which group is assigned
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();

        for (GraphNode node : graph) {
            if (!bfs(node, visited)) {
                return false;
            }
        }

        return true;
    }

    private boolean bfs(GraphNode node, HashMap<GraphNode, Integer> visited) {
        // If this node has been visited, return immediately
        if (visited.containsKey(node)) {
            return true;
        }

        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);

        // Start the breadth first search from the node. Since the node has not been visited,
        // any group can be assigned to it, for example, white.
        visited.put(node, white);
        while (!queue.isEmpty()) {
            GraphNode temp = queue.poll();

            // The group assigned for current node
            int group = visited.get(temp);
            // The group assigned for the neighbors of current node
            int newGroup = group == white ? black : white;

            for (GraphNode neighbor : temp.neighbors) {
                // If the node has not been visited yet, just put it into the queue
                // and choose the correct group
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, newGroup);
                    queue.offer(neighbor);
                } else if (visited.get(neighbor) != newGroup) {
                    // If the neighbor has been visited and the group doesn't match the desired one, return false
                    return false;
                }
                // If the neighbor has been visited and the group matches the desired one, we don't have to do anything.
            }
        }

        return true;
    }
}
