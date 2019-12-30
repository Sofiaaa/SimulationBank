

package simulationbank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;


public class SimulationBank {

   
    public static void main(String[] args) {
        
      
        
        
        Client c1=new Client("Χρήστος Παπαδόπουλος");//δημιουργία πελατών
        Client c2=new Client("Μαρία Παπαδοπούλου");
        Client c3=new Client("Γιάννης Μυλωνάς");
        Client c4=new Client("Γιώργος Νικοπολίδης");
        Client c5=new Client("Κώστας Μητρογλού");
        Client c6=new Client("Κώστας Μυλωνάς");
        Client c7=new Client("Μελίνα Παππά");
        Client c8=new Client("Αντιγόνη Γεωργίου");
        Client c9=new Client("Μιχάλης Μητρογλού");
        Client c10=new Client("Κατερίνα Δημητρίου");
        
        ArrayList <Client> allClients=new ArrayList <Client>();//πίνακας που κρατάει όλους τους πελάτες
        
        allClients.add(c1);//εισαγωγή στοιχείων στον πινάκα
        allClients.add(c2);
        allClients.add(c3);
        allClients.add(c4);
        allClients.add(c5);
        allClients.add(c6);
        allClients.add(c7);
        allClients.add(c8);
        allClients.add(c9);
        allClients.add(c10);
        
           
        try{   
       SimpleCreditAccount simple1 =new SimpleCreditAccount (new Transaction (1,0));//δημιουργία λογαριασμών απλού ταμιευτηρίου
       SimpleCreditAccount simple2 =new SimpleCreditAccount (new Transaction (1,1000));
       SimpleCreditAccount simple3 =new SimpleCreditAccount (new Transaction (10,0));
       SimpleCreditAccount simple4 =new SimpleCreditAccount (new Transaction (155,200));
       SimpleCreditAccount simple5 =new SimpleCreditAccount (new Transaction (272,0));
       SimpleCreditAccount simple6 =new SimpleCreditAccount (new Transaction (355,100));
       SimpleCreditAccount simple7 =new SimpleCreditAccount (new Transaction (50,0));
       
       SuperCreditAccount super1=new SuperCreditAccount (new Transaction(15,1000));//δημιουργία λογαριασμών υπερ-ταμιευτηρίου
       SuperCreditAccount super2=new SuperCreditAccount (new Transaction(15,0));
       SuperCreditAccount super3=new SuperCreditAccount (new Transaction(100,0));
       SuperCreditAccount super4=new SuperCreditAccount (new Transaction(150,200));
       SuperCreditAccount super5=new SuperCreditAccount (new Transaction(250,1000));
       SuperCreditAccount super6=new SuperCreditAccount (new Transaction(300,1000));
       SuperCreditAccount super7=new SuperCreditAccount (new Transaction(70,0));
       
       ReserveAccount reser1=new ReserveAccount (new Transaction (1,1000),90);//δημιουργία λογαριασμών προσθεμίας
       ReserveAccount reser2=new ReserveAccount (new Transaction (70,10000),90);
       ReserveAccount reser3=new ReserveAccount (new Transaction (100,5000),270);
       ReserveAccount reser4=new ReserveAccount (new Transaction (210,2000),180);
       ReserveAccount reser5=new ReserveAccount (new Transaction (1,1000),360);
       
     
       
      ArrayList <Account> allAccounts=new ArrayList <Account>();//πίνακας που έχει όλους τους λογαριασμούς
      
      allAccounts.add(simple1);//εισαγωγή στοιχείων στο πίνακα
      allAccounts.add(simple2);
      allAccounts.add(simple3);
      allAccounts.add(simple4);
      allAccounts.add(simple5);       
      allAccounts.add(simple6);
      allAccounts.add(simple7);
      allAccounts.add(super1);
      allAccounts.add(super2);
      allAccounts.add(super3);
      allAccounts.add(super4);
      allAccounts.add(super5);
      allAccounts.add(super6);
      allAccounts.add(super7);
      allAccounts.add(reser1);
      allAccounts.add(reser2);
      allAccounts.add(reser3);
      allAccounts.add(reser4);
      allAccounts.add(reser5);
      
      simple1.setClient(c10);
      simple1.setClient(c8);
      simple1.setClient(c7);
      simple2.setClient(c1);
      simple2.setClient(c10);
      simple3.setClient(c2);
      simple4.setClient(c3);
      simple4.setClient(c10);
      simple5.setClient(c4);
      simple6.setClient(c5);
      simple7.setClient(c9);
      
      super1.setClient(c10);
      super2.setClient(c6);
      super3.setClient(c8);
      super4.setClient(c7);
      super5.setClient(c1);
      super5.setClient(c2);
      super6.setClient(c3);
      super6.setClient(c4);
      super7.setClient(c2);
      
      reser1.setClient(c10);
      reser2.setClient(c6);
      reser3.setClient(c1);
      reser4.setClient(c2);
      reser5.setClient(c5);
      
   
        
    /* catch ( NullPointerException n) {
         System.out.println(n);
     }
    catch(IOException e)  {
       System.err.println(e);
        }   
       */
   
        
    for (int date=1;date<361;date++)   { 
                      
  
       for(Account acc: allAccounts){ 
           
           if(acc instanceof ReserveAccount){
               
              acc.payInterest(date);
           }
           else if (acc instanceof SimpleCreditAccount){
               acc.payInterest(date);
           }
           
           else if(acc instanceof SuperCreditAccount){
           acc.payInterest(date);
       }
           
          
             
       } 
    
      
       Random r1 = new Random(); 
       int rr1=r1.nextInt(5); 
       System.out.println(rr1); 
        
      for (int j=1;j<=rr1;j++){ 
            
          Random r2= new Random(); 
        int rr2 = r2.nextInt(allAccounts.size());    
                    
       Random r4=new Random(); 
                double rr4=r4.nextInt(3000);      
         
                    Random r3=new Random(); 
                   int rr3=r3.nextInt(2); 
          
        try { 
                 if (rr3==0){ 
                             
                      allAccounts.get(rr2).deposit(new Transaction(date,rr4));                         
                } 
     
               else{ 
                    allAccounts.get(rr2).withdraw(new Transaction(date,-rr4)); 
                } 
          } 
                               
        catch(DepositException d){ 
            System.out.println(d); 
        } 
    
     catch(WithdrawException w){ 
        
      System.out.println(w); 
        
  }        
       
      } 
    } 
   
    
    try{    
    ObjectOutputStream Clients= new ObjectOutputStream(new FileOutputStream("client.bin"));
    
    for(Client cli: allClients){
      Clients.writeObject(cli);
     }
          Clients.close();
      
    } catch ( NullPointerException n) {
         System.out.println(n);
     }
    catch(IOException e)  {
       System.err.println(e);
        }
      
    
   /*  try{    
      ObjectOutputStream accounts= new ObjectOutputStream(new FileOutputStream("accounts.bin"));
      
      for(Account acc: allAccounts){
          accounts.writeObject(acc);
           }
     } 
     
     catch(IOException e){
         
         System.err.println(e);
     }   
   */
      
       
       
} catch ( NullPointerException n) {
         System.out.println(n);
     }
    catch(IOException e)  {
       System.err.println(e);
        }
        
}}