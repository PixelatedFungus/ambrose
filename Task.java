package lusaris;

import java.io.Serializable;
import java.time.LocalTime;

public class Task implements Serializable {

    /* The title of our Task. */
    private String _title;

    /* The date of this Task's creation. */
    private LocalTime _currentDate;

    /* The details of this Task. */
    private String _details;

    public Task(String title) {
        _title = title;
        _currentDate = LocalTime.now();
        _details = "Placeholder";
    }

    /**
     * Getter method for the title of this blob.
     * @return A String that is the title of this blob.
     */
    public String get_title() {
        return _title;
    }

    /**
     * Setter method for the title of this blob.
     * @param _title A String that sets the title of this blob.
     */
    public void set_title(String _title) {
        this._title = _title;
    }

    /**
     * A simple hash function that guarantees all tasks are unique.
     * @return The hashcode of an instance of the class.
     */
    public int hashCode() {
        int hash = (_title + _currentDate).hashCode();
        if (hash < 0) {
            hash *= -1;
        }
        return hash;
    }

    /**
     * The override of the print function in Java that allows us to customize
     * the output of a System.out.
     * @return The title of the task and the time at which it was created.
     */
    public String toString() {
        return _title + " created at " + _currentDate.toString();
    }
}
