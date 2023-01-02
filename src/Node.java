
public class Node<T extends Cloneable>{
    private T data;
    private Node<T> next;
    public Node(T data)
    {
        this.data=data;
    }
    public Node(T data,Node<T> next)
    {
        this.data=data;
        this.next=next;
    }


    public void setNextNode(Node<T> otherNode)
    {
        this.next=otherNode;
    }

    public Node<T> getNextNode()
    {
        return this.next;
    }

    public T getData()
    {
        return this.data;
    }


}
//**