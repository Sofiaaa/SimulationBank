

package simulationbank;

import java.io.IOException;
import java.io.Serializable;



//η SimpleCreditAccount είναι υποκλάση της CreditAccount
public  class SimpleCreditAccount extends CreditAccount implements Serializable{
    
    private final double commonaccount;//πεδίο σταθέρο για το ποσό που δεν πρέπει να υπερβαίνει η κάθε ανάληψη
    
   //ο constructor της κλάσης
   public SimpleCreditAccount(Transaction trans) throws IOException{
        
        super(trans);
        this.commonaccount=-1000;
        setRate(0.01);
        setPeriod(180);
        
    }

  /**Μέθοδο ανάληψης.Ελέγχει αν μπορεί ο πελάτης να κάνει ανάληψη το πόσο που θέλει,αν μπορεί τότε δημιουργείται ένα νέο αντικείμενο Transaction
   * με το καινούριο υπόλοιπο και τη μέρα που έγινε η ανάληψη και προστίθεται στον πίνακα Tra και μετά βαζουμε το νέο υπόοιπο στο λογαριασμό.
   * Αν δεν δίνεται  πετάει μια εξαίρεση άμα δεν μπορεί να γίνει η ανάληψη στο λογαριασμό
   * 
   * @param trans Η παράμετρος που δέχεται είναι ένα αντικείμενο της κλάσης Transaction
   * 
   * @throws WithdrawException 
   */
   public void withdraw(Transaction trans) throws WithdrawException {
        
        if ((trans.getAmount()>=commonaccount) && (-trans.getAmount()<=getBalance())){//συγκρίνει αν το ποσό ανάληψης είναι μικρότερο του 1000 και μικρότερο 
                                                                                      //από το διαθέσιμο υπόλοιποτότε να γίνει ανάληψη
           
            getTra().add(new Transaction (trans.getDate(),(getBalance()+trans.getAmount())));//δημιουργία νέας συναλλαγης με την τρέχουσα μέρα και το νέο ποσό
           
         System.out.println("Στο λογαριασμό "+ getIdl() +" έγινε ανάληψη ποσού "+trans.getAmount()+"και το νέο υπόλοιπο είναι "+(getBalance()+trans.getAmount()));
              setBalance(getBalance()+trans.getAmount());//θέτουμε το νέο υπόλοιπο
        }//αλλιώς πετάει εξαίρεση
        else{
         throw new WithdrawException("Δεν μπορεί να γίνει ανάληψη σε αυτόν τον λογαριασμό "+getIdl()+" επειδή το διαθέσιμο είναι μικρότερο "+getBalance()+" .") ;
           }  
        System.out.println();
      } 
       
       
   }


    
    
    