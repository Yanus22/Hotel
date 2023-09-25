import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

abstract class Room implements Comparable<Room>, Serializable {

    private static long nextId = 1; // Shared among all Room objects
    protected long id = 0; // Each Room object gets a unique id
    protected boolean Tv = true;
    protected boolean closet = true;
    protected boolean bethRoom = true;
    protected boolean hasBar = false;
    protected boolean sitingArea = false;
    protected int countBed;
    protected int Price;
    Map<LocalDate, Integer> ReservedInDays = new HashMap<>();
    Map<LocalDate, String> mapOfHistory = new HashMap<>();

    Room() {
        id = IncrementId();
    }

    public boolean ReserveRoom(LocalDate dateCheckin, int days, Customer customer) {
        if(ReservedInDays.isEmpty()) {
            ReservedInDays.put(dateCheckin,days);
        }
        boolean canReserve = false;
        LocalDate datCheckOut = dateCheckin.plusDays(days);
        for (Map.Entry<LocalDate, Integer> entryes : ReservedInDays.entrySet()) {
            LocalDate tmpDateCheckin = entryes.getKey();
            Integer tmpDatReserve = entryes.getValue();
            LocalDate tmpDateCheckout = tmpDateCheckin.plusDays(tmpDatReserve);
            if ((dateCheckin.isAfter(tmpDateCheckin)||dateCheckin.isEqual(tmpDateCheckin)) && dateCheckin.isBefore(tmpDateCheckout)) {
                return false;
            } else if (dateCheckin.isBefore(tmpDateCheckin) && ((datCheckOut.isEqual(tmpDateCheckin)) || datCheckOut.isAfter(tmpDateCheckin))) {
                return false;
            }
            canReserve = true;
        }
        if (canReserve) {
            ReservedInDays.put(dateCheckin, days);
            mapOfHistory.put(dateCheckin, customer.getName());
            System.out.println(ReservedInDays.size());
            return true;
        }

        return false;
    }


    public long getId() {
        return id;
    }

    @Override
    public int compareTo(Room room) {
        return Integer.compare(this.Price, room.Price);
    }

    protected synchronized long IncrementId() {
        return nextId++;
    }

    public static void main(String[] args) {
        List<Room> listRoom = Arrays.asList(new SingleRoom(), new SingleRoom(), new SingleRoom(),
                new SingleRoom(), new SingleRoom(), new DoubleRoom(), new DoubleRoom(), new DoubleRoom(), new DeLyux(), new DeLyux(), new DeLyux());
        new FileWrite().WritInRoom(listRoom, "/home/yanus/IdeaProjects/ExamJava/src/ListRoom");
    }


}
