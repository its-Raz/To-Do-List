import java.util.LinkedHashSet;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

public class ToDoList implements Cloneable,TaskIterable {
    LinkedHashSet<Task> addingOrderList;
    TreeMap<Date, TreeSet<Task>> dateOrderDict;

    public ToDoList() {
        super();
    }

    @Override
    public void setScanningDueDate(Date date) {

    }



    public void addTask(Task task)
    {
        //TODO:ADD TO BOTH LISTS
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public Iterator<Task> iterator() {
        return null;
    }

    private class ToDoListIterator implements Iterator<Task> {
        private Task nextTask ;
        /**
         * constructor
         * @param nextTask- the next node
         */
        public ToDoListIterator(Task nextTask){

        }
        /**
         * continues to the next node
         * @return the data of the next node
         */
        @Override
        public Task next(){
            return null;
        }

        /**
         * checks if there is a next node
         * @return true if there is a next node, otherwise false
         */
        @Override
        public boolean hasNext(){
            return true;

        }

    }


}
