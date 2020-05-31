import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            //bufferedWriter.write(String.valueOf(node.data));
            System.out.println(node.data);
            node = node.next;

            /*if (node != null) {
                bufferedWriter.write(sep);
            }*/
        }
    }
}



class Result {

    /*
     * Complete the 'mergeInBetween' function below.
     *
     * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
     * The function accepts following parameters:
     *  1. INTEGER_SINGLY_LINKED_LIST list1
     *  2. INTEGER_SINGLY_LINKED_LIST list2
     *  3. INTEGER a
     *  4. INTEGER b
     */

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */

    public static SinglyLinkedListNode mergeInBetween(SinglyLinkedListNode list1, SinglyLinkedListNode list2, int a, int b) {
        // Write your code here
        SinglyLinkedListNode node =list1;
        SinglyLinkedListNode tmp1 = null;
        SinglyLinkedListNode tmp2 = null;
        int fc =1;
        while(node!=null && node.next !=null && fc!=a){
            node =node.next;
            tmp1 = node;
            fc++;
        }

        int sc =0;
        node =list1;
        while(node !=null && sc<b){
            node =node.next;
            tmp2 =node;
            sc++;
        }

        if(tmp2 != null) {
            list2.next = tmp2;
        }
        if(tmp1 !=null){
            tmp1.next =list1;
        }else{
            return list2;
        }

        return list1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
       // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        SinglyLinkedList list1 = new SinglyLinkedList();

        /*int list1Count = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, list1Count).forEach(i -> {
            try {
                int list1Item = Integer.parseInt(bufferedReader.readLine().trim());

                list1.insertNode(list1Item);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/

        list1.insertNode(1);
        list1.insertNode(2);

        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.insertNode(3);
        list2.insertNode(4);
        list2.insertNode(5);

/*

        int list2Count = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, list2Count).forEach(i -> {
            try {
                int list2Item = Integer.parseInt(bufferedReader.readLine().trim());

                list2.insertNode(list2Item);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
*/

        int a = 1;//Integer.parseInt(bufferedReader.readLine().trim());

        int b = 2;//Integer.parseInt(bufferedReader.readLine().trim());

        SinglyLinkedListNode result = Result.mergeInBetween(list1.head, list2.head, a, b);

        SinglyLinkedListPrintHelper.printList(result, "\n", null);
       /* bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
