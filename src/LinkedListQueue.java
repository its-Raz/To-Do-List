import java.lang.reflect.Method;
import java.util.Iterator;

public class LinkedListQueue<T extends Cloneable> implements Queue<T> {
    Node<T> first;
    Node<T> last;
    /**
     * CONSTRUCTOR
     */
    public LinkedListQueue(){
        this.first = null;
        this.last = null;
    }

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
/**
 * removes the head and returns the data of the head
 * @return the data of the head
 */
    public T dequeue()
    {
        if(isEmpty()){
            throw new EmptyQueueException();
        }
       T data = first.getData();
        if(size() == 1){
            first = null;
            last = null;
        }
        else {
            first = first.getNextNode();
        }
       return data;
    }
    public T peek()
    {
        if(this.first!=null){return this.first.getData();}
        else{throw new EmptyQueueException();}
    }
    /**
     * checks how many node are there
     * @return the number of nodes
     */
    public int size()
    {
      if(isEmpty()){return 0;}
      int sumNodes = 0;
      if(isEmpty()){
          return sumNodes;
      }
      for (T node: this){
         sumNodes ++;
      }
      return sumNodes;
    }
    /**
     * checks if the queue is empty
     * @return true if empty, otherwise false
     */
    public boolean isEmpty(){
        return first == null;
    }


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
      private Node<T> nextNode ;
        /**
         * constructor
         * @param nextNode- the next node
         */
      public LinkedListQueueIterator(Node<T> nextNode){
          this.nextNode = nextNode;
      }
        /**
         * continues to the next node
         * @return the data of the next node
         */
      @Override
        public T next(){
          T data = nextNode.getData();
          nextNode = nextNode.getNextNode();
          return data;
      }
        /**
         * checks if there is a next node
         * @return true if there is a next node, otherwise false
         */
      @Override
        public boolean hasNext(){
          return nextNode != null;
      }

    }
    /**
     * Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListQueueIterator(first);
    }
}

//    Integer num = new Integer(2);
//        num.clone();
//                Class myclass = Integer.class.getClass();
//        Method myclone = myclass.getMethod("clone");
//        myclone.invoke(num);
