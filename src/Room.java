import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

abstract class Room implements Comparable<Room>, Serializable {
    protected long Id = 0; // Each Room object gets a unique id
    private static long nextId = 1;
    protected boolean Tv = true;
    protected boolean closet = true;
    protected boolean bethRoom = true;
    protected boolean hasBar = false;
    protected boolean sitingArea = false;
    protected int countBed;
    protected int Price;
    protected Map<LocalDate, Integer> ReservedInDays = new HashMap<>();
    protected Map<String, LocalDate> mapOfHistory = new HashMap<>();//name and chechkin time customer

    Room() {

        this.Id = nextId;
        IncremetnId();
    }

    public boolean ReserveRoom(LocalDate dateCheckin, int days, Customer customer) {
        if (ReservedInDays.isEmpty()) {
            mapOfHistory.put(customer.getName(),dateCheckin);
            ReservedInDays.put(dateCheckin, days);
            return true;
        }
        boolean canReserve = false;
        LocalDate datCheckOut = dateCheckin.plusDays(days);
        for (Map.Entry<LocalDate, Integer> entryes : ReservedInDays.entrySet()) {
            LocalDate tmpDateCheckin = entryes.getKey();
            Integer tmpDatReserve = entryes.getValue();
            LocalDate tmpDateCheckout = tmpDateCheckin.plusDays(tmpDatReserve);
            if ((dateCheckin.isAfter(tmpDateCheckin) || dateCheckin.isEqual(tmpDateCheckin)) && dateCheckin.isBefore(tmpDateCheckout)) {
                return false;
            } else if (dateCheckin.isBefore(tmpDateCheckin) && ((datCheckOut.isEqual(tmpDateCheckin)) || datCheckOut.isAfter(tmpDateCheckin))) {
                return false;
            }
            canReserve = true;
        }
        if (canReserve) {
            ReservedInDays.put(dateCheckin, days);
            mapOfHistory.put(customer.getName(), dateCheckin);
            System.out.println(ReservedInDays.size());
            return true;
        }
        return false;
    }

    public boolean CanReserve(LocalDate dateCheckin, int days) {
        if (ReservedInDays.isEmpty()) {
            return true;
        }
        boolean canReserve = false;
        LocalDate datCheckOut = dateCheckin.plusDays(days);
        for (Map.Entry<LocalDate, Integer> entryes : ReservedInDays.entrySet()) {
            LocalDate tmpDateCheckin = entryes.getKey();
            Integer tmpDatReserve = entryes.getValue();
            LocalDate tmpDateCheckout = tmpDateCheckin.plusDays(tmpDatReserve);
            if ((dateCheckin.isAfter(tmpDateCheckin) || dateCheckin.isEqual(tmpDateCheckin)) && dateCheckin.isBefore(tmpDateCheckout)) {
                return false;
            } else if (dateCheckin.isBefore(tmpDateCheckin) && ((datCheckOut.isEqual(tmpDateCheckin)) || datCheckOut.isAfter(tmpDateCheckin))) {
                return false;
            }
            canReserve = true;
        }
        return canReserve;
    }


    public long getId() {
        return Id;
    }

    public int getPrice() {
        return Price;
    }

    public boolean isBethRoom() {
        return bethRoom;
    }

    public boolean isCloset() {
        return closet;
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public boolean isSitingArea() {
        return sitingArea;
    }

    public boolean isTv() {
        return Tv;
    }

    public int getCountBed() {
        return countBed;
    }

    public Map<LocalDate, Integer> getReservedInDays() {
        return ReservedInDays;
    }

    public Map<String, LocalDate> getMapOfHistory() {
        return mapOfHistory;
    }

    protected void IncremetnId() {
        this.nextId++;
    }
    public static void SetNextId(long idNext){
        nextId = idNext;
    }


    @Override
    public int compareTo(Room room) {
        return Integer.compare(this.Price, room.Price);
    }
}
