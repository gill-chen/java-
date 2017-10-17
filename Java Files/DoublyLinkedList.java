public class DoublyLinkedList{
    Node start;
    Node end; 
    int length;
    
    public DoublyLinkedList()
    {
       this.start = null;
       this.end = null; 
       this.length = 0;
    }
    //checks if the list is empty. 
    public boolean isEmpty ()
    {
      return (this.start == null); 
    }

    // find if the value exists in the list
    // if it does, return the first node that matches
    // return null otherwise
    public Node find( String value )
    {   
        Node current = start; 
        //iterates until the current Node has the same string value as the given string input. 
        while (current.data != value)
        {
         current = current.next; 
         if (current == null)
         {
           return null; 
         }
        }
        return current; 
    }
    
    // insert a new node at the beginning of the list
    public void insertStart (String value)
    {
      //creating a new Node with the given string value.
      Node newNode = new Node(value); 
      //if the list is empty, it sets the new Node as the beginning and the end. 
      if (isEmpty())
      {
        end = newNode;
      }
      //since the list is not empty, it changes the reference of the original start Node, so that the new Node
      //is now the previous of this original, and the new Node has the original Node referenced as its next Node. 
      else 
      {
        newNode.next = start;
        start.previous = newNode;      
      }
      start = newNode;
      //adds to the length of the list. 
      length ++; 
    }
    
    // insert a new node at the end of the list
    public void insertEnd( String value )
    {
      //creating a new Node with the given string value. 
      Node newNode = new Node(value);
      //if the list is empty, it sets the new Node as the beginning and the end. 
      if (isEmpty())
      {
        start = newNode;
      }
      //since the list is not empty, it changes the reference of the original end Node, so that the new Node 
      //is now the next reference for the original end Node, and the new Node has the original Node referenced
      //as its previous Node. 
      else 
      {
        end.next = newNode;
        newNode.previous = end; 
      }
      end = newNode; 
      length ++; 
    }

    // remove all the occurences of the value in the list
    public void remove(String value)
    {
      Node thisNode = start;
       // this while loop iterates through the list 
      while (thisNode != null)
      {
        //if the start Node has the value to be removed, this if statement handles it by setting the start Node's
        //next as the new start Node. 
        if (start.data == value)
        {
          start = start.next; 
          start.previous = null; 
          length --; 
        }
        //same with this else if statement, except vice versa for end Nodes. 
        else if (end.data == value)
        {
          end = end.previous;
          end.next = null; 
          length --; 
        }
        //if the Node needed to be removed is not a start or end Node, it changes the reference points
        //of its neighbouring Nodes (previous and next) respectively so they will be referenced to each other. 
        //This 'removes' the Node since it is no longer referenced in the list. 
        else if (thisNode.data == value)
        {
          thisNode.previous.next = thisNode.next; 
          thisNode.next.previous = thisNode.previous; 
          length --; 
        }
        thisNode = thisNode.next;
      }
    }

    // remove from the list the Node at the position given
    // by the value of index.
    public void removeAtIndex(int index)
    {
      //if the start Node needs to be removed, it sets the next Node as the new start Node. 
      if (index == 0)
      {
        start = start.next; 
        start.previous = null; 
      }
      //if the end Node needs to be removed, it sets the previous Node as the new end Node. 
      else if (index == this.length-1)
      {
        end = end.previous;
        end.next = null; 
      }
      //if index does not refer to the start or end node, it goes through a for loop to the specific index
      //given, and at that index, takes the Node's neighbours (next and previous), and references their previous/
      //next attributes to each other so that the Node is 'removed'. 
      else 
      {
        Node current = start;  
        for (int x = 0; x < index ; x++)
        {
          current = current.next; 
        }
        current.previous.next = current.next;
        current.next.previous = current.previous; 
      }
      length --; 
    }
   
    // print the string in reverse order
    public String toStringReverse()
    {
        String result = "";
        Node pointer = this.end;
        while (pointer != null)
        {
          result += pointer.toString();
          pointer = pointer.previous; 
        }
        return result;
    }

    // print the string
    public String toString()
    {
        String str = "";
        Node pointer = this.start;
        while ( pointer != null ) {
            str += pointer.toString();
            pointer = pointer.next;
        }        
        return str;
    }
    
    public static void main (String[] args){
        DoublyLinkedList list = new DoublyLinkedList();
        String once = "And you may find yourself ";
        
        list.insertStart("I am helpless. ");
        list.insertEnd(once);
        list.insertEnd("I do not believe ");
        list.insertEnd("Hello!. ");
        list.insertEnd("There is hope. ");

        
        System.out.println(list);
       
        Node n = list.find(once);
        n.data += " in a shotgun shack.";
        
        System.out.println(list);

        list.remove(once);
        list.removeAtIndex(4);

     
        System.out.println(list);
        
        
        System.out.println(list.toStringReverse());
    }
}
