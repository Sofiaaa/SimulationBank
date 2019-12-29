

package simulationbank;

import java.io.IOException;
import java.io.Serializable;



//Η SuperCreditAccount είναι υποκλάση της CreditAccount
public  class SuperCreditAccount extends CreditAccount implements Serializable {
    
    
  // ο construstor της κλάσης
   public SuperCreditAccount(Transaction trans) throws IOException{
        
        super(trans);  
        setRate(0.02);
        setPeriod(30);
   }
   
   
  
  /**Μέθοδος ανάληψης.
   * Έχουμε μία μεταβλητή count η οποία μετράει πόσες φορές έχει γίνει ανάληψη αυτό το μήνα.Τρέχουμε τον πίνακα Transaction και πέρνουμε τη μέρα από κάθε αντικείμενο
   * και τη διαιρούμε με το 30 και αν το αποτέλεσμα είναι ίσο με τη μέρα δια 30 του τρέχοντος αντικειμένου τότε αυξάνεται η μεταβλητη.Αν η μεταβλητή πάρει την τιμή ίση με τρία τότε πετάει μια 
   * εξαίρεση WithdrawException με την αντίστοιχη εκτύπωση.Αλλιώς γίνετε η ανάληψη έκτος άμα το ποσό που ζητάει ο πελάτης ειναι μεγαλύτερο από το διαθέσιμο υπόλοιπο
   * οπότε πετάει μια εξαίρεση WithdrawException με την αντίστοιχη εκτύπωση.
   * @param trans αντικείμενο της κλάσης Transaction
   * @throws WithdrawException 
   */

  public void withdraw (Transaction trans)throws WithdrawException  {
   
     int count=0;
    
     for (int i=0;i<getTra().size();i++){
         
         if((trans.getDate()/30)==(getTra().get(i).getDate()/30)){//βρίσκει το μήνα και 
              count++;//μετράει πόσες φορές έχει γίνει ανάληψη αυτό το μήνα
             
         }
     }  

     
        if(count <3){//αν είναι μικρότερο από τρια μπορεί να γίνει ανάληψη αυτό το μήνα
        if ( (-trans.getAmount()<=getBalance())){//συγκρίνει αν το ποσό ανάληψης είναι μικρότερο από το διαθέσιμο υπόλοιπο ,αν είναι γίνεται ανάληψη
        getTra().add(new Transaction (trans.getDate(),(getBalance()+trans.getAmount())));//δημιουργία συναλλάγης ανάληψης με την τρέχουσα μέρα και το νέο υπόλοιπο 
        System.out.println("Στο λογαριασμό "+getIdl()+" έγινε ανάληψη ποσού "+trans.getAmount()+" και το νέο υπόλοιπο είναι "+(getBalance()+trans.getAmount()));
        setBalance(getBalance()+trans.getAmount());//θέτουμε το νέο υπόλοιπο
        }//αλλιώς πετάει εξαίρεση
     else{
         throw new WithdrawException("Δεν μπορεί να γίνει ανάληψη σε αυτόν τον λογαριασμό "+getIdl()+" επειδή το διαθέσιμο είναι "+getBalance()+" .") ;
           } 
         
      }  
        else {
            throw new WithdrawException("Δεν μπορεί να γίνει ανάληψη σε αυτόν τον λογαριασμό "+getIdl()+" γιατί έχουν γίνει ήδη τρεις αναλήψεις αυτό το μήνα.");
        }
System.out.println();
     }



  }