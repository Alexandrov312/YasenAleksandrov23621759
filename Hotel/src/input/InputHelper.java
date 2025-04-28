package input;

import model.Activity;
import model.Date;
import model.Hotel;
import model.View;

import java.util.Scanner;

public class InputHelper {
    public static Scanner input = new Scanner(System.in);
    private static String temp;

    public static Date enterDate(String prompt){
        do {
            System.out.print(prompt + "(example: 2005/01/25): ");
            temp = input.nextLine();
            Date date = ValidateInput.isValidDate(temp);
            if (date != null) return date;
            else System.out.println("You didn't enter a valid date");
        } while (true);
    }

    public static String enterPersonalNumber(String prompt){
        String personalNumber;
        do {
            System.out.print(prompt);
            personalNumber = input.nextLine();

            if(personalNumber.length() == 10 && ValidateInput.isNumeric(personalNumber))
                break;
            else
                System.out.println("Invalid personal number!");

        }while(true);
        return personalNumber;
    }

    public static int enterInteger(String prompt){
        do {
            System.out.print(prompt);
            temp = input.nextLine();
            if (!ValidateInput.isNumeric(temp)) {
                System.out.println("Invalid input!");
                continue;
            }
            return Integer.parseInt(temp);
        }while(true);
    }

    public static View enterView(){
        do{
            int option = enterInteger("Enter view type:\n1) Side sea view\n2) Direct sea view\nOption: ");
            if(option == 1) {
                return View.SIDE_SEA_VIEW;
            }
            else if(option == 2) {
                return View.DIRECT_SEA_VIEW;
            }
            else {
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);
    }

    public static boolean confirm(String prompt){
        do{
            System.out.print(prompt + "(Y/N):");
            temp = input.nextLine();
            if(!temp.isEmpty() && Character.toLowerCase(temp.charAt(0)) == 'y'){
                return true;
            }
            else if(!temp.isEmpty() && Character.toLowerCase(temp.charAt(0)) == 'n'){
                return false;
            }
            else{
                System.out.println("You didn't enter a valid option!");
            }
        }while(true);
    }

    public static String enterTime(String prompt){
        String time = "";
        do{
            System.out.print(prompt + " (example: 16:00): ");
            temp = input.nextLine();

            time = ValidateInput.isValidTime(temp);
            if(!time.isEmpty())
                return time;
            else
                System.out.println("Invalid time!");
        }while(true);
    }

    public static Activity findActivityById(int id){
        for(Activity activity : Hotel.getInstance().getActivityService().getActivities()){
            if(activity.getId() == id){
                return activity;
            }
        }
        return null;
    }

    public static Date enterFutureDate(String prompt) {
        Date date;
        do {
            date = InputHelper.enterDate(prompt);
            if (ValidateInput.isTodayAfterDate(date)) {
                System.out.println("You can't enter an old date!");
            }
        } while (ValidateInput.isTodayAfterDate(date));
        return date;
    }

    public static Date enterEndDateAfterStartDate(Date startDate, String prompt) {
        Date endDate;
        do {
            endDate = InputHelper.enterDate(prompt);
            if (endDate.isBefore(startDate)) {
                System.out.println("The end date can't be before the start date!");
            }
        } while (endDate.isBefore(startDate));
        return endDate;
    }
}
