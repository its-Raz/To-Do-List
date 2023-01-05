
public class Node<T extends Cloneable>{
    private T data;
    private Node<T> next;

    /**
     * CONSTRUCTOR
     * @param data
     */
    public Node(T data)
    {
        this.data=data;
    }

    /**
     * copy constructor
     * @param data
     * @param next
     */
    public Node(T data,Node<T> next)
    {
        this.data=data;
        this.next=next;
    }

    /**
     * updates the next node
     * @param otherNode - the node to add next
     */
    public void setNextNode(Node<T> otherNode)
    {
        this.next=otherNode;
    }

    /**
     * returns the next node
     * @return the next node
     */
    public Node<T> getNextNode()
    {
        return this.next;
    }

    /**
     * returns the data of the current node
     * @return the data of the current node
     */
    public T getData()
    {
        return this.data;
    }
}