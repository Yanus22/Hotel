import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

abstract class Room implements Comparable<Room>, Serializable {
    long id;
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
        this.id+=1;
    }

    public boolean ReserveRoom(LocalDate dateCheckin, int days, Customer customer) {
        boolean canReserve = false;
        LocalDate datCheckOut = dateCheckin.plusDays(days);
        for (Map.Entry<LocalDate, Integer> entryes : ReservedInDays.entrySet()) {
            LocalDate tmpDateCheckin = entryes.getKey();
            Integer tmpDatReserve = entryes.getValue();
            LocalDate tmpDateCheckout = tmpDateCheckin.plusDays(tmpDatReserve);
            if (dateCheckin.isAfter(tmpDateCheckin) && dateCheckin.isBefore(tmpDateCheckout)) {
                return false;
            } else if (dateCheckin.isBefore(tmpDateCheckin) && ((datCheckOut.isEqual(tmpDateCheckin)) || datCheckOut.isAfter(tmpDateCheckin))) {
                return false;
            }
            canReserve = true;
        }
        ReservedInDays.put(dateCheckin, days);
        mapOfHistory.put(dateCheckin, customer.getName());
        return true;
    }


    public boolean Reserve(LocalDate date, int age, Customer customer) {
        if (ReserveRoom(date, age, customer)) {
            System.out.println("your room is reserved");
            return true;
        }
        System.out.println("room is reserved");
        return false;
    }

    @Override
    public int compareTo(Room room) {
        return Integer.compare(this.Price, room.Price);
    }

}
