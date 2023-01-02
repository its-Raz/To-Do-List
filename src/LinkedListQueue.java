import java.lang.reflect.Method;

public class LinkedListQueue<T extends Cloneable> implements Queue<T> {
    Node<T> first;
    Node<T> last;
    public void enqueue(T element)
    {
        Node<T> newNode = new Node<T>(element);
        if(isEmpty())
        {
            this.first = newNode;
            this.last = first;
        }
        else
        {
            this.last.setNextNode(newNode);
            this.last=newNode;
        }
    }
    public void setFirst(Node<T> node){this.first=node;}
    public void setLast(Node<T> node){this.last=node;}


    public T dequeue()
    {
       
    }
    public T peek()
    {
        if(this.first!=null){return this.first.getData();}
        else{throw new EmptyQueueException();}
    }

    public int size()
    {
      
    }
    public boolean isEmpty(){}


    @Override
    public LinkedListQueue<T> clone() {
        try{
            int listSize = this.size();
            int counter=1;
            LinkedListQueue<T> newList = new LinkedListQueue<T>();
            if(this.first!=null) {
                T clonedData;
                Node<T> previousClonedNode = null;
                for (T nodeData : this) {
                    Class<T> cls = (Class<T>) nodeData.getClass(); // Get the Class object for the parameter type T
                    Method cloneMethod = cls.getMethod("clone"); // Get the Method object for the clone() method
                    clonedData = (T) cloneMethod.invoke(nodeData);// Invoke the clone() method on an instance of the data field
                    Node<T> newNode = new Node<T>(clonedData);
                    if (counter == 1) {
                        newList.setFirst(newNode);
                        if (listSize == 1) {
                            newList.setLast(newNode);
                        } else {
                            previousClonedNode = newNode;
                        }
                    } else {
                        previousClonedNode.setNextNode(newNode); //Set cloned Node to the previous node
                        previousClonedNode = newNode;
                        if (counter == listSize) {
                            newList.setLast(newNode);
                        }
                    }
                    counter++;
                }
            }
            return newList;
        }
        catch(Exception exp){
            return null;
        }
    }


    private class LinkedListQueueIterator implements Iterator<T> {
      

    }

    @Override
    public Iterator<T> iterator() {
        
    }
}

//    Integer num = new Integer(2);
//        num.clone();
//                Class myclass = Integer.class.getClass();
//        Method myclone = myclass.getMethod("clone");
//        myclone.invoke(num);
