import jdk.nashorn.api.tree.Tree;

import java.util.*;

public class ToDoList implements Cloneable,TaskIterable {
    private LinkedHashSet<Task> addingOrderList;
    private TreeMap<Date, TreeSet<Task>> dateOrderDict;
    private Date scanningDueDate;

    public ToDoList()
    {
        this.addingOrderList=new LinkedHashSet<Task>();
        this.dateOrderDict=new TreeMap<Date,TreeSet<Task>>();
        this.scanningDueDate=null;
    }

    public TreeMap<Date, TreeSet<Task>> getDateOrderDict() {
        return dateOrderDict;
    }

    public LinkedHashSet<Task> getAddingOrderList() {
        return addingOrderList;
    }

    public Task getFirstTask()
    {
        for(Map.Entry<Date,TreeSet<Task>> entry : dateOrderDict.entrySet())
        {
            for(Task task:entry.getValue())
            {
                return task;
            }
        }
        return null;
    }

    @Override
    public void setScanningDueDate(Date date) {
        this.scanningDueDate=date;
    }

    public void addTask(Task task)
    {
        Date taskDate = task.getDueDate();
        TreeSet<Task> tSet;
        if (!(dateOrderDict.containsKey(taskDate)))
        {
            tSet = new TreeSet<Task>(new TaskComparator());
            tSet.add(task);
            this.dateOrderDict.put(taskDate,tSet);
            addingOrderList.add(task);
        }
        else
        {
            tSet = dateOrderDict.get(taskDate);
            for(Task treeTask:tSet)
            {
                if(treeTask.equals(task)){
                    throw new TaskAlreadyExistsException();
                }
            }
                tSet.add(task);
                addingOrderList.add(task);
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
        if(this.scanningDueDate!=null)
        {
            return new ToDoListIterator(getFirstTask(),this.scanningDueDate);
        }
        else{
            return new ToDoListIterator(getFirstTask());
        }
    }

    private class ToDoListIterator implements Iterator<Task> {
        private Task nextTask ;
        private Date nextDate;
        private Date limitScanDate;


        /**
         * constructor
         * @param firstTask- the next node
         */
        public ToDoListIterator(Task firstTask){
            this.nextTask=firstTask;
            this.nextDate=getDateOrderDict().firstKey();
            this.limitScanDate=null;
        }
        public ToDoListIterator(Task firstTask,Date date)
        {
            this.nextTask=firstTask;
            this.nextDate=getDateOrderDict().firstKey();
            this.limitScanDate=date;
        }
        /**
         * continues to the next node
         * @return the data of the next node
         */
        @Override
        public Task next(){
            boolean noChange=false;
                while(!(noChange)){
                for(Task task: dateOrderDict.get(nextDate))
                {
                    if(task.getDescription().compareTo(nextTask.getDescription())>0) //means that we find a task with greater lexicographical value
                    {
                        nextTask = task;
                        return nextTask;
                    }
                }
                if(dateOrderDict.tailMap(nextDate).firstKey()!=null)
                {
                    nextDate = dateOrderDict.tailMap(nextDate).firstKey();
                    if(this.limitScanDate!=null && this.limitScanDate.compareTo(nextDate) < 0) //means the limit scan date is lower than the next key date that found
                    {
                        this.nextTask=null;
                        return null;
                    }
                }
                else
                {
                    this.nextTask=null;
                    return null;
                }
            }
            return nextTask;
        }

        /**
         * checks if there is a next node
         * @return true if there is a next node, otherwise false
         */
        @Override
        public boolean hasNext(){
            return nextTask!=null;

        }

    }
    public class TaskComparator implements Comparator<Task>
    {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.compareTo(t2);
        }
    }
}
