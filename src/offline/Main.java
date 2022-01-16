package offline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    ArrayList<Player> players;

    public Main(){
        players = new ArrayList<>();
    }

    public ArrayList<String> splitLine(String str) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        Pattern p = Pattern.compile("\\d+");

        for (int i = 0; i < split.size(); i++) {
            String s = split.get(i);
            if (s.equals("a") || s.equals("an") || p.matcher(s).matches()) {
                if (split.get(i+1).charAt(split.get(i+1).length()-1) != ',' &&
                        split.get(i+1).charAt(split.get(i+1).length()-1) != '.') {
                    if (!split.get(i+2).equals("and")) {
                        split.set(i + 1, split.get(i + 1) + " " + split.get(i + 2));
                        split.remove(i + 2);
                    }
                }
            }
            split.set(i, split.get(i).replaceAll("\\p{Punct}", ""));
        }
        return split;
    }

    public void readLine(String s){
//        ArrayList<String> split = new ArrayList<>(Arrays.asList(s.replaceAll("\\p{Punct}", "").split("\\s+")));
        ArrayList<String> split = splitLine(s);
        int n = players.indexOf(new Player(split.get(0)));
        switch (split.get(1)) {
            case "starts" -> {
                if(n == -1) {
                    players.add(new Player(split.get(0)));
                    n = players.indexOf(new Player(split.get(0)));
                }
                split.remove("and");
                for (int i = 3; i < split.size(); i+=2) {
                    players.get(n).addCard(toNum(split.get(i)), split.get(i + 1));
                }
//                players.get(n).addCard(toNum(split.get(3)), split.get(4));
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
            case "plays" -> {}
            case "draws" -> {}
            case "discards " -> {}
            case "shuffles  " -> {}
        }
    }

    public int toNum(String s){
        if (s.equals("a") || s.equals("an")) return 1;
        else return Integer.parseInt(s);
    }

    public void printPlayers() {
        for (Player p :
                players) {
            System.out.println("Player: " + p);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.readLine("W starts with a Goat.");
        m.readLine("W starts with 6 Coppers.");
        m.readLine("W starts with a Hovel, a Necropolis and an Overgrown estate.");
        m.readLine("j starts with a Goat.");
        m.readLine("j starts with 6 Coppers.");
        m.readLine("j starts with a Hovel, a Necropolis and an Overgrown.");
        m.readLine("W shuffles their deck.");
        m.readLine("W draws 2 Coppers, a Necropolis, an Overgrown Estate and a Goat.");
        m.readLine("j shuffles their deck.");
        m.readLine("j draws 5 cards.");

        m.readLine("Turn 1 - William123");
        m.readLine("W plays a Necropolis.");
        m.readLine("W gets +2 Actions.");
        m.readLine("W plays 2 Coppers. (+$2)");
        m.readLine("W plays a Goat. (+$1)");
        m.readLine("W trashes an Overgrown.");
        m.readLine("W draws a Copper.");
        m.readLine("W plays a Copper. (+$1)");
        m.readLine("W buys a Wedding.");
        m.readLine("W gains a Gold.");
        m.readLine("W gets 1 VP.");
        m.readLine("W shuffles their deck.");
        m.readLine("W draws 4 Coppers and a Hovel.");

        m.readLine("Turn 1 - jkb");
        m.readLine("j plays 4 Coppers. (+$4)");
        m.readLine("j plays a Goat. (+$1)");
        m.readLine("j buys a Forum.");
        m.readLine("j gets +1 Buy.");
        m.readLine("j gains a Forum.");
        m.readLine("j draws 5 cards.");
//        m.readLine("");
//        m.readLine("");

        m.printPlayers();
/*
        String s = "";
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            s = scan.next();
            System.out.println(s + ":");
            Pattern p = Pattern.compile("\\d+");
            if (s.equals("a") || s.equals("an") || p.matcher(s).matches()) {
                System.out.println("y");
            }
            else {
                System.out.println("no");
            }
        }
*/
    }
}

//y combinator, hacker news
//https://dominion.games/images/cards/art/dark-ages/overgrown-estate.jpg

