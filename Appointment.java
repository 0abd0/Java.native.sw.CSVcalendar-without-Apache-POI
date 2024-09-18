import java.time.LocalDateTime;

public class Appointment {
    private final LocalDateTime time;

    public Appointment(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
