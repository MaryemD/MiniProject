import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class Event {
    private String eventName;
    private LocalDate eventDate;
    private String description;
    private List<User> signups = new ArrayList<>();

    public Event(String eventName, LocalDate eventDate, String description) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getSignups() {
        return signups;
    }

    public void setSignups(List<User> signups) {
        this.signups = signups;
    }
}
