package offline;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    ArrayList<Player> players;

    public Main(){
        players = new ArrayList<>();
    }

    public void readLine(String s){
        ArrayList<String> split = new ArrayList<>(Arrays.asList(s.replaceAll("\\p{Punct}", "").split("\\s+")));
        int n = players.indexOf(new Player(split.get(0).charAt(0)));
        switch (split.get(1)) {
            case "starts" -> {
                if(n == -1) {
                    players.add(new Player(split.get(0).charAt(0)));
                    n = players.size()-1;
                }
                players.get(n).addCard(toNum(split.get(3)), split.get(4));
            }

            case "buys" -> {
                if (split.contains("gains")){
                    players.get(n).addCard(toNum(split.get(4)), split.get(5));
                }
            }

            case "gains" -> {
                players.get(n).addCard(toNum(split.get(2)), split.get(3));
            }

            case "trashes" -> {
                split.remove("and");
                for (int i = 2; i < split.size(); i+=2) {
                    players.get(n).removeCard(toNum(split.get(i)), split.get(i + 1));
                }
            }
        }
    }

    public int toNum(String s){
        if (s.equals("a") || s.equals("an")) return 1;
        else return Integer.parseInt(s);
    }

    public void printPlayers() {
        for (Player p :
                players) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
//        m.readLine("W starts with 7 Coppers.");
//        m.readLine("W starts with 3 Estates.");
//        m.readLine("L starts with 7 Coppers.");
//        m.readLine("L starts with 3 Estates.");
//        m.readLine("L buys and gains a Chapel.");
//        m.readLine("L buys and gains an Chapel.");
//        m.readLine("L buys and gains 2 Chapels.");
//        m.readLine("L trashes 4 Chapels.");
//
//        m.readLine("W trashes an Estate.");


        m.readLine("W starts with a Coppers.");
//        m.readLine("L starts with a Curse.");
//        m.readLine("W starts with 2 Estate.");
//        m.readLine("L buys and gains a Chapel.");
//        m.readLine("L buys and gains an Estate.");
//        m.readLine("L buys and gains 2 Estates.");
//        m.readLine("L gains a Copper");
//        m.readLine("W trashes a Curse, a Copper and an Estate.");
//        m.readLine("W trashes 2 Coppers and 2 Estates.");



        m.printPlayers();
    }
}
//load start w/
//ignore draw and shuffle (for now)
//ignore plays (for now)
//SEE:
//trashes
//buys and gains
// gains??

//y combinator, hacker news

