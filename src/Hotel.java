import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Hotel {
    private String pathCustomer = "src/Costumer";
    private String pathRoom = "src/RoomList";
    private FileRead fileRead = new FileRead();
    private FileWrite fileWrite = new FileWrite();
    private List<Room> roomList = new ArrayList<>();
    private Map<String, Customer> mapCustomer;///

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
            long maxId = -1;
            for (Room elem : roomList) {
                if (elem.getId() > maxId) {
                    maxId = elem.getId();
                }
            }
            maxId++;
            Room.SetNextId(maxId);
        }
        Hello();
    }


    private void Hello() {//start
        boolean exitAplication = false;
        while (exitAplication == false) {
            String reoport = "";
            InformatIonAplication();
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().replaceAll(" ", "");
            if (answer.equals("1")) {//add room in hotel
                Room room = CreatRoom();
                AddRoom(room);
            } else if (answer.equals("2")) {//add coustumer
                Register();
            } else if (answer.equals("3")) {//reserve room
                Reserve();
            } else if (answer.equals("4")) {//save information in txt
                fileWrite.WritInRoom(roomList, pathRoom);
                fileWrite.WriteInCoustumer(mapCustomer, pathCustomer);
            } else if (answer.equals("5")) {//show   last rsereve  information
                if (HelperStaticClass.ReportReader() != null) {
                    System.out.println(HelperStaticClass.ReportReader());
                }
            } else if (answer.equals("6")) {//show hotel infromation
                HotelInfo();
            }
            else if(answer.equals("0")){//exit
                exitAplication = true;
            }
            else {
                System.out.println("please choice number of [0-6]");
            }
        }
    }

    private boolean Register() { //add coustumer
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine().replaceAll(" ", "");
        while (name.equals("")) {
            System.out.println("this is not valid name ");
            name = scanner.nextLine();
        }
        if (mapCustomer.get(name) == null) {// no costumer in map -> can register
            System.out.println("please enter your email");
            String email = scanner.nextLine();
            Customer customer = new Customer(name, email);
            mapCustomer.put(name, customer);
            System.out.println("registration is sucsesful");
            return true;
        }//else Peopel is will be Register
        System.out.println("this peopel alredy is register");
        return false;
    }

    private boolean Reserve() {//reserve rrom
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine();
        if (mapCustomer.get(name) != null) {//costumer is register -> can reserve { }
            System.out.println("we have 3 type which type do you want ");
            System.out.println("first type is have    bethroom , bed for One peopel, and  price is +  20 $");
            System.out.println("second type is have    bethroom , bed for two peopel, and  price is +  35 $");
            System.out.println("third type is have    bethroom , bed for two peopel,mini bar and siting area and  price is +  55 $");
            int answer = HelperStaticClass.TypeGenerator();
            if (answer == 1) {//single room
                ReserveFirstType(name);
                return true;
            } else if (answer == 2) {//double room
                ReserveSecondType(name);
                return true;
            } else if (answer == 3) {//delyux room
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
            if (elem.CanReserve(date, day) && (elem instanceof SingleRoom)) {//filter room which can reserve in that time
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
                String report = HelperStaticClass.RepoortFunction(elem, name);
                System.out.println(report);
                return;
            }
        }
    }

    private void ReserveSecondType(String name) {
        List<Long> canReservWithDay = new ArrayList<>();
        LocalDate date = HelperStaticClass.DateGenerator();
        int day = HelperStaticClass.DayGenerator();
        for (Room elem : roomList) {
            if (elem.CanReserve(date, day) && (elem instanceof DoubleRoom)) {
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
                String report = HelperStaticClass.RepoortFunction(elem, name);
                System.out.println(report);
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
                String report = HelperStaticClass.RepoortFunction(elem, name);
                System.out.println(report);
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

    private void HotelInfo() {
        int countSinglRoom = 0;
        int countDoubleRoom = 0;
        int countDelyuxRoom = 0;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i) instanceof SingleRoom) {
                countSinglRoom++;
            } else if (roomList.get(i) instanceof DoubleRoom) {
                countDoubleRoom++;
            } else if (roomList.get(i) instanceof DeLyux) {
                countDelyuxRoom++;
            }
        }
        System.out.println("hello in our hotel , now show you information about rooms ");
        if (countSinglRoom != 0) {
            System.out.print("we have " + countSinglRoom + " singleRoom");
            System.out.println("and price is this room  20");
        }
        if (countSinglRoom != 0) {
            System.out.print("we have" + countDoubleRoom + " doubleRoom");
            System.out.println("and price is this room  35");
        }
        if (countDelyuxRoom != 0) {
            System.out.print("we have" + countDelyuxRoom + " DelyuxRoom");
            System.out.println("and price is this room  55");
        }
    }
}
