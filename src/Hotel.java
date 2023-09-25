import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Hotel {
   private String pathCustomer = "/home/yanus/IdeaProjects/ExamJava/src/Coustumer";
  private   String pathRoom = "/home/yanus/IdeaProjects/ExamJava/src/ListRoom";
   private FileRead fileRaed = new FileRead();
   private FileWrite fileWrite = new FileWrite();
   private List<Room> roomList = fileRaed.ReadInRoom(pathRoom);
   private Map<String, Customer> mapCustomer = fileRaed.readCustomerData(pathCustomer);

    Hotel() {
        Hello();
    }


    private void Hello() {
        Scanner scannerString = new Scanner(System.in);
        System.out.println("If you want to Reserve a room, please enter 'Reserve'.");
        System.out.println("If you want to Register, please enter 'Register'.");
        String answer = scannerString.nextLine();

        if (answer.contains("Reserve")) {
            if (Reserve()) {
                return;
            }

        } else if (answer.contains("Register")) {
            if (Register()) {
                Reserve();
            }

        } else {
            System.out.println("Your answer is not correct. Please try again.");
            Hello();
        }
    }

    private boolean Register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine();
        if (mapCustomer.get(name) == null) {
            System.out.println("please enter your email");
            String email = scanner.nextLine();
            Customer customer = new Customer(name, email);
            mapCustomer.put(name, customer);
            fileWrite.WriteInCoustumer(mapCustomer, pathCustomer);
            System.out.println("registration is sucsesful");
            return true;
        }// aysinqn anunov mard chka
        else {
            System.out.println("thiis peopel already is register");
            System.out.println("if you want register please enter yes"); //peopel is register and need register another name
            System.out.println("if you want resereve please enter resereve");
            String answer = scanner.nextLine();
            if (answer.contains("yes")) {
                Register();
                return true;
            } else if (answer.contains("reserve")) {
                Reserve();
                return true;
            }
        }
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
            int answer = scannerInt.nextInt();
            if (answer == 1) {
                ReserveFirstType(name);
                fileWrite.WritInRoom(roomList,pathRoom);
                return true;
            } else if (answer == 2) {
                ReserveSecondType(name);
                return true;
            } else if (answer == 3) {
                ReserveThirdType(name);
                return true;
            } else {
                System.out.println("ilegal type");
                throw new RuntimeException();
            }
        } else {
            System.out.println("need registration for reserved");
            Register();
            Reserve();
        }
        return false;
    }

    private void ReserveFirstType(String name) {
        Scanner scannerString = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("in this id you can choce");
        roomList.forEach(elem -> {
            if (elem instanceof SingleRoom) {
                System.out.print(elem.getId() + ",");
            }
        });
        System.out.println("please enter id");
        int answerId = scanner.nextInt();
      label:roomList.forEach(elem -> {
            if (elem.id == answerId) {
                LocalDate datCheckin = ReservInDay();
                System.out.println("print how days you want");
                int days = scanner.nextInt();
                while (days <=0) {
                    System.out.println("is not valid days");
                    System.out.println("please enter days");
                    days = scanner.nextInt();
                }
                if(elem.ReserveRoom(datCheckin,days,mapCustomer.get(name))){
                    System.out.println("Reserved is sucsesful");
                    System.out.println("do you want   see your  information enter yes ");
                    String answer = scannerString.nextLine();
                    if(answer.contains("yes")) {
                        PrintInfo(elem,name,days);
                    }else {
                        return;
                    }
                }
                else {
                    System.out.println("this room is resereved in days who you want ");
                   ReserveFirstType(name);
                   return;

                }
            }
        });

    }

    private void ReserveSecondType(String name) {
        Scanner scannerString = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("in this id you can choce");
        roomList.forEach(elem -> {
            if (elem instanceof DoubleRoom) {
                System.out.print(elem.getId() + ",");
            }
        });
        System.out.println("please enter id");
        int answerId = scanner.nextInt();
        label:roomList.forEach(elem -> {
            if (elem.id == answerId) {
                LocalDate datCheckin = ReservInDay();
                System.out.println("print how days you want");
                int days = scanner.nextInt();
                while (days <=0) {
                    System.out.println("is not valid days");
                    System.out.println("please enter days");
                    days = scanner.nextInt();
                }
                if(elem.ReserveRoom(datCheckin,days,mapCustomer.get(name))){
                    System.out.println("Reserved is sucsesful");
                    System.out.println("do you want   see your  information enter yes ");
                    String answer = scannerString.nextLine();
                    if(answer.contains("yes")) {
                        PrintInfo(elem,name,days);
                    }else {
                        return;
                    }
                }
                else {
                    System.out.println("this room is resereved in days who you want ");
                    ReserveSecondType(name);
                    return;

                }
            }
        });

    }

    private void ReserveThirdType(String name) {
        Scanner scannerString = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("in this id you can choce");
        roomList.forEach(elem -> {
            if (elem instanceof DeLyux) {
                System.out.print(elem.getId() + ",");
            }
        });
        System.out.println("please enter id");
        int answerId = scanner.nextInt();
        label:roomList.forEach(elem -> {
            if (elem.id == answerId) {
                LocalDate datCheckin = ReservInDay();
                System.out.println("print how days you want");
                int days = scanner.nextInt();
                while (days <=0) {
                    System.out.println("is not valid days");
                    System.out.println("please enter days");
                    days = scanner.nextInt();
                }
                if(elem.ReserveRoom(datCheckin,days,mapCustomer.get(name))){
                    System.out.println("Reserved is sucsesful");
                    System.out.println("do you want   see your  information enter yes ");
                    String answer = scannerString.nextLine();
                    if(answer.contains("yes")) {
                        PrintInfo(elem,name,days);
                    }else {
                        return;
                    }
                }
                else {
                    System.out.println("this room is resereved in days who you want ");
                    ReserveThirdType(name);
                    return;

                }
            }
        });

    }

    private LocalDate ReservInDay() {
        LocalDate dateReserve = LocalDate.now();
        boolean isValidTime = false;
        while (isValidTime == false) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateNow = LocalDate.now();
            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter date in format yyyy-mm-dd");
            String dateStr = scanner.nextLine();
            try {
                dateReserve = LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                System.out.println("this  is not valid  date try again");
                return  ReservInDay();
            }
            if (dateReserve.isBefore(dateNow)) {
                System.out.println("is not valid date  try again");
            } else {isValidTime = true;

            }
        }
        return dateReserve;
    }
    private void PrintInfo(Room elem,String name,int days) {
        System.out.print("Dir " + name + " you are reserve in room for " +  elem.countBed + "peopel  is have bethroom,Tv,id is "  + elem.getId() + " " );
        if(elem instanceof  DeLyux) {
            System.out.print(",Siting area,Mini bare");
        }
        System.out.print("and her price is " + elem.Price);
        System.out.println("you are resrev in " + days + " and all price is "  + days*elem.Price);
    }
}
