import java.util.Date;
import java.text.SimpleDateFormat;

public class Task implements Cloneable {

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
}
