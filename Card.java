package offline;

public class Card {

    String name;

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
