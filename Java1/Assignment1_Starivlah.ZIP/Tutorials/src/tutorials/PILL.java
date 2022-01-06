/*
 Nikola Starivlah
991570332
2020-01-30
The purpose of the program is to get morpheus to tell neo
that there are two choices and ten they ask him to choose

 */
package tutorials;

/**
 *
 * @author Nik
 */

public class PILL {//Pill class
    
    
    //Private data feilds 
    private String color;
    private static double size;
    private String efficacy;
    private boolean status;
    
     public boolean isTaken(){//i taken method for data feild status
       return status;
    }
     
     public void setstatus(boolean status)//set method for status
             {
              this.status = status;   
             }
    
    public void printInfo()
//method that print all the information about the pill            
    {
        if(size >= 2)//if size is greater than 2 print large
        {
            System.out.println("large");
        }
        else if (size <= 2)//if size is smaller than 2 print small
        {
            System.out.println("small");   
        }
        if(status == true)//prints status to be taken if status is true
        {
            System.out.println("taken");
        }
        else if(status == false)
        {
            System.out.println("availible");
        }
    }

    public PILL(String color, double size){
       this.color = color;
       this.size = size ;
       status = true;
    }//construcotr that creates a pill with pecified color
    //and size 
    public void setcolor(String color){
        this.color=color;
    }
    

    //getSet methods
    public String getcolor(){
        return color;
    }
    public void setsize(double size){
        this.size = size;
    }
    public double getsize(){
        return size;
    }
    public void setefficacy(String efficacy){
        this.efficacy = efficacy;
    }
    public String getefficacy(){
        return efficacy;
    }
  
}
