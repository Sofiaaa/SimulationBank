

package simulationbank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Account implements Serializable {
    
    
    
    private static int idlstatic;  //static πεδίο που αυξάνεται,το χρησιμοποιούμε για να εκχωρούμε διαφορετικές τιμές στο πεδίο idl
   // private ArrayList<Integer> idclient;
    private ArrayList<Client> clients;//πεδίο που αποθηκεύει τους πελάτες
    private ArrayList<Transaction> tra;//πεδίο που αποθηκεύει τις συναλλαγές
    private double balance;//πεδίο που αποθηκεύει το τρέχον υπόλοιπο
    private double rate;//πεδίο που αποθηκεύει το επιτόκιο
    private  int idl; //πεδίο που αποθηκεύει τον αριθμό του λογαριασμού
    private String idlll;
    private  ObjectOutputStream transaction;
    private   ObjectInputStream readtrans ;
    //ο constructor της κλάσης
    public Account(Transaction trans) throws IOException{
       idl=++idlstatic;
       idlll="Οι συναλλαγές του λογαριασμού "+idl+".bin";
       this.balance=trans.getAmount();
      //this.idclient =new ArrayList<Integer>();
       this.clients =new ArrayList<Client>();
       this.tra=new ArrayList<Transaction>();
       this.tra.add(trans);
        readtrans=new  ObjectInputStream(new FileInputStream( idlll));
       //readtrans.close();
       transaction= new  ObjectOutputStream(new FileOutputStream( idlll));
      
       transaction.writeObject(trans);
       
      
     
        transaction.close();
       
      // this.rate=rate;
    }
   
    
   public ObjectOutputStream getTransaction(){
       return transaction;
   }
  
    
    //Με τη μέθοδο αυτή παίρνουμε τον πίνακα Transaction
    public ArrayList<Transaction> getTra(){
        return tra;
    }
   //Με τη μέθοδο αυτή παίρνουμε την τιμή του διαθέσιμου υπολοίπου
    public double getBalance(){
        return balance;
    }
    
    //Με τη μέθοδο αυτή παίρνουμε τον αριθμό τουλογαριασμού
    public int getIdl(){
        return idl;
    }
   public void setClient(Client cli){
       clients.add(cli);
       cli.setAccount(idl);
       
   }
   public ArrayList <Client> getClient(){
       
       return clients;
   }
   
   public void setRate(double rate){
        this.rate=rate;
   }
   public double getRate(){
       return rate;
   }
   
   ////Με τη μέθοδο αυτή εκχωρούμε τιμή στο διαθέσιμο υπόλοιπο του λογαριασμού 
   public void setBalance(double balance){
       this.balance=balance;
   }
   
   
   
   
   
   //αφηρημένη μέθοδος για την κατάθεση
   public abstract void deposit(Transaction trans) throws DepositException;   
   //αφηρημένη μέθοδος για την ανάληψη
   public abstract void withdraw(Transaction trans) throws WithdrawException;
   //αφηρημένη μέθοδος για τον τοκισμό
   public abstract void payInterest(int date);
   //αφηρημένη μέθοδος για το κλείσιμο
   public abstract void close(int date,boolean b ); 
}
