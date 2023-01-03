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
        String str = "[";
        if(this.addingOrderList == null){
            return str + "]";
        }
        Iterator itr = this.addingOrderList.iterator(); // ********** remove after update? ***********
        for(Task task:addingOrderList){
            str = str + "," + task;
        }
        return str + "]";
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
            TreeSet<Task> tSet;
            Task taskToReturn=null;
            if(nextTask!=null){
            taskToReturn=nextTask;
            tSet = dateOrderDict.get(nextDate);
            nextTask = tSet.higher(taskToReturn);
            }
            if(nextTask==null&&taskToReturn==null)
            {
                if (dateOrderDict.higherKey(nextDate) != null)
                {
                    nextDate = dateOrderDict.higherKey(nextDate);
                    if (this.limitScanDate != null && this.limitScanDate.compareTo(nextDate) < 0)
                    {
                        nextTask=null;
                        nextDate=null;
                    }
                    else
                    {
                        tSet = dateOrderDict.get(nextDate);
                        taskToReturn = tSet.first();
                        nextTask=taskToReturn;
                    }
                }
                else {nextTask=null;nextDate=null;}
            }
            return taskToReturn;
        }

        /**
         * checks if there is a next node
         * @return true if there is a next node, otherwise false
         */
        @Override
        public boolean hasNext(){
            return nextTask!=null||nextDate!=null;

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
