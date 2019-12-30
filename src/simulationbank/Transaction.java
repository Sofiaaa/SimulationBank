

package simulationbank;

import java.io.Serializable;


public class Transaction implements Serializable{
    
    private int date;//πεδίο που αποθηκεύει την ημερομηνία
    private double amount;//πεδίο που αποθηκεύει το ποσό,θετικό για κατάθεση,αρνητικό για ανάληψη
    
    //ο constructor της κλάσης
    public Transaction(int date,double amount){
       this.date=date;
       this.amount=amount;
    }
  //Με τη μέθοδο αυτή παίρνουμε την τιμή του ποσού από το πεδίο amount
    public double getAmount(){
        return amount;
    }
    
    //Με τη μέθοδο αυτή παίρνουμε την τιμή της μέρας από το πεδίο date
    public int getDate(){
        return date;
    }
  
}

