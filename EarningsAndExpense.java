
import java.io.*;

public class EarningsAndExpense
{

    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    File ee=new File("C:\\ShopManagement\\earnings_expense");
   
    public void earnings(String mm,float earning)
    {
        try
        {
        
        ee.mkdir();

        float erngs=0.00f,preverngs=0.00f;
        File M=new File(ee+"\\"+mm);
        M.mkdir();
        File en=new File(M+"\\earnings.txt");
        if(en.exists())
        {
            fr=new FileReader(en);
            br=new BufferedReader(fr);
            preverngs=Float.parseFloat(br.readLine());
            fr.close();
        }
        erngs=earning+preverngs;
        fw=new FileWriter(en);
        fw.write(""+erngs);
        fw.close();
        }
        catch(Exception e)
        {
            System.out.print("\nError : "+e.getMessage());
        }
    }

    public void expense(String mm,float expense)
    {
        try
        {
            // File mk=new File("earnings_expense");
            ee.mkdir();

            float expns=0.00f,prevexpns=0.00f;
            File M=new File(ee+"\\"+mm);
            M.mkdir();
            File ep=new File(M+"\\expense.txt");
            if(ep.exists())
            {
                fr=new FileReader(ep);
                br=new BufferedReader(fr);
                prevexpns=Float.parseFloat(br.readLine());
                fr.close();
            }
            expns=expense+prevexpns;
            fw=new FileWriter(ep);
            fw.write(""+expns);
            fw.close();
  
        }
        catch(Exception e)
        {
            System.out.print("\nError : "+e.getMessage());
        }
    }
}