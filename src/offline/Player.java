package offline;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    private char name;
    private ArrayList<Card> cards;

    public Player (char name){
        this.name = name;
        cards = new ArrayList<>();
    }

    public void addCard(int n, String name){
        if (n > 1 && name.charAt(name.length()-1) == 's'){
            name = name.substring(0,name.length()-1);
        }
        for (int i = 0; i < n; i++) {
            cards.add(new Card(name));
        }
    }

    public void removeCard(int n, String name){
        if (n > 1 && name.charAt(name.length()-1) == 's'){
            name = name.substring(0,name.length()-1);
        }
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).name.equals(name) && n-- > 0)
                cards.remove(i--);
        }
    }

    public char getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return name == player.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
