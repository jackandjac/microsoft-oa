import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private ListNode head = new ListNode();
    private ListNode tail  = new ListNode();
    Map<Integer, ListNode> cache = new HashMap<>();
    int size =0;
    int capacity;
    public LRUCache(int capacity){
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    private void add(ListNode node){
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    private void remove(ListNode node){
        ListNode nex = node.next;
        ListNode pre = node.pre;
        pre.next = nex;
        nex.pre = pre;

    }
    private void evictNode(){
        ListNode node = tail.pre;
        this.remove(node);
        cache.remove(node.key,node);
    }
    private void moveHead(ListNode node){
        this.remove(node);
        this.add(node);
    }
}

class ListNode{
    public int key;
    public int val;
    public ListNode pre;
    public ListNode next;
    public ListNode(){}
    public ListNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}
