package linkedlist;

public class InsertIntoSortedCircularLinkedList {

    public Node insert(Node head, int insertVal){
        if(head == null){
            Node node= new Node(insertVal);
            node.next = node;
            return node;
        }

        Node prev = head;
        Node cur = head.next;
        boolean insert = false;
        do{
            if(prev.val >= insertVal && insertVal <= cur.val ){
                insert = true;
            }else if(prev.val > cur.val){
                if(insertVal >= prev.val || insertVal<= cur.val){
                    insert = true;
                }
                if(insert){
                    prev.next = new Node(insertVal, cur);
                    return head;
                }

                prev = cur;
                cur = cur.next;
            }

        }while(prev != head );
        prev.next = new Node(insertVal,cur);
        return head;
    }
}
