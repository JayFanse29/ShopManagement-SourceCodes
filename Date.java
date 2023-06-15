public class Date {

public static boolean properDate(String dateTemp) {

// Scanner sc = new Scanner(System.in);
String str = dateTemp;
boolean a = false;
//  boolean decision;


//   System.out.print("\nEnter date (DD-MM-YYYY) of sale : ");
//   str = sc.nextLine();

if (str.charAt(2) == '-' && str.charAt(5) == '-' && str.length() == 10) {

String str1 = str.substring(0, 2);
String str2 = str.substring(3, 5);
String str3 = str.substring(6, 10);

int d, m, y;
d = Integer.parseInt(str1);
m = Integer.parseInt(str2);
y = Integer.parseInt(str3);

if (y % 4 == 0) { //leap year
    if (y % 100 == 0) {
    if (y % 400 == 0) {
    if (m >= 1 && m <= 12) {
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
        if (d >= 1 && d <= 31) {
        a = true;
        } else {
        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
        if (d >= 1 && d <= 30) {
        a = true;
        } else {
        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        } else if (m == 2) {
        if (d >= 1 && d <= 29) {
        a = true;
        } else {
        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        }
    }
    } else { //not leap year
    if (m >= 1 && m <= 12) {
    if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
        if (d >= 1 && d <= 31) {
        a = true;
        } else {

        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
        if (d >= 1 && d <= 30) {
        a = true;
        } else {

        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        } else if (m == 2) {
        if (d >= 1 && d <= 28) {
        a = true;
        } else {
        //          System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        }
    } else {
        //        System.out.println("\n\tEnter valid date.\n");
        a = false;
    }
}
} else {
if (m >= 1 && m <= 12) {
    if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
        if (d >= 1 && d <= 31) {
        a = true;
        } else {
        //         System.out.println("\n\tEnter valid date.\n");
        a = false;
    }
    } else if (m == 4 || m == 6 || m == 9 || m == 11) {
    if (d >= 1 && d <= 30) {
        a = true;
        } else {
        //         System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
    } else if (m == 2) {
    if (d >= 1 && d <= 29) {
        a = true;
        } else {
        //         System.out.println("\n\tEnter valid date.\n");
        a = false;
        }
        }
    }
    }
} else { //Not leap year
    if (m >= 1 && m <= 12) {
    if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
    if (d >= 1 && d <= 31) {
        a = true;
    } else {

//        System.out.println("\n\tEnter valid date.\n");
        a = false;
    }
    } else if (m == 4 || m == 6 || m == 9 || m == 11) {
    if (d >= 1 && d <= 30) {
    a = true;
    } else {

//        System.out.println("\n\tEnter valid date.\n");
        a = false;
    }
    } else if (m == 2) {
    if (d >= 1 && d <= 28) {
        a = true;
    } 
    else {
//        System.out.println("\n\tEnter valid date.\n");
        a = false;
    }
    }
    } else {
//      System.out.println("\n\tEnter valid date.\n");
    a = false;
    }
}
} else {
//    System.out.println("\n\tEnter valid date.\n");
a = false;
}

return a;
}
public boolean properMonth(String str)
{
//    Scanner sc = new Scanner(System.in);
//	String str = "";
	boolean a = false;

//	while(a == false){

//    System.out.print("\nEnter Month and Year (MM-YYYY) : ");
//    str = sc.nextLine();

	if(str.length() == 7){
		if(str.charAt(2) == '-')
		{
		
			String str1 = str.substring(0,2);
			String str2 = str.substring(3,7);
		
			int m,y;
			m = Integer.parseInt(str1);
			y = Integer.parseInt(str2);

			if(m>=1 && m<=12)
			{
				a=true;
			}
			else
			{
//			System.out.print("\nInvalid date!!");
//			continue;
				a=false;
			}
		}
		else
		{
//		System.out.print("\nInvalid date!!");
//		continue;
			a=false;
		}
	}
	else
	{
//	System.out.print("\nInvalid date!!");
//	continue;
		a=false;
	}
//	}
	return a;
}

public boolean properYear(String str)
{
//    Scanner sc = new Scanner(System.in);
//	String str = "";
	boolean a = false;

//	while(a == false){

//    System.out.print("\nEnter Year (YYYY) : ");
//    str = sc.next();

	if(str.length()==4)
	{
		a=true;
	}
	else
	{
//		System.out.print("\nInvalid year!!");
//		continue;
		a=false;
	}
//	}
	return a;
}
}