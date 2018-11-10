/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudsim.ext.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author cloudsim
 */
public class Sender {
    
    public static  void SenderAccess(String uname,int val)
    {
                 char[] chars = "x+".toCharArray();
StringBuilder sb = new StringBuilder();
StringBuilder th = new StringBuilder();
Random random = new Random();
//Genreated  binary
      
       String s=Integer.toBinaryString(val);
       
 //================================================================================================      
//Genreated  randomlly
 int len=s.length();
for (int i = 0; i < len; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
}
String output = sb.toString();

String gen2=s;
String gen1=output;
  //      System.err.println("(1)--->"+gen1);
//System.err.println("(2)---->"+gen2);
//================================================================================================
for(int i=0;i<len;i++)
{
String fs=gen2.substring(i, i+1);
String ss=gen1.substring(i, i+1);

if((fs.equalsIgnoreCase("1")) &&(ss.equalsIgnoreCase("+")))
{
  // System.out.println(ss+"-"+fs+"--> 1");
   th.append("1");
}
else if((fs.equalsIgnoreCase("0")) &&(ss.equalsIgnoreCase("+")))
{
 //  System.out.println(ss+"-"+fs+"--> -");
     th.append("-");
}
   
else if((fs.equalsIgnoreCase("0")) &&(ss.equalsIgnoreCase("x")))
{
  // System.out.println(ss+"-"+fs+"--> \\");
     th.append("\\");
}
else if((fs.equalsIgnoreCase("1")) &&(ss.equalsIgnoreCase("x")))
{
  // System.out.println(ss+"-"+fs+"--> /");
     th.append("/");
}
}


//===========================================================================

String gen3 = th.toString();
gen2=s;
gen1=output;

        System.out.println(""+gen1);
         System.out.println(""+gen2);
           System.out.println("----------------------");           
             System.out.println(""+gen3);

//==========================================================================================
       System.out.println("============= Sender completed=========================================\n");
int sl=gen3.length();
StringBuilder genr = new StringBuilder();
for (int i = 0; i < sl; i++) {
    char c = chars[random.nextInt(chars.length)];
    genr.append(c);
}

String gr = genr.toString();
StringBuilder sendres = new StringBuilder();
        System.out.println(""+gr);
          System.out.println(""+gen3);
    
    
   //=============================================================
          
        System.out.println("====================Reciever completed======================");     
          for(int i=0;i<sl;i++)
{
String fs=gr.substring(i, i+1);
String ss=gen3.substring(i, i+1);

if((fs.equalsIgnoreCase("x")) &&(ss.equalsIgnoreCase("/")))
{
//   System.out.println(ss+"-"+fs+"--> 1");
   sendres.append("1");
  
}
else if((fs.equalsIgnoreCase("+")) &&(ss.equalsIgnoreCase("1")))
{
//  System.out.println(ss+"-"+fs+"--> 1");
    sendres.append("1");
   
}   
else 
{
// System.out.println(ss+"-"+fs+"--> *");
   sendres.append("*");

}

}
          String sr = sendres.toString();
        System.out.println(""+sr);  
        
        
         String eq1=gen1;
        String eq2=gen2;
         String eq3=gr;
         
         String msg=uname+"#"+val+"#"+eq1+"#"+eq2+"#"+eq3+"#"+sr;
         keytask(msg);
    }
    
    
 public static void keytask(String t) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sender.txt", true));
            bw.write(t + "\n");
            bw.close();
        } catch (Exception ex) {
        }

    }    
 
 
 public static  boolean RecieverAccess(String uname,int val)
    {
        
        boolean rr=false;
     String s=  getSenderValue(uname);
          String sss[]=s.split("#");
             String eq1=sss[2];
        String eq2=Integer.toBinaryString(val);
         String eq3=sss[4];
        System.out.println(""+eq1);  
        System.out.println(""+eq2);  
        System.out.println(""+eq3);  
        System.out.println("====================sender completed================\n");  
  StringBuilder r = new StringBuilder();       
         int tl=eq1.length();
            for(int i=0;i<tl;i++)
{
String fs=eq1.substring(i, i+1);
String ss=eq3.substring(i, i+1);
String ts=eq2.substring(i, i+1);
if((fs.equalsIgnoreCase("x")) &&(ss.equalsIgnoreCase("x")) && (ts.equalsIgnoreCase("1")) )
{
  // System.out.println(ss+"-"+fs+"--> 1");
   r.append("1");
  
}
else if((fs.equalsIgnoreCase("+")) &&(ss.equalsIgnoreCase("+")) && (ts.equalsIgnoreCase("1")) )
{
//  System.out.println(ss+"-"+fs+"--> 1");
    r.append("1");
   
}   
else 
{
// System.out.println(ss+"-"+fs+"--> *");
   r.append("*");

}

}
            
              String res = r.toString();
        System.out.println(""+res); 
        
        String ch=sss[5];
          System.out.println(ch+"="+res); 
       if(ch.equalsIgnoreCase(res))
       {
           JOptionPane.showMessageDialog(null, "Login success");
           rr=true;
       }else
       {
           JOptionPane.showMessageDialog(null, "incorrect user name or password! try again");
       }
    
    return rr;
    }
 
 
 public static String getSenderValue(String u)
 {
     String data="";
      try {
            int c = 0;
           
            String line = "", s="";
            BufferedReader br = new BufferedReader(new FileReader("sender.txt"));
            while ((line = br.readLine()) != null) {
               s=line;
               
               String ss[]=s.split("#");
               
              String uname=ss[0];
              String key=ss[1];
             if(uname.equalsIgnoreCase(u) )
             {
                 data=s;
             }

            }
 }catch(Exception ex){}
      return data;
 }
 
 public static boolean chekSender(String u, String k)
 {
     boolean data=false;
      try {
            int c = 0;
           
            String line = "", s="";
            BufferedReader br = new BufferedReader(new FileReader("sender.txt"));
            while ((line = br.readLine()) != null) {
               s=line;
               
               String ss[]=s.split("#");
               
              String uname=ss[0];
              String key=ss[1];
             if(uname.equalsIgnoreCase(u) && key.equalsIgnoreCase(k))
             {
                 data=true;
             }

            }
 }catch(Exception ex){}
      return data;
 }
 
}
