import java.io.Serializable;
import java.util.*;
                                
public class DSALinkedList implements  Iterable<Object>,Serializable
{
    private class DSAListNode implements Serializable
{
    private Object _value;
    private DSAListNode _next;
    private DSAListNode _previous;

    private DSAListNode(Object pValue) 
    {
        _value=pValue;
        _next=null;
        _previous=null;
    }

    public Object getValue()
    {
        return _value;
    }

    public DSAListNode getNext()
    {
        return _next;
    }

    public DSAListNode getPrevious()
    {
        return _previous;
    }

    public void setValue(Object pValue)
    {
        _value=pValue;
    }

    public void setNext(DSAListNode pNext)
    {
        _next=pNext;
    }

    public void setPrevious(DSAListNode pPrevious)
    {
        _previous=pPrevious;
    }
}

    private DSAListNode head,tail=null;
    private int count=0;

    public boolean isEmpty()
    {
        boolean empty=false;

        if(head==null || tail==null)
            empty=true;

        return empty;
    }
    public void insertFirst(Object pNewObject)
    {
        DSAListNode newNode=new DSAListNode(pNewObject);

        if(head==null)
        {
            head=tail=newNode;
            head.setPrevious(null);
            head.setNext(tail);
            tail.setPrevious(head);
            tail.setNext(null);
            count++;
        }
        else 
        {
            //Sets the current value in head as the next value of newNode
            newNode.setNext(head);
            head.setPrevious(newNode);
            //newNode becomes the new head;
            head=newNode;
            count++;
        }
    }

    public void insertLast(Object pNewObject)
    {
        DSAListNode newNode=new DSAListNode(pNewObject);
        newNode.setNext(null);
        if(head==null)
        {
            head=tail=newNode;
            head.setPrevious(null);
            head.setNext(tail);
            tail.setPrevious(head);
            tail.setNext(null);
            count++;

        }
        else
        {
             //need to traverse to the end of the linked list to find the tail and add the new item there.
        tail=head;

        while(tail.getNext()!=null)
        {
            tail=tail.getNext();
        }
        
        newNode.setPrevious(tail);
        tail.setNext(newNode);
        tail=newNode;
        tail.setNext(null);
        count++;
        }
       
    }

    public Object peekFirst()
    {
        Object toReturn=null;

        if(isEmpty())
            System.out.println("Linked list in empty");
        else
            toReturn=head.getValue();

        return toReturn;
    }

    public Object peekLast()
    {
        Object toReturn=null;
        
        if(isEmpty())
            System.out.println("Linked list is empty");
        else
            toReturn=tail.getValue();

        return toReturn;
    }

    public Object removeFirst() 
    {
        DSAListNode nodeToReturn;

        if(head==null)
            throw new IllegalStateException("Linked List is empty");
        else if(head.getNext()==null)
        {
            nodeToReturn=head;
            head=null;
            count--;
           
        }
        else 
        {
            nodeToReturn=head;

            head=head.getNext();
            head.setPrevious(null);
            count--;
        }
        return nodeToReturn.getValue();
    }

    public Object removeLast()
    {
        DSAListNode nodeToReturn;

        if(head==null && tail==null)
            throw new IllegalStateException("LinkedList is empty");
        else if(tail.getPrevious()==null)
        {
            nodeToReturn=tail;

            tail=null;
            head=null;
            count--;
        }
        else
        {
            nodeToReturn=tail;

            tail=nodeToReturn.getPrevious();
            tail.setNext(null);
            count--;
        }

        return nodeToReturn.getValue();
    }

    public void delete(Object pObject)
    {
        DSAListNode currentNode=head;

        if(head==(pObject))
        {
            removeFirst();
        }
        else if(tail==(pObject))
        {
            removeLast();
        }
        else 
        {

         while(currentNode!=null)
        {
            DSAListNode previousNode=currentNode.getPrevious();
            DSAListNode nextNode=currentNode.getNext();

            if(currentNode.getValue()==(pObject))
            {
                  
                    previousNode.setNext(nextNode);
                    nextNode.setPrevious(previousNode);
            }
    
            currentNode=nextNode;
        }
        }

        
    }

    public int getCount()
    {
        return count;
    }
    
    /************************************************************************************************
    PURPOSE: To print all items in the linked list from front to end
    *************************************************************************************************/
    public void printListForwards()
    {
        DSAListNode currentNode=head;

        while(currentNode!=null)
        {
            System.out.println("List Node Value: "+currentNode.getValue().toString());
            currentNode=currentNode.getNext();
        }
    }

    /************************************************************************************************
    PURPOSE: To print all items in the linked list from end to front
    *************************************************************************************************/
    public void printListBackwards()
    {
        DSAListNode currentNode=tail;

        while(currentNode!=null)
        {
            System.out.println("List Node Value: "+currentNode.getValue().toString());
            currentNode=currentNode.getPrevious();
        }
    }

    public boolean contains(Object inObject)
    {
        boolean returnVal=false;
        DSAListNode current=head;

        while(current.getNext()!=null)
        {
            if(current.getValue().equals(inObject))
                returnVal=true;

            current=current.getNext();
        }

        return returnVal;
    }

    public Iterator<Object> iterator()
    {
        return new DSALinkedListIterator(this);
    }

    private class DSALinkedListIterator implements Iterator<Object>
    {
        public DSAListNode _iterNext;

        public DSALinkedListIterator(DSALinkedList pLinkedList) 
        {
            _iterNext=pLinkedList.head;
        }

        public boolean hasNext()
        {
            return _iterNext!=null;
            
        }

        public Object next()
        {
            Object nextValue=null;

            if(_iterNext==null)
                nextValue=null;
            else
            {
               nextValue=_iterNext.getValue();
               _iterNext=_iterNext.getNext();
            }
            
            return nextValue;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }

    

       


    }


}
