import java.util.Scanner;
import java.io.IOException;

public class Time {

  public static boolean DEBUGMODE = false;
  public static int amp = 1;
  public static long delay;
  public static int sec = 0;
  public static String secString;
  public static int min = 0;
  public static String minString;
  public static int hour = 0;
  public static String hourString;
  public static boolean AM = true;
  public static String phase = "AM";
  public static int phaseInt = 1;
  public static int day = 1;
  public static int dayMeta = 1;
  public static String dayString = "Monday";
  public static int month = 1;
  public static String monthString = "January";
  public static int year = 1;

  public Time(boolean show, int amplifier) {
    if(DEBUGMODE) {
      System.out.println("[System/Debug] Time Object created!");
    }
    try {
      runTime(show, amplifier);
    } catch (IOException e) {
      System.out.println("[System/Error] I-O failed!");
    } catch (InterruptedException e) {
      System.out.println("[System/Error] Interrupted Exception occured!");
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {

    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    System.out.println("Welcome to the Time Project!");
    System.out.println("Made by NaV - 23.11.2018");

    try {
      Thread.sleep(3000);
    } catch(InterruptedException ex) {
      System.out.println("Delay Malfunction");
    }

    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Please input the time amplifier");
    amp = scanner.nextInt();

    if(DEBUGMODE) {
      System.out.println("[System/Debug] Starting Time!");
    }

    try {
      runTime(true, amp);
    } catch (IOException e) {
      System.out.println("[System/Error] IO Fail");
    } catch (InterruptedException e) {
      System.out.println("[System/Error] I Fail");
    }
  }

  public static void runTime(boolean show, int amplifier) throws IOException, InterruptedException {

    new ProcessBuilder("cmd", "/c", "@echo off").inheritIO().start().waitFor();
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    if(DEBUGMODE) {
      System.out.println("[System/Debug] Starting Loop!");
    }
    //Loop
    while(true) {

      //Phase Counter
      if(AM) {
        phaseInt = 1;
      } else if (!AM) {
        phaseInt = 2;
      }

      //Time Engine
      if(sec < 59) {
        sec++;
      } else if (sec == 59) {
        sec = 0;
        if(min < 59) {
          min++;
        } else if (min == 59) {
          min = 0;
          if(hour < 11) {
            hour++;
          } else if (hour == 11) {
            hour++;
            if(AM) {
              AM = false;
            } else if (!AM) {
              AM = true;
            }

            if (AM) {
              hour = 1;

              if(dayMeta != 7) {
                dayMeta++;
              } else if (dayMeta == 7) {
                dayMeta = 1;
              }

              if(month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11) {
                if(day < 31) {
                  day++;
                } else if (day == 31) {
                  day = 1;
                  month++;
                }
              } else if(month == 4 || month == 6 || month == 8 || month == 10 || month == 12) {
                if(day < 30) {
                  day++;
                } else if (day == 30) {
                  day = 1;
                  if(month != 12) {
                    month++;
                  } else if (month == 12) {
                    month = 1;
                    year++;
                  }
                }
              } else if (month == 2) {
                if(year%4==0) {
                  if (day < 29) {
                    day++;
                  } else if (day == 29) {
                    day = 1;
                    month++;
                  }
                } else if (year%4!=0) {
                  if (day < 28) {
                    day++;
                  } else if (day == 28) {
                    day = 1;
                    month++;
                  }
                }
              }
            }
          } else if (hour == 12) {
            hour = 0;
          }
        }
      }

      //Phase Namer
      switch(phaseInt) {
        case 1:
          phase = "AM";
          break;
        case 2:
          phase = "PM";
          break;
        default:
          System.out.println("[System/Error] Missing Initialization");
          break;
      }
      //Month Namer
      switch(month) {
        case 1:
          monthString = "January";
          break;
        case 2:
          monthString = "February";
          break;
        case 3:
          monthString = "March";
          break;
        case 4:
          monthString = "April";
          break;
        case 5:
          monthString = "May";
          break;
        case 6:
          monthString = "June";
          break;
        case 7:
          monthString = "July";
          break;
        case 8:
          monthString = "August";
          break;
        case 9:
          monthString = "September";
          break;
        case 10:
          monthString = "October";
          break;
        case 11:
          monthString = "November";
          break;
        case 12:
          monthString = "December";
          break;
        default:
          System.out.println("[System/Error] Missing Initialization");
          break;
      }
      //Day Namer
      switch(dayMeta) {
        case 1:
          dayString = "Monday";
          break;
        case 2:
          dayString = "Tuesday";
          break;
        case 3:
          dayString = "Wednesday";
          break;
        case 4:
          dayString = "Thursday";
          break;
        case 5:
          dayString = "Friday";
          break;
        case 6:
          dayString = "Saturday";
          break;
        case 7:
          dayString = "Sunday";
          break;
        default:
          System.out.println("[System/Error] Missing Initialization");
          break;
      }


      //ZotC 1.0 - Zero On The Clock 1.0
      if(sec < 10) {
        secString = "0" + String.valueOf(sec);
      } else if(sec >= 10) {
        secString = String.valueOf(sec);
      }

      if(min < 10) {
        minString = "0" + String.valueOf(min);
      } else if(min >= 10) {
        minString = String.valueOf(min);
      }

      if(hour < 10) {
        hourString = "0" + String.valueOf(hour);
      } else if(sec >= 10) {
        hourString = String.valueOf(hour);
      }

      if(show) {
        //Message
        System.out.println("Today is " + dayString + ", the " + day + " day of " + monthString + " in year " + year + ". The time is: " + hourString + ":" + minString + ":" + secString + " " + phase + ".");
      }

      //Delay
      delay = Long.parseLong(String.valueOf(Math.round(1000/amplifier)));
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        System.out.println("[System/Error] Delay Malfunction");
      }
      if(show) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }

    }

  }

  public static String[] getTimeData() {
    String[] Data = {secString, minString, hourString, String.valueOf(phaseInt), String.valueOf(day), String.valueOf(dayMeta), String.valueOf(month), String.valueOf(year), String.valueOf(String.valueOf(amp))};
    return Data;
  }

  public static String parseDay() {

    int meta = Integer.parseInt(getTimeData()[5]);

    String StringDay = "Default";

    switch(meta) {
      case 1:
        StringDay = "Monday";
        break;
      case 2:
        StringDay = "Tuesday";
        break;
      case 3:
        StringDay = "Wednesday";
        break;
      case 4:
        StringDay = "Thursday";
        break;
      case 5:
        StringDay = "Friday";
        break;
      case 6:
        StringDay = "Saturday";
        break;
      case 7:
        StringDay = "Sunday";
        break;
      default:
        System.out.println("[System/Error] Number out of Range");
        break;
    }

    return StringDay;
  }

  public static String parseMonth() {

    int meta = Integer.parseInt(getTimeData()[6
    ]);

    String StringMonth = "Default";

    switch(meta) {
      case 1:
        StringMonth = "January";
        break;
      case 2:
        StringMonth = "February";
        break;
      case 3:
        StringMonth = "March";
        break;
      case 4:
        StringMonth = "April";
        break;
      case 5:
        StringMonth = "May";
        break;
      case 6:
        StringMonth = "June";
        break;
      case 7:
        StringMonth = "July";
        break;
      case 8:
        StringMonth = "August";
        break;
      case 9:
        StringMonth = "September";
        break;
      case 10:
        StringMonth = "October";
        break;
      case 11:
        StringMonth = "November";
        break;
      case 12:
        StringMonth = "December";
        break;
      default:
        System.out.println("[System/Error] Number out of Range");
        break;
    }

    return StringMonth;
  }

  public static String parsePhase() {
    int meta = Integer.parseInt(getTimeData()[3]);
    String res = "AM";

    if(meta == 1) {
      res = "AM";
    } else if (meta == 2) {
      res = "PM";
    }

    return res;
  }

}
