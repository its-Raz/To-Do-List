import jdk.nashorn.api.tree.Tree;

import java.util.LinkedHashSet;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

public class ToDoList implements Cloneable,TaskIterable {
    LinkedHashSet<Task> addingOrderList;
    TreeMap<Date, TreeSet<Task>> dateOrderDict;

    public ToDoList()
    {
        this.addingOrderList=null;
        this.dateOrderDict=null;
    }

    public TreeMap<Date, TreeSet<Task>> getDateOrderDict() {
        return dateOrderDict;
    }

    public LinkedHashSet<Task> getAddingOrderList() {
        return addingOrderList;
    }

    @Override
    public void setScanningDueDate(Date date) {

    }

    public void addTask(Task task)
    {
        Date taskDate = task.getDueDate();
        if(this.dateOrderDict == null)
        {
            this.dateOrderDict = new TreeMap<Date,TreeSet<Task>>();
            TreeSet<Task> tSet = new TreeSet<Task>();
            tSet.add(task);
            this.dateOrderDict.put(taskDate,tSet);
            this.addingOrderList = new LinkedHashSet<>();
            this.addingOrderList.add(task);
        }
        else if(!(dateOrderDict.containsKey(taskDate)))
        {
            TreeSet<Task> tSet = new TreeSet<Task>();
            tSet.add(task);
            this.dateOrderDict.put(taskDate,tSet);
            addingOrderList.add(task);
        }
        else
        {
            TreeSet<Task> tSet = dateOrderDict.get(taskDate);
            boolean alreadyExist = false;
            for(Task treeTask:tSet)
            {
                if(treeTask.equals(task)){alreadyExist=true;}
            }
            if(!(alreadyExist))
            {
                tSet.add(task);
                addingOrderList.add(task);
            }
        }
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
