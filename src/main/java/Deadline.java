import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDate endTime;

    public Deadline(String name, LocalDate endTime) {
        super(name);
        this.endTime = endTime;
    }

    public Deadline(String name, LocalDate endTime, boolean done) {
        super(name, done);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "D" + super.toData() + "%" + this.endTime;
    }
}
