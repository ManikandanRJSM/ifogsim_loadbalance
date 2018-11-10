/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudsim.ext.gui;

import java.util.Random;

/**
 *
 * @author cloudsim
 */
public class Test {
    
    public static void main(String a[])
    {
               char[] chars = "x+".toCharArray();
StringBuilder sb = new StringBuilder();
StringBuilder th = new StringBuilder();
Random random = new Random();
//Genreated  binary
        int val=8552;      
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
          
          
  //=======================================sendetr=========================================     
        String eq1=gen1;
        String eq2=gen2;
         String eq3=gr;
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
    
    }
}
