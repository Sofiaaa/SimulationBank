
package simulationbank;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;



//Η κλάση CreditAccount είναι υποκλάση της Account
public abstract class CreditAccount extends Account implements Serializable{

   private  int period; //πεδίο που αποθηκεύομε πότε πρέπει κανονικά να γίνει ο τοκισμός
   double tokos=0.0; //μεταβλητή για υποογίζουμε τον τόκο
  
  //ο constructor της κλάσης
    public CreditAccount(Transaction trans) throws IOException {
        
        super(trans);
      //  this.period=period; 
    }
    
 
    /**Μέθοδος κατάθεσης.
     * Με τη μέθοδο αυτή ο πελάτης κάνει κατάθεση το ποσό που θέλει στο λογαριασμό.Δημιουργεί  ένα νέο αντικείμενο Transaction
     * με το καινούριο υπόλοιπο, τη μέρα που έγινε η κατάθεση και προστίθεται στον πίνακα Tra και μετά βαζουμε το νέο υπόοιπο στο λογαριασμό.
     * 
     * @param trans Η παράμετρος που δέχεται είναι ένα αντικείμενο της κλάσης Transaction.
     */
     public void deposit( Transaction trans){
       try {
           getTra().add(new Transaction (trans.getDate(),(getBalance()+trans.getAmount())));//δημιουργία νέας συναλλαγής με την τρέχουσα μέρα και το νέο υπόλοιπο
           Transaction transacti=new Transaction (trans.getDate(),(getBalance()+trans.getAmount()));
           getTransaction().writeObject(transacti);
           System.out.println("Στο λογαριασμό "+getIdl()+ " έγινε κατάθεση ποσού " +trans.getAmount()+" και το νέο υπόλοιπο είναι "+ (getBalance()+trans.getAmount())+" .");
           setBalance(getBalance()+trans.getAmount());//θέτουμε το νέο υπόλοιπο
           System.out.println();
       } catch (IOException ex) {
          
       }
     }  
     
     
     
     /**
      * Μέθοδος για να θέτουμε στο πεδίο period τιμή
      * @param period 
      */
     public void setPeriod(int period){
         this.period=period;
     }
     
     
     
   
   /**
    * abstract μέθοδος ανάληψης υλοποιείται στις υποκλάσεις
    * @param trans
    * @throws WithdrawException 
    */
   public abstract void withdraw(Transaction trans) throws WithdrawException ;
   
   
   
   
   /**
    * μέθοδος υπολογισμού του τόκου
    * @param date 
    */
  
    public  void payInterest(int date){ 
        
       if ((date%period==0))  {             //Αν η τρέχουσα ημέρα είναι πολλαπλάσιο της περιόδου τοκισμού 
                                            //τότε γίνεται ο τοκισμός
           int sum=getTra().size();
           int y=sum-1;                     //δείκτης τελευταίου στοιχείου του πίνακα 
           
           
          if (getTra().get(0).getDate()>(date-period)){ //Αν ο πίνακας των συναλλαγών ξεκινά στα μέσα μιας περιόδου τοκισμού
                                                        //δηλαδή αν δεν έχει υπάρξει προηγούμενος τοκισμός
               
              for(int i=0;i<(sum-1);i++){           //Αν ο πίνακας των συναλλαγών είναι μεγαλύτερος από 1 εκτελείται η for
                 
              tokos+=(((getTra().get(i+1).getDate()-getTra().get(i).getDate()))*(getTra().get(i).getAmount()));//η οποία 
                 //υπολογίζει τα χρονικά διαστήματα μεταξύ των συναλλαγών επί το υπόλοιπο του κάθε διαστήματος και τα αθροίζει
                 //ξεκινώντας από την πρώτη συναλλαγή, χωρίς να συμπεριλαμβάνει και το διάστημα μεταξύ της τρέχουσας ημερομηνίας 
                 //και της τελευταίας συναλλαγής
              
              } 
                //στη συνέχεια προστείθεται το διάστημα μεταξύ της τρέχουσας ημέρας και της τελευταίας συναλλαγής +1 
                //επειδή στο διάστημα αυτό προσμετράται και η μέρα τοκισμού, επί το υπόλοιπο
                //και διαιρούμε με το συνολικό διάστημα από την έναρξη του λογαριασμού μέχρι την τρέχουσα ημέρα
                //για να βρούμε το μέσο υπόλοιπο
            tokos=(tokos+((date-getTra().get(y).getDate()+1)*getTra().get(y).getAmount()))/(date-getTra().get(0).getDate());//σε περίπτωση 
           // που ο πίνακας των συναλλαγών έχει μόνο μία συναλλαγή και δεν εκτελείται η παραπάνω for το μέσο υπόλοιπο εξαρτάται μόνο 
           //από το διάστημα μεταξύ της τρέχουσας ημέρας και της τελευταίας συναλλαγής επί το υπόλοιπο δια το διάστημα αυτό αφού αρχικά 
            //ο τόκος ήταν μηδέν
            
             tokos=tokos*getRate()/360*(date-getTra().get(0).getDate()); //Τελικά πολ/σιάζουμε με το επιτόκιο
                    //διαιρούμε με τις μέρες του χρόνου και πολ/σιάζουμε με το συνολικό χρονικό διάστημα οπότε βρίσκουμε τον τόκο
             
            
                    } 
                    
          
            //Εάν έχει υπάρξει προηγούμενος τοκισμός θα εκτελεστεί το else 
          else{     
               for(Transaction t:getTra()){  //Διατρέχουμε τον πίνακατων συναλλαγών
               
              if  (t.getDate()==date-period){   //και αναζητούμε το στοιχείο t που περιέχει τη συναλλαγή  
                                                //προηγούμενου τοκισμού για την οποία θα εκτελεστεί το if
                  
             for (int i=getTra().indexOf(t);i<y;i++){  //Όπως και πιο πάνω (στην περίπτωση που δεν υπήρχε προηγούμενος τοκισμός) 
                 //αν ο πίνακας συναλλαγών περιέχει περισσότερες από μία συναλλαγές από το στοιχείο t και μετά, εκτελείται η for
              
              
                tokos+= (((getTra().get(i+1).getDate()-getTra().get(i).getDate()))*getTra().get(i).getAmount());//υπολογίζονται
                //όπως στην προηγούμενη περίπτωση τα διαστήματα επί το υπόλοιπο πλην το τελευταίο διάστημα
                System.out.println(tokos);
             } }    
                  
                tokos=tokos+((date-getTra().get(y).getDate()+1)*getTra().get(y).getAmount()); //προστίθεται και το τελευταίο διάστημα 
                                                                                              //συν μια μέρα
                tokos=((tokos-(t.getAmount()))/period);   // αφαιρείται η μέρα προηγούμενου τοκισμού με το υπόλοιπό της και 
                                                        //διαιρούμε με το συνολικό διάστημα ημερών για να βρούμε το μέσο υπόλοιπο
                
                tokos=tokos*getRate()/360*period;  //Ο τόκος
                
         
               
         } 
          } 
           
         
         
       double d=(double)Math.round(tokos*100)/100;  //Στρογγυλοποίηση του τόκου
       System.out.println("Aυτός είναι ο τόκος για όσο καιρό έμειναν το χρήματα μέσα στο λογαριασμό: "+d+" ."); 
       deposit(new Transaction(date,d));  //Κλήση της μεθόδου κατάθεσης για αλλαγή υπολοίπου και δημιουργία συναλλαγής
                                           //της νέας συναλλαγής στον πίνακα των συναλλαγών
       System.out.println();
       }
       
   } 
    
    
    
    
  /**
   * Μέθοδος κλεισίματος λογαριασμού.Είναι ο ίδιος κώδικας με τη μέθοδο της κατάθεσης, το μόνο που αλλάζειέχουμε προσθέσει κομμάτια κώδικα για το
   * κλείσιμο του λογαριασμου.
   * @param date
   * @param b 
   */  
   
   public  void close(int date,boolean b){
       int sum=getTra().size();//από εδώ ξεκινάει ο κώδικας που είναι ίδιος με τη μέθοδο της κατάθεσης
           int y=sum-1;
           
           
     if (date<period){
       
          for(int i=0;i<y;i++){ 
              
              tokos+=(((getTra().get(i+1).getDate()-getTra().get(i).getDate()))*(getTra().get(i).getAmount()));
            }
        
          tokos=(tokos+((date-getTra().get(y).getDate()+1)*getTra().get(y).getAmount()))/(date-getTra().get(0).getDate()); 
          
          tokos=tokos*getRate()/360*(date-getTra().get(0).getDate());           
           
              double d=(double)Math.round(tokos*100)/100; 
              System.out.println("Aυτός είναι ο τόκος για όσο καιρό έμειναν το χρήματα μέσα στο λογαριασμό: "+d+" .");
             
              deposit(new Transaction(date,d)); //μέχριεδώ.
               setBalance(0.00);//Θέτουμε το υπόλοιπο με μηδέν
               getTra().add(new Transaction(date,getBalance()));//προσθέτουμε τη συναλλαγή στο πίνακα με της συναλλαγές του λογαριασμού
              System.out.println("Ο πελάτης κάνει ανήληψη όλο το ποσό και το υπόλοιπο στο λογαριασμό πριν κλείσει είναι: "+ getBalance()+" .");
           
            
            
          }
     else{ 
          for(Transaction t:getTra()){ //εδώ είναι ίδιος ο κώδικας με τη μέθοδο της κατάθεσης 
               
              if  (t.getDate()==date-period){ 
             for (int i=getTra().indexOf(t);i<y;i++){ 
                   
                tokos+= (((getTra().get(i+1).getDate()-getTra().get(i).getDate()))*getTra().get(i).getAmount());
       
                       } 
              }
                  
                tokos=tokos+((date-getTra().get(y).getDate()+1)*getTra().get(y).getAmount()); 
                tokos=((tokos-(t.getAmount()))/period);   
                tokos=tokos*getRate()/360*period;
                
               }
                
              double d=(double)Math.round(tokos*100)/100; 
            System.out.println("Aυτός είναι ο τόκος για όσο καιρό έμειναν το χρήματα μέσα στο λογαριασμό: "+d+" .");
              
              deposit(new Transaction(date,d)); //μέχρι εδώ
             
              setBalance(0.00);//θέτουμε στο υπόλοιπο τη 0.0
              getTra().add(new Transaction(date,getBalance()));
              System.out.println("Ο πελάτης κάνει ανήληψη όλο το ποσό και το υπόλοιπο στο λογαριασμό πριν κλείσει είναι: "+ getBalance()+" .");
     }
          
          for(Client cli: getClient()){//εδώ ελέγχουμε ποιος πελάτης έχει αυτό τον λογαριασμό
   
          cli.getAccounts();
           for(int i=0;i<cli.getAccounts().size();i++){
               if (getIdl()==cli.getAccounts().get(i)){
                  cli.getAccounts().remove(i);//αφαιρούμε το λογαριασμό από το αντίστοιχο πίνακα που έχει όλους τους λογαριασμούς του πελάτη
               System.out.println("O συγκεκριμένος λογαριασμός "+getIdl()+" αφαιρείται από τους λογαριασμούς του πελάτη "+cli.getId()+" .");  
               
               
                 
                      }
              
           }
              
      }System.out.println("Ο λογαριασμός "+getIdl()+" έκλεισε." ); 
       System.out.println();
   
}}