package scratch;

public class PrayerParticipant implements Participant
{
    private String first = null;
    private String last = null;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


    @Override
    public String toString() {
        return "PrayerParticipant{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }


    @Override
    public String identifier() {
        return this.getLast() + this.getFirst();
    }
}
