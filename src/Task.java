import java.util.Date;
import java.text.SimpleDateFormat;

public class Task implements Cloneable,Comparable {

    private String description;
    private Date dueDate;
    private String dueDateSimple;


    public Task(String desc, Date date)
    {
        this.description=desc;
        this.dueDate=date;
        this.dueDateSimple=simpleDateFormat(date);
    }

    public static String simpleDateFormat(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDueDateSimple() {
        return dueDateSimple;
    }


    @Override
    public int hashCode() {
        String space = " ";
        return (this.description+space+this.dueDateSimple).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Task))
        {return false;}
        else
        {
            Task other = (Task)obj;
            return this.dueDate.equals(other.getDueDate()) && this.description.equals(other.getDescription());
        }
    }

    //TODO:CHECK MAYBE TO ADD COMPERATOR AND NOT USING COMPARE TO DUE TO DOWNCASTING DANGER

    @Override
    public int compareTo(Object other) {
        Task otherTask = (Task)other;
        if(this.equals(otherTask)){return 0;}
        if(this.getDueDate().compareTo(otherTask.getDueDate())==1)
        {
            if(this.description.compareTo(otherTask.getDescription())==1)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else{return -1;}

    }

    @Override
    public Task clone() {
        try{
            Task copy = (Task) super.clone();
            copy.dueDate = (Date) super.clone();
            return copy;
        }
        catch(CloneNotSupportedException e)
        {return null;}
    }

    @Override
    public String toString() {
        String str = "(" + this.description + "," + this.dueDateSimple + ")";
        return str;
    }
}
