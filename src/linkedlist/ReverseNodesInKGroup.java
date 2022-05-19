package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k){
        ListNode fh = head;
        List<Integer> list = new ArrayList<>();
        while(fh != null){
            list.clear();
            ListNode fast = fh;
            for(int i=0;i<k && fast!=null;i++){
                list.add(fast.val);
                fast = fast.next;
            }
            if(fast == null) break;

            for(int i=list.size()-1;i>=0;i--){
                fh.val = list.get(i);
                fh= fh.next;
            }
        }
        return head;
    }
}
