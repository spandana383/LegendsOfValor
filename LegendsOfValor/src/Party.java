import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<Character> party = new ArrayList<Character>();

    public boolean checkMember(Character c){ // check c is in party or not
        if(party.isEmpty()){
            return false;
        }
        for(int i = 0; i < party.size(); i++){
            if(party.get(i).getName().equals(c.getName())){ // if name is the same
                return true;
            }
        }
        return false;
    }
    public void addMember(Character h){
        party.add(h);
    }
    public Character getMembers(int index){ //return hero by the index
        return party.get(index);
    }
    public int size(){
        return party.size();
    }
    public Character getCharacter(int index){
        return party.get(index);
    }
    public void updateCharacter(int index, Character c){// update the character in party
        party.set(index, c);
    }
    public void updateCharacterBYSearch(Character c){ // search this character is in the party. if yes, update character
        for(int i = 0; i < party.size(); i++){
            if(party.get(i).getName().equals(c.getName())){
                party.set(i, c); // update the character data
                return;
            }
        }
    }
    public int getMaxLevel(){ // return the max value of level in the party
        int max_level = 0;
        for(int i = 0; i < party.size(); i++){
            if(party.get(i).getLevel() > max_level){
                max_level = party.get(i).getLevel();
            }
        }
        return max_level;
    }
    public void remove(Character c){
        party.remove(c);
    }
    public void gainMoneyExp(int money, int exp){ // everyone gain money and exp
        System.out.println(">> Let's collect some Gold and gain some Exp <<");
        for(Character h: party){
            h.gainGold(money);
            h.gainExp(exp);
            TerminalPrinter.gain_exp_money_print(h,money,exp);
        }
    }

    public boolean empty(){
        return party.isEmpty();
    }

    @Override
    public String toString() {
        String party_print = party.get(0).getTitle();
        for(int i = 0; i < party.size(); i++){
            party_print+=("["+i+"]" +party.get(i).toString());
        }
        party_print += "\n";
        return party_print;
    }

    public int getIndex(Character c){
        return party.indexOf(c);

    }




}
