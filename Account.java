
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Account
{
    static Scanner sc=new Scanner(System.in);
   // PrintWriter System.out=new PrintWriter(System.out,true);
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
	String name,pass;
    EarningsAndExpense e1=new EarningsAndExpense();

    File sm=new File("C:\\ShopManagement");
    File pro = new File("C:\\ShopManagement\\products");
    File acc=new File("C:\\ShopManagement\\Accounts");
    
    
    public void sell(String productName,int units,String mm) throws IOException
    {
        try
        {
            File F1=new File(""+acc);
            String name[]=F1.list();
            F1=new File(acc+"\\" + name[0] + "\\balance.txt");
            File F2=new File(pro+"\\" + productName + "\\Selling_price.txt");

            float crrntBal,income;

            fr=new FileReader(F1);
            Scanner rd=new Scanner(fr);
            crrntBal=rd.nextFloat(); rd.close();
            fr=new FileReader(F2);
            rd=new Scanner(fr);
            income=units * rd.nextFloat(); rd.close();
            crrntBal+=income;

            e1.earnings(mm,income);
            System.out.print("\nTotal amount of sale : "+income);
            System.out.print("\nCurrent balance : "+crrntBal);
            fw=new FileWriter(F1);
            fw.write("" + crrntBal); fw.close();
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e.getMessage());
        }    
    }

    public boolean stock(String productName, int units, String mm) throws IOException
    {
        try
        {
            boolean decision=false;
            float totalCost=0.00f;
            while(true)
            {
            File AF=new File(""+acc);
            String nm[]=AF.list();
            File F1=new File(acc+"\\"+nm[0]+"\\balance.txt");   
            File F2=new File(pro+"\\"+productName+"\\Cost_price.txt");
            
            float crrntBal=0.00f;
            
            fr=new FileReader(F1);
            br=new BufferedReader(fr);
            // Scanner rd=new Scanner(fr);
            // crrntBal=rd.nextFloat();
            crrntBal=Float.parseFloat(br.readLine());
            // rd.close();
            fr.close();
            fr=new FileReader(F2);
            br=new BufferedReader(fr);
            // rd=new Scanner(fr);
            // totalCost=units * rd.nextFloat();
            totalCost=units*(Float.parseFloat(br.readLine()));
            // rd.close();
            fr.close();
            
           
            if(totalCost<=crrntBal)
            {
            	int confirm=JOptionPane.showConfirmDialog(null,"Total cost of this re-stock is :"+totalCost+
            										"\nYour balance is :" + crrntBal +
            										"\nDo you want to confirm purchase ?", "Confirm Purchase?", JOptionPane.YES_NO_OPTION);
//                System.out.print("Total cost of this re-stock is :" + totalCost +"\n");
//                System.out.println("Your balance is :" + crrntBal);
//                System.out.print("Do you want to confirm purchase ? (y/n) :");
//                char yn=sc.next().charAt(0);

                if(confirm==0)
                {
                    fw=new FileWriter(F1);
                    decision=true;
                    crrntBal=crrntBal-totalCost;
                    fw.write(""+crrntBal); 
                    fw.close();
//                    System.out.println("Purchase Confirmed..");
                }
                else if(confirm==1)
                {
                    // fw=new FileWriter(F1);
                    // fw.write(""+crrntBal); 
                    // fw.close();
                    decision=false;
                    JOptionPane.showMessageDialog(null, "Purchase cancelled!", "Purchase cancelled", JOptionPane.WARNING_MESSAGE);
        			
//                    System.out.println("Purchase aborted..");
                }
               
            }
            else 
            {
                // fw=new FileWriter(F1);
                // fw.write(""+crrntBal); 
                // fw.close();    
            	JOptionPane.showMessageDialog(null,"Total cost of this re-stock is :"+totalCost+
						"\nYour balance is :" + crrntBal+
						"\nPurchase cancelled", "Insufficient funds", JOptionPane.WARNING_MESSAGE);
//                System.out.print("Total cost of this re-stock is :" + totalCost +"\n");
//                System.out.println("Your balance is :" + crrntBal);
//                System.out.println("Insufficient Funds.\nPurchase aborted..");
                decision=false;
            }
            // fw.close();
            break;
            }
            if(decision==true)
            {
                e1.expense(mm,totalCost);
            }
            return decision;
        }
        catch(Exception e){
            System.out.println("Error :"+e.getMessage());
            return false;
        }
    }

    void changePass()
    {
	    try
	    {
        while(true)
        {
            System.out.println("Enter current password: ");
            pass=sc.next();
            fr=new FileReader(acc+"\\"+name+"\\password.txt");
            BufferedReader br = new BufferedReader(fr);
            String accpass=br.readLine();
            fr.close();
            if(accpass.equals(pass)==false)
            {
                System.out.println("Invalid password...");
                continue;
            }
            break;
        }
        System.out.println("Enter new password: ");
        String newpass=sc.next();
        fw=new FileWriter(acc+"\\"+name+"\\password.txt");
        fw.write(newpass);
        fw.close();
        System.out.println("Password changed successfully!!");
	    }
	    catch(Exception e)
	    {
    	    System.out.println("Error: "+e.getMessage());
	    }    
    }
}