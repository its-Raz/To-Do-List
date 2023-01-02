public class LinkedListQueue<T extends Cloneable> implements Queue<T> {
    Node<T> first;
    Node<T> last;
    public void enqueue(T element)
    {
        
    }


    public T dequeue()
    {
       
    }
    public T peek()
    {
       
    }

    public int size()
    {
      
    }
    public boolean isEmpty(){}


    @Override
    public LinkedListQueue<T> clone() {
      
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
