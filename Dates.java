import java.util.*;

public class Dates {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the day of week of 1st January with numbers(1-7): ");
        int startDay = input.nextInt();
        System.out.println("Options:");
        System.out.println("1 - Date correctness check");
        System.out.println("2 - Format data");
        System.out.println("3 - By date to determine the day of the week");
        System.out.println("4 - By description with a day of the week to determine the date");
        System.out.println("5 - Prints a calendar for a given month");
        System.out.print("Enter option: ");
        int option = input.nextInt();
        switch (option) {
            case 1:
                System.out.print("Enter date(dd/mm/yy): ");
                input.nextLine();
                System.out.println(isValidDate(input.nextLine()) ? "The date is valid!" : "The date is not valid!");
                break;
            case 2:
                System.out.print("Enter day: ");
                int day = input.nextInt();
                System.out.print("Enter month: ");
                int month = input.nextInt();
                System.out.print("Enter year: ");
                int year = input.nextInt();
                if (dateValidation(day, month, year)) {
                    System.out.println("Enter format type:" + "\n1: dd/MM/yyyy " + "\n2: MM/dd/yyyy" + "\n3: dd-MMM-yyyy");
                    int type = input.nextInt();
                    System.out.println(formatDate(day, month, year, type));
                } else {
                    System.out.println("Invalid date!");
                }
                break;
            case 3:
                System.out.print("Enter date(29/02): ");
                String date = input.next();
                dateToDayOfWeek(startDay, date);
                break;
            case 4:
                System.out.print("Enter the description of the date (second Monday of January): ");
                input.nextLine();
                String description = input.nextLine();
                printDateByDescription(startDay, description);
                break;
            case 5:
                input.nextLine();
                System.out.print("Enter month: ");
                printCalendar(startDay, input.nextLine());
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    //option 1
    public static boolean isValidDate(String date) {
        String[] dateParts = date.split("/");
        if (dateParts.length != 3)
            return false;
        int day, month, year;
        try {
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return dateValidation(day, month, year);
    }

    //option 2
    public static boolean dateValidation(int day, int month, int year) {
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (1 <= day && day <= 31)) {
            return true;
        } else if ((month <= 12 && month != 2) && (1 <= day && day <= 30)) {
            return true;
        } else if (month == 2) {
            if ((year % 4 == 0) && (1 <= day && day <= 29)) {
                return true;
            } else if (1 <= day && day <= 28) {
                return true;
            }
        }
        return false;
    }

    public static String formatDate(int day, int month, int year, int type) {
        switch (type) {
            case 1:
                return day + "/" + month + "/" + year;
            case 2:
                return month + "/" + day + "/" + year;
            case 3:
                String text = day + "-";
                switch (month) {
                    case 1:
                        text += "Jan";
                        break;
                    case 2:
                        text += "Feb";
                        break;
                    case 3:
                        text += "Mar";
                        break;
                    case 4:
                        text += "Apr";
                        break;
                    case 5:
                        text += "May";
                        break;
                    case 6:
                        text += "Jun";
                        break;
                    case 7:
                        text += "Jul";
                        break;
                    case 8:
                        text += "Aug";
                        break;
                    case 9:
                        text += "Sep";
                        break;
                    case 10:
                        text += "Oct";
                        break;
                    case 11:
                        text += "Nov";
                        break;
                    case 12:
                        text += "Dec";
                        break;
                }
                return text + "-" + year;
            default:
                return "Invalid format";
        }
    }

    //option 3
    public static void dateToDayOfWeek(int startDay, String date) {
        startDay--;
        String[] dateParts = date.split("/");
        int month = Integer.valueOf(dateParts[1]);
        int days = Integer.valueOf(dateParts[0]) + getDaysToTheMonth(month) - getMonthDays(month) + startDay;
        System.out.println(getDayOfWeek(days % 7));
    }

    public static String getDayOfWeek(int day) {
        String dayOfWeek = "";
        switch (day) {
            case 1:
                dayOfWeek = "Monday";
                break;
            case 2:
                dayOfWeek = "Tuesday";
                break;
            case 3:
                dayOfWeek = "Wednesday";
                break;
            case 4:
                dayOfWeek = "Thursday";
                break;
            case 5:
                dayOfWeek = "Friday";
                break;
            case 6:
                dayOfWeek = "Saturday";
                break;
            case 0:
                dayOfWeek = "Sunday";
                break;
        }
        return dayOfWeek;
    }

    public static int getDaysToTheMonth(int month) {
        int days = 0;
        switch (month) {
            case 12:
                days += 31;
            case 11:
                days += 30;
            case 10:
                days += 31;
            case 9:
                days += 30;
            case 8:
                days += 31;
            case 7:
                days += 31;
            case 6:
                days += 30;
            case 5:
                days += 31;
            case 4:
                days += 30;
            case 3:
                days += 31;
            case 2:
                days += 29;
            case 1:
                days += 31;
        }
        return days;
    }

    public static int getMonthDays(int month) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                return 29;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
        }
        return 0;
    }

    //option 4
    public static void printDateByDescription(int startDay, String description) {
        String[] textParts = description.split(" ");
        int numberOfDay = getTheNumberOfDay(textParts[0]);
        int dayOfWeek = getDayOfWeek(textParts[1]);
        int monthDays = getMonthDays(textParts[textParts.length - 1]);
        int daysToTheMonth = getDaysToTheMonth(textParts[textParts.length - 1]) - monthDays + startDay - 1;
        int firstWeekDay = daysToTheMonth % 7;
        int currentDay = 1;
        boolean isFoundDay = false;
        int countDayRepeat = 0;
        for (int i = 1; i <= monthDays + firstWeekDay; i++) {
            if (i > firstWeekDay) {
                if (i % 7 == dayOfWeek) {
                    countDayRepeat++;
                }
                if (i % 7 == dayOfWeek && countDayRepeat == numberOfDay) {
                    isFoundDay = true;
                    break;
                }
                currentDay++;
            }
        }
        if (isFoundDay) {
            System.out.print((currentDay < 10 ? "0" + currentDay : currentDay) + "/" + (monthToNumber(textParts[textParts.length - 1]) < 10 ? "0" + monthToNumber(textParts[textParts.length - 1]) : monthToNumber(textParts[textParts.length - 1])));
        } else {
            System.out.println("The date is not found");
        }
    }

    public static int getDayOfWeek(String day) {
        switch (day.toLowerCase()) {
            case "monday":
                return 1;
            case "tuesday":
                return 2;
            case "wednesday":
                return 3;
            case "thursday":
                return 4;
            case "friday":
                return 5;
            case "saturday":
                return 6;
            case "sunday":
                return 0;
        }
        return 7;
    }

    public static int getTheNumberOfDay(String numberOfDay) {
        switch (numberOfDay.toLowerCase()) {
            case "first":
                return 1;
            case "second":
                return 2;
            case "third":
                return 3;
            case "fourth":
                return 4;
            case "fifth":
                return 5;
            case "last":
                return 6;
        }
        return 0;
    }

    public static int getMonthDays(String month) {
        switch (month.toLowerCase()) {
            case "january":
                return 31;
            case "february":
                return 29;
            case "march":
                return 31;
            case "april":
                return 30;
            case "may":
                return 31;
            case "june":
                return 30;
            case "july":
                return 31;
            case "august":
                return 31;
            case "september":
                return 30;
            case "october":
                return 31;
            case "november":
                return 30;
            case "december":
                return 31;
        }
        return 0;
    }

    public static int getDaysToTheMonth(String month) {
        int days = 0;
        switch (month.toLowerCase()) {
            case "december":
                days += 31;
            case "november":
                days += 30;
            case "october":
                days += 31;
            case "september":
                days += 30;
            case "august":
                days += 31;
            case "july":
                days += 31;
            case "june":
                days += 30;
            case "may":
                days += 31;
            case "april":
                days += 30;
            case "march":
                days += 31;
            case "february":
                days += 29;
            case "january":
                days += 31;
        }
        return days;
    }

    public static int monthToNumber(String month) {
        int monthNum = 0;
        switch (month.toLowerCase()) {
            case "january":
                monthNum = 1;
                break;
            case "february":
                monthNum = 2;
                break;
            case "march":
                monthNum = 3;
                break;
            case "april":
                monthNum = 4;
                break;
            case "may":
                monthNum = 5;
                break;
            case "june":
                monthNum = 6;
                break;
            case "july":
                monthNum = 7;
                break;
            case "august":
                monthNum = 8;
                break;
            case "september":
                monthNum = 9;
                break;
            case "octomber":
                monthNum = 10;
                break;
            case "november":
                monthNum = 11;
                break;
            case "december":
                monthNum = 12;
                break;
        }
        return monthNum;
    }

    public static void printCalendar(int startDay, String month) {
        int monthDays = getMonthDays(month);
        int daysToTheMonth = getDaysToTheMonth(month) - monthDays + startDay - 1;
        int firstWeekDay = daysToTheMonth % 7;
        int currentDay = 1;
        for (int i = 1; i <= monthDays + firstWeekDay; i++) {
            if (i <= firstWeekDay) {
                System.out.print("   ");
            } else {
                if (currentDay < 10) {
                    System.out.print("0" + currentDay + " ");
                } else {
                    System.out.print(currentDay + " ");
                }
                currentDay++;
            }
            if (i % 7 == 0) {
                System.out.println();
            }
        }
    }

}
