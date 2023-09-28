import java.time.LocalDate;
import java.util.*;

public class Hotel { //4rd 5rd keterna mnum
    private String pathCustomer = "src/Costumer";
    private String pathRoom = "src/RoomList";
    private FileRead fileRead = new FileRead();
    private FileWrite fileWrite = new FileWrite();
    private List<Room> roomList = new ArrayList<>();
    private Map<String, Customer> mapCustomer;

    Hotel() {

        // Check if customer data file exists, and create it if it doesn't
        if (!HelperStaticClass.fileExists(pathCustomer)) {
            HelperStaticClass.createFile(pathCustomer);
            mapCustomer = new HashMap<>();
            fileWrite.WriteInCoustumer(mapCustomer, pathCustomer);
        }
        if (!HelperStaticClass.fileExists(pathRoom)) {
            HelperStaticClass.createFile(pathRoom);
            roomList.add(new SingleRoom());
            roomList.add(new SingleRoom());
            roomList.add(new SingleRoom());
            roomList.add(new DoubleRoom());
            roomList.add(new DoubleRoom());
            roomList.add(new DeLyux());
            fileWrite.WritInRoom(roomList, pathRoom);
        } else {
            this.roomList = fileRead.ReadInRoom(pathRoom);
            this.mapCustomer = fileRead.readCustomerData(pathCustomer);
        }
        System.out.println(roomList);
        Hello();
    }


    private void Hello() {
        boolean exitAplication = false;
        while (exitAplication == false) {
            InformatIonAplication();
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().replaceAll(" ", "");
            if (answer.equals("1")) {
                Room room = CreatRoom();
                AddRoom(room);
            } else if (answer.equals("2")) {
                Register();
            } else if (answer.equals("3")) {
                Reserve();
            }
        }
    }

    private boolean Register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine().replaceAll(" ", "");
        while (name.equals("")) {
            System.out.println("this is not valid name ");
            name = scanner.nextLine();
        }
        if (mapCustomer.get(name) == null) {
            System.out.println("please enter your email");
            String email = scanner.nextLine();
            Customer customer = new Customer(name, email);
            mapCustomer.put(name, customer);
            fileWrite.WriteInCoustumer(mapCustomer, pathCustomer);
            System.out.println("registration is sucsesful");
            return true;
        }//else Peopel is will be Register
        System.out.println("this peopel alredy is register");
        return false;
    }

    private boolean Reserve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine();
        if (mapCustomer.get(name) != null) {//aysinqn et dzev mard ka { }
            Scanner scannerInt = new Scanner(System.in);
            System.out.println("we have 3 type which type do you want ");
            System.out.println("first type is have    bethroom , bed for One peopel, and  price is +  20 $");
            System.out.println("second type is have    bethroom , bed for two peopel, and  price is +  35 $");
            System.out.println("third type is have    bethroom , bed for two peopel,mini bar and siting area and  price is +  55 $");
            int answer = HelperStaticClass.TypeGenerator();
            if (answer == 1) {
                ReserveFirstType(name);
                fileWrite.WritInRoom(roomList, pathRoom);
                return true;
            } else if (answer == 2) {
                ReserveSecondType(name);
                return true;
            } else if (answer == 3) {
                ReserveThirdType(name);
                return true;
            }
        } else {
            System.out.println("need registration its customer is not register");
        }
        return false;
    }

    private void ReserveFirstType(String name) {
        List<Long> canReservWithDay = new ArrayList<>();
        LocalDate date = HelperStaticClass.DateGenerator();
        int day = HelperStaticClass.DayGenerator();
        for (Room elem : roomList) {
            if (elem.CanReserve(date, day) && (elem instanceof SingleRoom)) {
                canReservWithDay.add(elem.getId());
            }
        }
        if (canReservWithDay.size() == 0) {
            System.out.println("soory we not have  free room  in that time");
            return;
        }
        System.out.println(canReservWithDay + " in this room you can reserve");
        long idAnswer = HelperStaticClass.IdGenerAtor();
        while (!canReservWithDay.contains(idAnswer)) {
            System.out.println("please send id what are show ");
            System.out.println(canReservWithDay + " in this id rooms you can resereve");
            idAnswer = HelperStaticClass.IdGenerAtor();
        }

        for (Room elem : roomList) {
            if (elem.getId() == idAnswer) {
                Customer customer = mapCustomer.get(name);
                elem.ReserveRoom(date, day, customer);
                System.out.println("room is reserved sucsesful");
                return;
            }
        }
    }

    private void ReserveSecondType(String name) {
        List<Long> canReservWithDay = new ArrayList<>();
        LocalDate date = HelperStaticClass.DateGenerator();
        int day = HelperStaticClass.DayGenerator();
        for (Room elem : roomList) {
            if (elem.CanReserve(date, day) && (elem instanceof DoubleRoom )) {
                canReservWithDay.add(elem.getId());
            }
        }
        if (canReservWithDay.size() == 0) {
            System.out.println("soory we not have  free room ");
            return;
        }
        System.out.println(canReservWithDay + " in this room you can reserve");
        long idAnswer = HelperStaticClass.IdGenerAtor();
        while (!canReservWithDay.contains(idAnswer)) {
            System.out.println("please send id what are show ");
            System.out.println(canReservWithDay + " in this id rooms you can resereve");
            idAnswer = HelperStaticClass.IdGenerAtor();
        }

        for (Room elem : roomList) {
            if (elem.getId() == idAnswer) {
                Customer customer = mapCustomer.get(name);
                elem.ReserveRoom(date, day, customer);
                System.out.println("room is reserved sucsesful");
                return;
            }
        }
    }

    private void ReserveThirdType(String name) {
        List<Long> canReservWithDay = new ArrayList<>();
        LocalDate date = HelperStaticClass.DateGenerator();
        int day = HelperStaticClass.DayGenerator();
        for (Room elem : roomList) {
            if (elem.CanReserve(date, day) && (elem instanceof DeLyux)) {
                canReservWithDay.add(elem.getId());
            }
        }
        if (canReservWithDay.size() == 0) {
            System.out.println("soory we not have  free room ");
            return;
        }
        System.out.println(canReservWithDay + " in this room you can reserve");
        long idAnswer = HelperStaticClass.IdGenerAtor();
        while (!canReservWithDay.contains(idAnswer)) {
            System.out.println("please send id what are show ");
            System.out.println(canReservWithDay + " in this id rooms you can resereve");
            idAnswer = HelperStaticClass.IdGenerAtor();
        }

        for (Room elem : roomList) {
            if (elem.getId() == idAnswer) {
                Customer customer = mapCustomer.get(name);
                elem.ReserveRoom(date, day, customer);
                System.out.println("room is reserved sucsesful");
                return;
            }
        }
    }

    private boolean AddRoom(Room room) {
        if (roomList == null) {
            System.out.println("esim inch");
        } else if (roomList.contains(room)) {
            System.out.println("this room already is included in hote");
            return false;
        } else {
            roomList.add(room);
            fileWrite.WritInRoom(roomList, pathRoom);
        }
        return true;
    }

    private void InformatIonAplication() {
        String message = """
                1: Add a Room
                2: Add a Customer
                3: Book a Room for a Customer
                4: Save System State to a File
                5: Make report
                6: Print hotel info
                0: Exit
                """;
        System.out.println(message);
    }

    private Room CreatRoom() {
        Scanner scanner = new Scanner(System.in);
        String mesage = """
                1:SingleRoom
                2:DoubleRoom
                3:DeLyuxRoom
                """;
        System.out.println(mesage);
        String answer = scanner.nextLine().replaceAll(" ", "");
        if (answer.equals("1")) {
            return new SingleRoom();
        } else if (answer.equals("2")) {
            return new DoubleRoom();
        } else if (answer.equals("3")) {
            return new DeLyux();
        } else {
            System.out.println("invalid type");
            return CreatRoom();
        }
    }
}
