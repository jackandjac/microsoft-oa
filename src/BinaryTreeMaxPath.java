public class BinaryTreeMaxPath {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode node){
        dfs(node);
        return max;
    }

    private int dfs(TreeNode node){
        if(node == null) return 0;
        int val = node.val;
        int left = Math.max(dfs(node.left),0);
        int right = Math.max(dfs(node.right),0);
        max = Math.max(val + left + right ,max);
        return Math.max(left, right) + val;
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
          this.right = right;
      }
  }
