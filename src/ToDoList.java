import jdk.nashorn.api.tree.Tree;

import java.util.*;
public class ToDoList implements Cloneable,TaskIterable {
    private LinkedHashSet<Task> addingOrderList;
    private TreeMap<Date, TreeSet<Task>> dateOrderDict;
    private Date scanningDueDate;

    /**
     *CONSTRUCTOR
     */
    public ToDoList()
    {
        this.addingOrderList=new LinkedHashSet<Task>();
        this.dateOrderDict=new TreeMap<Date,TreeSet<Task>>();
        this.scanningDueDate=null;
    }

    /**
     * returns the list of tasks by date order
     * @return the list of tasks by date order
     */
    public TreeMap<Date, TreeSet<Task>> getDateOrderDict() {
        return dateOrderDict;
    }

    /**
     * returns the list of tasks by adding order
     * @return the list of tasks by adding order
     */
    public LinkedHashSet<Task> getAddingOrderList() {
        return addingOrderList;
    }

    /**
     *gets the first task of the list
     * @return the first task of the list
     */
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

    /**
     * sets the date that will determine the way to scan
     * @param date- the date that will determine the way to scan
     */
    @Override
    public void setScanningDueDate(Date date) {
        this.scanningDueDate=date;
    }

    /**
     *adds a task to the list
     * @param task- the task to add
     */
    public void addTask(Task task)
    {
        Date taskDate = task.getDueDate();
        TreeSet<Task> tSet;
        for(Task t : addingOrderList)
        {
            if(t.equals(task))
            {
                throw new TaskAlreadyExistsException();
            }
        }
        if (!(dateOrderDict.containsKey(taskDate)))
        {
            tSet = new TreeSet<Task>(new TaskComparator());
            tSet.add(task);
            this.dateOrderDict.put(task.getDueDate(),tSet);
            addingOrderList.add(task);
        }
        else
        {
            tSet = dateOrderDict.get(taskDate);
                tSet.add(task);
                addingOrderList.add(task);
        }

    }

    /**
     *returns the hash code of the current list
     * @return the hash code of the current list
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for(Task task:addingOrderList){
            hash += task.hashCode();
        }
        return hash;
    }

    /**
     * checks if two lists are the same
     * @param obj - list1 to compare
     * @return true if the lists are the same, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ToDoList))
        {return false;}
        ToDoList other = (ToDoList) obj;
        TreeMap<Date, TreeSet<Task>> otherDateOrderDict = other.dateOrderDict;
        if(other.addingOrderList.size() != this.addingOrderList.size())
        {return false;} //they dont have the same amount of tasks
        if(other.dateOrderDict.size() != this.dateOrderDict.size())
        {return false;} //maybe they have the same amount of tasks but different key date values
        for(Map.Entry<Date,TreeSet<Task>> entry : otherDateOrderDict.entrySet())
        {
            if(!(this.dateOrderDict.containsKey(entry.getKey()))) //because if this date doesnt exist in this tree so
            {
                return false;
            }
            TreeSet<Task> otherTSet = entry.getValue();
            TreeSet<Task> thisTSet = this.dateOrderDict.get(entry.getKey());
            for(Task otherTask:otherTSet)
            {
                boolean exist=false;
               for(Task thisTask : thisTSet)
               {
                   if(otherTask.equals(thisTask))
                   {
                       exist=true;
                   }
               }
               if(!exist)
               {
                   return false;
               }
            }
        }
        return true;
    }

    /**
     * copies the current list
     * @return a copy of the current list
     */
    @Override
    public ToDoList clone() {
        try{
        ToDoList newTDL = new ToDoList();
        for(Task t : this.addingOrderList)
        {
            newTDL.addTask(t.clone());
        }
        if(this.scanningDueDate!=null){
        Date copiedScanningDate =(Date) this.scanningDueDate.clone();
            newTDL.setScanningDueDate(copiedScanningDate);}

        return newTDL;}
        catch(Exception e)
        {
            return null;
        }
    }

    /**
     * creates a String of the current list
     * @return a String of the current list
     */
    @Override
    public String toString() {
        int counter=0;
        String str = "[";
        if(this.addingOrderList == null){
            return str + "]";
        }
        for(Task task:addingOrderList){
            if(counter==0)
            {str +="("+ task+")";}
            else{
                str += ", ";
                str +="("+ task+")";
            }
            counter++;
        }
        return str + "]";
    }

    /**
     * ITERATOR
     * @return an iterator
     */
    @Override
    public Iterator<Task> iterator() {
        if(this.scanningDueDate!=null)
        {
            return new ToDoListIterator(getFirstTask(),this.scanningDueDate);
        }
        else if(!addingOrderList.isEmpty()){
            return new ToDoListIterator(getFirstTask());
        }
        else
        {
            return new ToDoListIterator();
        }
    }

    private class ToDoListIterator implements Iterator<Task> {
        private Task nextTask ;
        private Date nextDate;
        private Date limitScanDate;

        /**
         * CONSTRUCTOR
         */
        public ToDoListIterator() {
            this.nextTask=null;
        }

        /**
         * CONSTRUCTOR
         * @param firstTask- the next node
         */
        public ToDoListIterator(Task firstTask){
            this.nextTask=firstTask;
            this.nextDate=getDateOrderDict().firstKey();
            this.limitScanDate=null;
        }

        /**
         * CONSTRUCTOR
         * @param firstTask- the next node
         * @param date- the limitScanDate
         */
        public ToDoListIterator(Task firstTask,Date date)
        {
            this.nextTask=firstTask;
            this.nextDate=getDateOrderDict().firstKey();
            this.limitScanDate=date;
        }

        /**
         * continues to the next task
         * @return the data of the next task
         */
        @Override
        public Task next()
        {
            Task taskToReturn = nextTask;
            nextTask = dateOrderDict.get(nextDate).higher(taskToReturn);
            if(nextTask==null)
            {
                nextDate = dateOrderDict.higherKey(nextDate);
                if(nextDate!=null)
                {
                    if(limitScanDate==null){nextTask = dateOrderDict.get(nextDate).first();}
                    else if(nextDate.compareTo(limitScanDate)<=0)
                    {
                        nextTask = dateOrderDict.get(nextDate).first();
                    }
                    else
                    {
                        nextDate=null;
                    }
                }
            }
            return taskToReturn;
        }

        /**
         * checks if there is a next task
         * @return true if there is a next task, otherwise false
         */
        @Override
        public boolean hasNext(){
            if(addingOrderList.isEmpty()){return false;}
            if(limitScanDate!=null && nextDate!=null)
            {
                if(limitScanDate.compareTo(nextDate)<0)
                {
                    return false;
                }
            }
            return nextTask!=null||nextDate!=null;
        }
    }
    public class TaskComparator implements Comparator<Task>
    {
        /**
         * compares two tasks
         * @param t1 the first object to be compared.
         * @param t2 the second object to be compared.
         * @return
         */
        @Override
        public int compare(Task t1, Task t2) {
            return t1.compareTo(t2);
        }
    }
}
