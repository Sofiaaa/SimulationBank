

package simulationbank;

//Η ReserveAccount είναι υποκλάση της Account

import java.io.IOException;
import java.io.Serializable;

public class ReserveAccount extends Account implements Serializable{
    
   private int period; //πεδίο που αποθηκεύουμε την τιμή ανάλογα με το χρονικό δίαστημα που θέλουμε να δεσμεύσει τα χρήματα ο πελάτης
   private CreditAccount x; //πεδίο που είναι αντικείμενο της κλασης CreditAccount

 //ο constructor της κλασης  
public ReserveAccount (Transaction trans,int period) throws IOException{
    
    super (trans);
    setRate(0.04);
    this.period=period;
}
/**
 * Σε αυτή τη μέθοδο θέτουμε τιμή στο πεδίο  x που είναι αντικείμενο της κλάσης CreditAccount
 * @param x 
 */
public void setA(CreditAccount x){
    this.x=x;
}


/**
 * Αυτή η μέθοδος το μόνο που κάνει είναι να πετάει εξαίρεση τύπου DepositException
 * γιατί δεν μπορεί να γίνει κατάθεση σε αυτόν τον λογαριασμό.
 * @param trans η παράμετρος που δέχεται είναι ένα αντικείμενο της κλάσης Transaction
 * @throws DepositException 
 */
    public  void deposit(Transaction trans) throws DepositException{
        
        throw new DepositException("Δεν μπορεί να γίνει κατάθεση σε αυτό το λογαριασμό.Είναι λογαριασμός προσθεμίας.");
    }
    
    
  /**Αυτή η μέθοδος το μόνο που κάνει είναι να πετάει εξαίρεση τύπου WithdrawException
   * γιατί δεν μπορεί να γίνει ανάληψη σε αυτόν τον λογαριασμό.
   * @param trans η παράμετρος που δέχεται είναι ένα αντικείμενο της κλάσης Transaction
   * @throws WithdrawException 
   */  
     public void withdraw(Transaction trans) throws WithdrawException {
         
       throw new WithdrawException("Δεν μπορεί να γίνει ανάληψη σε αυτό το λογαριασμό.Είναι λογαριασμός προσθεμίας.");   
}
     
     
  /**
   * Αυτή η μέθοδος υπολογίζει τον τοκίσμο του λογαριασμού ανάλογα το πόσο καιρό έχει δεσμεύσει ο πελάτης 
   * το λογαριασμό.Έχουμε μία μεταβλητή rate που της εκχωρούμε  το ανάλογο επιτόκιο ανάλογα το πόσο καιρό θέλουμε να είναι
   * δεσμευμένος ο λόγαριασμος.Ελέγχουμε αν είναι η σωστή μέρα τοκισμού,αν είναι τότε υπολογίζει το τόκο (παίρνει το ποσό
   * το πολλαπλασιάζει με το σωστό επιτόκιο το διαιρεί με τις 360 μέρες και μετά πολλαπλασιάζει το αποτέλεσμα με το χρονικό
   * διάστημα που δεσμεύεται ο λογαριασμός).Μετά στρογγυλοποιούμε τον τόκο σε δύο δεκαδικά ψηφία .Μέτα δημιουργούμε ένα αντικείμενο
   * της κλάσης Trancaction μετά στο αντικείμενο της CreditAccount καλούμε τη μέθοδο κατάθεση του αντίστοιχου αντικειμένου.
   *Μετά καλούμε τη μέθοδο close για να κλείσει ο λογαριασμός.
   * @param date 
   */   
     
       public  void payInterest(int date){
             double rate;
             double add=0.002;
             
           if  ((period-date)+1==getTra().get(0).getDate()){
                if(period==90)
               { rate=getRate();
               System.out.println("Ο λογαριασμός είναι κλειστός για 3 μήνες.");
                   
               } else if(period==180) {
                   rate=getRate()+add;
                    System.out.println("Ο λογαριασμός είναι κλειστός για 6 μήνες.");
               }
               else if(period==270){
                   rate=getRate()+(2*add);
                    System.out.println("Ο λογαριασμός είναι κλειστός για 9 μήνες.");
               }
               else{
                   rate=getRate()+(3*add);
                    System.out.println("Ο λογαριασμός είναι κλειστός για 12 μήνες.");
               }
                  
                 double amount= getTra().get(0).getAmount();
                 double  tokos= (rate*amount)/360*period;
                 double d=(double) Math.round(tokos*100)/100;
                 System.out.println("Ο τόκος είναι : "+d);
               
                 Transaction t1=new Transaction(date,(d+getTra().get(0).getAmount()));
                 x.deposit(t1);
                 
                  
                  }
                 
                close(date,false);
          
  }
                    
            
/**
 * Η μέθοδος αυτή κλείνει το λογαριασμό και αφαιρεί από το πελάτη τον αντιστοιχό λογαριασμό από το πίνακα Accounts
 * του πελάτη.Αν κλείσει νωρίτερα τότε στο συνδεδεμένο λογαριασμό καταθέτει το ποσό που είχε στην αρχή που άνοιξε ο
 * προθεσμιακός λογαριασμός χωρίς τόκους.
 * @param date
 * @param t η παράμετρος αυτή είναι true όταν θέλει ο πελάτης να κλείσει νωρίτερα τον λογαριασμό για να εκτελεστεί
 * ο αντίστοιχος κώδικας και false όταν θέλουμε να κλείσει ο λογαριασμός και πρώτα να γίνει ο τοκισμός
 */          
   
   public  void close(int date,boolean t ){
    
       for(Client cli: getClient()){
                    cli.getAccounts();
                    for(int i=0;i<cli.getAccounts().size();i++){
                   Integer x=cli.getAccounts().get(i);
                      if (x==getIdl()){
                        cli.getAccounts().remove(i);
                        System.out.println("Έκλεισε ο λογαριασμός νωρίτερα!!!!");
                      }
      
   }        if(t==true){
                    x.deposit(new Transaction(date,(getTra().get(0).getAmount())));
       }
      
}
     
   }
}