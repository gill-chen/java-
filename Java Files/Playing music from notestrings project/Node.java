public class Node
{
    String data;
    Node next;
    //added previous as an attribute, to reference previous node. 
    Node previous; 
    
    public Node(String input_data)
    {
        this.data = input_data;
        this.next = null;
        this.previous = null; 
    }
    //if data, next node and previous node are specified in creation of this new Node. 
    public Node (String input_data, Node next, Node previous)
    {
      this.data = input_data;
      this.next = next;
      this.previous = previous;
    }
    
    public String toString ()
    {
      return data + ""; 
    }
}
