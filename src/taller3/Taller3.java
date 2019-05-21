/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;
import java.util.InputMismatchException;
import ucn.ArchivoEntrada;
import ucn.StdIn;
import ucn.StdOut;
/**
 *
 * @author fabianxd
 */
public class Taller3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Card card1=new Card("1","2");
        GameSystemImpl system = new GameSystemImpl();
        //system.showDuelOptions();
        system.addCardsToTheSystem();
        system.determinateDuelTurns();
        system.gameTurns();
    }
    /*
        public Deck chooseYourCards(int player){
        Warrior warriorSelected;
        Guardian guardianSelected;
        Spell spellSelected;
        Deck newDeck;
        StdOut.println(" .:: Warriors ::. ");
        for(int i=0;i<warriorCards.getWarriorQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            warriorCards.getWarrior(i).getName()+" -> "+
            warriorCards.getWarrior(i).getRarity()+" -> "+
            warriorCards.getWarrior(i).getRace()+" -> "+
            "Hp: "+warriorCards.getWarrior(i).getHp()+" -> "+
            "Damage: "+ warriorCards.getWarrior(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        int cardSelected;
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<warriorCards.getWarriorQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        warriorSelected=warriorCards.getWarrior(cardSelected);
        
        
        StdOut.println(" .:: Guardians ::. ");
        for(int i=0;i<guardianCards.getGuardianQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            guardianCards.getGuardian(i).getName()+" -> "+
            guardianCards.getGuardian(i).getRace()+" -> "+
            "Hp: "+guardianCards.getGuardian(i).getHp()+" -> "+
            "Damage: "+ guardianCards.getGuardian(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<guardianCards.getGuardianQty()&& cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        guardianSelected=guardianCards.getGuardian(cardSelected);
  
        StdOut.println(" .:: Spells ::. ");
        for(int i=0;i<spellCards.getSpellQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            spellCards.getSpell(i).getName()+" -> "+
            spellCards.getSpell(i).getRarity()+" -> "+
            "Damage: "+ spellCards.getSpell(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<spellCards.getSpellQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        spellSelected=spellCards.getSpell(cardSelected);
        newDeck= new Deck(warriorSelected,guardianSelected,spellSelected);
        return newDeck;
    
    */
}
