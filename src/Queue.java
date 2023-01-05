public interface Queue<E extends Cloneable> extends Iterable<E>, Cloneable {

    /**
     * adds a node to the end of the queue
     * @param element- the node to add
     */
    void enqueue(E element);

    /**
     * removes the head and returns the data of the head
     * @return the data of the head
     */
    E dequeue();

    /**
     * returns the first node at the head of the queue without removing it
     * @return the first node
     */
    E peek();

    /**
     * checks how many node are there
     * @return the number of nodes
     */
    int size();

    /**
     * checks if the queue is empty
     * @return true if empty, otherwise false
     */
    boolean isEmpty();

    /**
     * copies a queue
     * @return the copied queue
     */
    Queue<E> clone();
}


