import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task implements Cloneable,Comparable {
    private String description;
    private Date dueDate;
    private String dueDateSimple;

    /**
     * CONSTRUCTOR
     * @param desc -the description
     * @param date - dueDate
     */
    public Task(String desc, Date date)
    {
        this.description=desc;
        this.dueDate=date;
        this.dueDateSimple=simpleDateFormat(date);
    }

    /**
     * creates a String of a current date
     * @param date
     * @return a String of a current date
     */
    public static String simpleDateFormat(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    /**
     * gets the description of the current task
     * @return the description of the current task
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the due date
     * @param date - the date to update
     */
    public void setDueDate(Date date)
    {
        this.dueDate.setTime(date.getTime());
        this.dueDateSimple = simpleDateFormat(date);
    }

    /**
     * gets the current due date
     * @return the current due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * gets the string of the current due date
     * @return the string of the current due date
     */
    public String getDueDateSimple() {
        return dueDateSimple;
    }

    /**
     *returns the hash code of the current task
     * @return the hash code of the current task
     */
    @Override
    public int hashCode() {
        String space = " ";
        return (this.description+space+this.dueDateSimple).hashCode();
    }

    /**
     * checks if two tasks are the same
     * @param obj - task1 to compare
     * @return true if the tasks are the same, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Task))
        {return false;}
        else
        {
            Task other = (Task)obj;
            return this.description.equals(other.getDescription());
        }
    }

    /**
    * compares two tasks
     * @param other the object to be compared.
     * @return 1 if the tasks are the same, otherwise -1
     */
    @Override
    public int compareTo(Object other) {
        Task otherTask = (Task)other;
        if(this.equals(otherTask)){return 0;}
        int dateCompare=this.getDueDate().compareTo(otherTask.getDueDate());
        if(dateCompare==0)//if they have same date but diff desc
        {
            if(this.description.compareTo(otherTask.getDescription())>0)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        } else if (dateCompare>0) {
            return 1;
        }
        else{return -1;}
    }

    /**
     * copies the current task
     * @return a copy of the current task
     */
    @Override
    public Task clone() {
        try{
            Task copy = (Task) super.clone();
            copy.dueDate = (Date) dueDate.clone();
            return copy;
        }
        catch(CloneNotSupportedException e){
            return null;
        }
    }

    /**
     * creates a String of the current task
     * @return a String of the current task
     */
    @Override
    public String toString() {
        String str = this.description + ", " + this.dueDateSimple;

        return str;
    }
}