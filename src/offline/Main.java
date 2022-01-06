package offline;

import java.util.ArrayList;

public class Main {
    ArrayList<Player> players;

    public Main(){
        players = new ArrayList<>();
    }

    public void readLine(String s){
        String[] split = s.replaceAll("\\p{Punct}", "").split("\\s+");
        for (int i = 0; i < split.length; i++) {
            switch (split[i]) {
                case "starts" -> {
                    int n = players.indexOf(new Player(split[0].charAt(0)));
                    if(n == -1) {
                        players.add(new Player(split[0].charAt(0)));
                        n = players.size()-1;
                    }
                    players.get(n).addCard(Integer.parseInt(split[3]), split[4]);
                }
                case "gains" -> {
                    int n = players.indexOf(new Player(split[0].charAt(0)));
                    players.get(n).addCard(toNum(split[i+1]), split[i+2]);
                }
                //W trashes a Curse, a Copper and an Estate.
                //L trashes 2 Coppers and 2 Estates.
                //W trashes a Curse.
                case "trashes" -> {
                    int n = players.indexOf(new Player(split[0].charAt(0)));
                    players.get(n).removeCard(toNum(split[i+1]), split[i+2]);
                    //if split contains and
                    //just jump i and del and ?
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
        m.readLine("W starts with 7 Coppers.");
        m.readLine("W starts with 3 Estates.");
        m.readLine("L starts with 7 Coppers.");
        m.readLine("L starts with 3 Estates.");
        m.readLine("L buys and gains a Chapel.");
        m.readLine("L buys and gains an Chapel.");
        m.readLine("L buys and gains 2 Chapels.");
        m.readLine("L trashes 4 Chapels.");

        m.readLine("W trashes an Estate.");

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

