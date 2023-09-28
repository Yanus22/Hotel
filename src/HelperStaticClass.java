import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HelperStaticClass {

    public static void PrintInfo(Room elem, String name, int days) {
        System.out.print("Dir " + name + " you are reserve in room for " + elem.countBed + "peopel  is have bethroom,Tv,id is " + elem.getId() + " ");
        if (elem instanceof DeLyux) {
            System.out.print(",Siting area,Mini bare");
        }
        System.out.print("and her price is " + elem.Price);
        System.out.println("you are resrev in " + days + " and all price is " + days * elem.Price);
    }


    public static int TypeGenerator() {
        int result = -1;
        System.out.println("please enter Type");
        Scanner scanner = new Scanner(System.in);
        String answerIdStr = scanner.nextLine().replaceAll(" ", "");
        while (!(answerIdStr.equals("1") || answerIdStr.equals("2") || answerIdStr.equals("3"))) {
            System.out.println("this is invalid Type  please enter again");
            answerIdStr= scanner.nextLine();
        }
        if (answerIdStr.equals("1")) {
            result = 1;
        }
        if (answerIdStr.equals("2")) {
            result = 2;
        }
        if (answerIdStr.equals("3")) {
            result = 3;
        }
        return result;
    }

    public static int IdGenerAtor() {
        System.out.println("please enter Id");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        int result;
        try {
            result = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            System.out.println("this is not valid number");
            return IdGenerAtor();
        }
        return result;
    }

    public static int DayGenerator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter how day you want reserve");
        int result = -1;
        try {
            String answerDay = scanner.nextLine().replaceAll(" ", "");
            result = Integer.parseInt(answerDay);
        } catch (NumberFormatException e) {
            System.out.println("this is not valid answer  try again");
            return DayGenerator();
        }
        if (result <= 0) {
            System.out.println("day not can be negativ");
            return DayGenerator();
        }
        return result;
    }

    public static LocalDate DateGenerator() {
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
            } catch (DateTimeParseException e) {
                System.out.println("this is not valid type");
                return DateGenerator();
            }
            if (dateReserve.isBefore(dateNow)) {
                System.out.println("date not can be before in now");
                return DateGenerator();

            } else {
                isValidTime = true;
            }
        }
        return dateReserve;
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

}
