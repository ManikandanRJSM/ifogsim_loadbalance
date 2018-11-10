/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudsim;
import java.io.*;
/**
 *
 * @author star
 */
public class Greedy {
    public static void test() throws IOException  
    {  
        int i,j=0,max_qty,m,n;  
        float sum=0,max;  
                   BufferedReader cin= new BufferedReader (new InputStreamReader(System.in));  
        int array[][]=new int[2][20];  
        System.out.println("enter no of items");  
        n=Integer.parseInt(cin.readLine());  
        for(i=0;i<n;i++)  
        {  
            System.out.println("enter maximum volume of item no " + (i+1) + " is : ");  
            array[0][i]=Integer.parseInt(cin.readLine());  
        }  
        for(i=0;i<n;i++)  
        {  
            System.out.println("enter total profit of item no " + (i+1) + " is : ");  
            array[1][i]=Integer.parseInt(cin.readLine());  
        }  
        System.out.println("enter maximum volume of knapsack :");  
        max_qty=Integer.parseInt(cin.readLine());  
        m=max_qty;  
        while(m>=0)  
        {  
            max=0;  
            for(i=0;i<n;i++)  
            {  
                if(((float)array[1][i])/((float)array[0][i])>max)  
                {  
                    max=((float)array[1][i])/((float)array[0][i]);  
                    j=i;  
                }  
            }  
            if(array[0][j]>m)  
            {  
                System.out.println("qty of item no " +  (j+1) + " added is " +m);  
                sum+=m*max;  
                m=-1;  
            }  
            else  
            {  
                System.out.println("qty of item no " + (j+1) + " added is " + array[0][j]);  
                m-=array[0][j];  
                sum+=(float)array[1][j];  
                array[1][j]=0;  
            }  
        }  
        System.out.println("total profit is " + sum);  
    }  
}
