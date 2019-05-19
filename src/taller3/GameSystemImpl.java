/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;
import java.io.IOException;
import ucn.ArchivoEntrada;
import ucn.StdOut;
import ucn.StdIn;
import java.util.InputMismatchException;
import ucn.Registro;
import ucn.StdRandom;
/**
 *
 * @author fabianxd
 */
public class GameSystemImpl implements GameSystem {
    private HeroList Heroes;
    private WarriorList warriorCards;
    private GuardianList guardianCards;
    private SpellList spellCards;
    private Hero player1;
    private Hero player2;
    /**
     * method that open the file cards.txt and create all the cards
     */
    public void addCardsToTheSystem(){
        warriorCards = new WarriorList(50000);
        guardianCards = new GuardianList(50000);
        spellCards = new SpellList(50000);
        try{
            ArchivoEntrada arch = new ArchivoEntrada("Cards.txt");
            while(!arch.isEndFile()){
                Registro registro = arch.getRegistro();
                String field1 = registro.getString();
                String field2 = registro.getString();
                String field3 = registro.getString();
                String field4 = registro.getString();
                String field5 = registro.getString();
                String field6 = registro.getString();
                
                if(field5 != null && field6 != null){
                    String name = field1;
                    String id = field2;
                    String rarity = field3;
                    String race = field4;
                    double hp = Double.parseDouble(field5);;
                    double damage = Double.parseDouble(field6);
                    Warrior warrior = new Warrior(name,id,rarity,race,hp,damage);
                    warriorCards.addWarrior(warrior);
                }
                if(field5 == null && field6 == null){
                    String name = field1;
                    String id = field2;
                    String rarity = field3;
                    double damage = Double.parseDouble(field4);
                    Spell spell = new Spell(name,id,rarity,damage);
                    spellCards.addSpell(spell);
                }
                if(field6 == null && field5 != null){
                   String name = field1;
                   String id = field2;
                   String race = field3;
                   double hp = Double.parseDouble(field4);;
                   double damage = Double.parseDouble(field5);
                   Guardian guardian = new Guardian(name,id,race,hp,damage);
                   guardianCards.addGuardian(guardian);
                }
            }
        }
        catch(IOException e){
            StdOut.println("The file cards.txt doesn't exist");
        }
    }
    public void determinateDuelTurns(){
        StdOut.println("Player 1 enter your alias");
        String name1 = StdIn.readString();
        //no se si es asi: Deck deck1 = chooseYourCards(Card[] cards);
        StdOut.println("Player 2 enter your alias");
        String name2 = StdIn.readString();
        //no se si es asi: Deck deck1 = chooseYourCards(Card[] cards)
        int dice1 = StdRandom.uniform(6)+1;
        int dice2 = StdRandom.uniform(6)+1;
        while(dice1 == dice2){
            dice1 = StdRandom.uniform(6)+1;
            dice2 = StdRandom.uniform(6)+1;
        }
        if (dice1 > dice2){
            //Hero player1 = new Hero(name1,15,deck1,true)
            //Hero player2 = new Hero(name2,15,deck2,false)
            //addHeros(player1,player2);
        }
        else{
            //Hero player1 = new Hero(name1,deck1,false)
            //Hero player2 = new Hero(name2,deck2,true)
            //addHeros(player1,player2);
        }
    }
    public void Game(){
        
    }
    @Override
    public boolean chooseYourCards(int player){
        Warrior warriorSelected;
        Guardian guardianSelected;
        Spell spellSelected;
        
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
                return false;
            }
            if(cardSelected<warriorCards.getWarriorQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        warriorSelected=warriorCards.getWarrior(cardSelected);
        //esto es de prueba
        StdOut.println(warriorSelected.getName());
        if(player==1){
            player1.getDeck().setWarrior(warriorSelected);
            player1.getDeck().getWarrior().cardSelected();
        }
        else{
            player2.getDeck().setWarrior(warriorSelected);
            player2.getDeck().getWarrior().cardSelected();
        }
        
        
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
                return false;
            }
            if(cardSelected<guardianCards.getGuardianQty()&& cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        guardianSelected=guardianCards.getGuardian(cardSelected);
        if(player==1){
            player1.getDeck().setGuardian(guardianSelected);
            player1.getDeck().getGuardian().cardSelected();
        }
        else{
            player2.getDeck().setGuardian(guardianSelected);
            player2.getDeck().getGuardian().cardSelected();
        }
        
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
                return false;
            }
            if(cardSelected<spellCards.getSpellQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        spellSelected=spellCards.getSpell(cardSelected);
        if(player==1){
            player1.getDeck().setSpell(spellSelected);
            player1.getDeck().getSpell().cardSelected();
        }
        else{
            player2.getDeck().setSpell(spellSelected);
            player2.getDeck().getSpell().cardSelected();
        }
        return true;
    }
    @Override
    public void useWarrior(int player){
        if(player==1){
            StdOut.println(".:: Warrior of "+ player1.getAlias()+" ::.");
            StdOut.println(player1.getDeck().getWarrior().getName()+" -> "+
            player1.getDeck().getWarrior().getRace() + " -> Hp: "    +
            player1.getDeck().getWarrior().getHp() +   " -> Damage: "+
            player1.getDeck().getWarrior().getDamage());
            
            StdOut.println(".:: "+player1.getDeck().getWarrior().getName()+
            " attacks ::.");
            if(player2.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player2.getAlias());
                player2.loseHp(player1.getDeck().getWarrior().getDamage());
            }
            else{
                
                StdOut.print(player2.getDeck().getWarrior().getName()+" -> "+
                player2.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player2.getDeck().getWarrior().getHp());
            
                player2.getDeck().getWarrior().loseHp(player1.getDeck().getWarrior()
                .getDamage());
            
            }
        }
        else{
            StdOut.println(".:: Warrior of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getWarrior().getName()+" -> "+
            player2.getDeck().getWarrior().getRace() + " -> Hp: "    +
            player2.getDeck().getWarrior().getHp() +   " -> Damage: "+
            player2.getDeck().getWarrior().getDamage());
            
            StdOut.println(".:: "+player2.getDeck().getWarrior().getName()+
            " attacks ::.");
            if(player1.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player1.getAlias());
                player1.loseHp(player2.getDeck().getWarrior().getDamage());
            }
            else{
                
                StdOut.print(player1.getDeck().getWarrior().getName()+" -> "+
                player1.getDeck().getWarrior().getRace()+" -> "+ "Hp:"+
                player1.getDeck().getWarrior().getHp());
            
                player1.getDeck().getWarrior().loseHp(player2.getDeck().getWarrior()
                .getDamage());
            }
            
        }
        
        
    }
    @Override
    public void useSpell(int player){
        if(player==1){
            StdOut.println(".:: Spell of "+ player1.getAlias()+" ::.");
            StdOut.println(player1.getDeck().getSpell().getName()+" -> "+
            player1.getDeck().getSpell().getRarity() + 
            " -> Damage: "+ player1.getDeck().getSpell().getDamage());
            
            StdOut.println(".:: Attack with  "
            +player1.getDeck().getSpell().getName()+" .::");
            if(player2.getDeck().getWarrior().getHp()==0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player2.getAlias());
                player2.loseHp(player1.getDeck().getSpell().getDamage());
            }
            else{
                
                StdOut.print(player2.getDeck().getWarrior().getName()+" -> "+
                player2.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player2.getDeck().getWarrior().getHp());
            
                player2.getDeck().getWarrior().loseHp(player2.getDeck().getSpell()
                .getDamage());
                StdOut.println(" -> Damage: "+
                player2.getDeck().getWarrior().getDamage());
            }
        }
        else{
            StdOut.println(".:: Spell of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getSpell().getName()+" -> "+
            player2.getDeck().getSpell().getRarity() + " -> Damage: "+
            player2.getDeck().getSpell().getDamage());
            
            StdOut.println(".:: Attack with "+player2.getDeck()
            .getSpell().getName()+ " ::.");
            if(player1.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player1.getAlias());
                player1.loseHp(player2.getDeck().getSpell().getDamage());
            }
            else{
                StdOut.print(player1.getDeck().getWarrior().getName()+" -> "+
                player1.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player1.getDeck().getWarrior().getHp()+" -> ");
            
                player1.getDeck().getWarrior().loseHp(player2.getDeck().getWarrior()
                .getDamage());
                StdOut.println(" -> Damage: "
                +player1.getDeck().getWarrior().getDamage());
            }
            
        }
        
        
    }

    @Override
    public void showDuelOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useGuardian(int player) {
        
        
    }

    @Override
    public boolean findCardGame(String id) {
        for(int i=0;i<warriorCards.getWarriorQty();i++){
            if(id.equals(warriorCards.getWarrior(i).getId())){
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getName());
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getRace());
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getTimesUsed());
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getTimesDead());
                return true;
                
            }
        }
        for(int i=0;i<guardianCards.getGuardianQty();i++){
            if(id.equals(guardianCards.getGuardian(i).getId())){
                
            }
        }
        for(int i=0;i<spellCards.getSpellQty();i++){
            if(id.equals(spellCards.getSpell(i).getId())){
               StdOut.println(); 
               StdOut.println();
               StdOut.println();
               StdOut.println();
            }
        }
        //xddxd
        return true;
    }

    @Override
    public void heroesThatParticipated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detailsOfLastCombat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
