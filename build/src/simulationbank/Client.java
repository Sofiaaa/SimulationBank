

package simulationbank;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable {
    
    
   private String name;//πεδίο που αποθηεύουμε το όνομα του πελάτη
   private int id;//πεδίο που αποθηκεύουμε το μοναδικό αριμό που έχει κάθε πελάτης
   private static int idstatic;//static πεδίο που αυξάνεται συνέχεια
   private ArrayList <Integer> accounts;//πίνακας που αποθηκεύουμε τον αριθμό (idl) του λογαριασμού
   
 
   
    

  //ο constructor της κλάσης
  public Client(String name){
      
     
    
      this.name=name;
      this.id=++idstatic;
      accounts=new ArrayList <Integer>();
     
     
  }
  
  
/**
 * Μέθοδο που αναθέτουμε στο πίνακα με τους λογαριασμούς του πελάτη το αριθμό του λογαριασμού
 * @param idl 
 */
   public  void setAccount(int idl){
       accounts.add(idl);
     
      
   }
   
   /**
    * Μέθοδο που παίρνουμε τον αριθμό (id) του πελάτη
    * @return 
    */
   public int getId(){
       return id;
   }
   
   /**
    *  Μέθοδο που παίρνουμε τον πίνακα Accounts που έχει τους λογαριασμούς του πελάτη
    * @return 
    */
   public ArrayList<Integer> getAccounts(){
       return accounts;
   }
   
   
  /**
   * Μέθοδο που εκτύπωνει το αντικείμενο πελάτη
   * @return 
   */
   public String toString(){
      
           
     return ("Το όνομα του πελάτη είναι "+name+",το id είναι "+id+" και οι συνολικοί του λογαριασμοί  είναι " + countAccounts())+" .";  
   }
   
   
 /**
  * Μέθοδος που μετράει πόσους συνολικά λογαριασμούς έχει ο πελάτης
  * @return 
  */
 public int countAccounts(){
    if(accounts.isEmpty()){
        return 0;
    }
     else
        
    {    int count=0;
        for(int i=0;i<accounts.size();i++){
          count++;}
       return count;
    } 
    
     
    }
 
   
 }

