import java.util.Scanner;

public class UserInput {
    public static Guest enterGuest(int roomNumber){
        String firstName;
        String lastName;

        Date birthDate;
        int year = 0;
        int month = 0;
        int day = 0;

        String personalNumber;
        String country;

        Scanner input = new Scanner(System.in);

        System.out.println("First name: ");
        firstName = input.nextLine();
        System.out.println("Last name: ");
        lastName = input.nextLine();

        boolean flag = true;
        do {
            System.out.println("Birth date (example: 2005/01/25): ");
            String date = input.nextLine();
            for (int i = 0; i < 3; i++) {
                String temp = "";
                for (int j = 0; j < date.length(); j++) {
                    if (date.charAt(j) == '/')
                        break;
                    temp += date.charAt(j);
                }
                if(!isNumeric(temp)) {
                    System.out.println("Invalid date!");
                    break;
                }

                if (i == 0) {
                    year = Integer.parseInt(temp);
                    temp += "/";
                    date = date.replace(temp, "");
                } else if (i == 1) {
                    month = Integer.parseInt(temp);
                    temp += "/";
                    date = date.replace(temp, "");
                } else {
                    day = Integer.parseInt(temp);
                }
            }
            flag = Date.isValidBirthDate(year, month, day);
        }while(flag);
        birthDate = new Date(year, month, day);

        flag = true;
        do {
            System.out.println("Personal number: ");
            personalNumber = input.nextLine();

            if(personalNumber.length() == 10 && isNumeric(personalNumber))
                flag = false;
            else
                System.out.println("Invalid personal number!");

        }while(flag);

        System.out.println("Country: ");
        country = input.nextLine();


        return new Guest(firstName, lastName, birthDate, personalNumber, country, roomNumber);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
