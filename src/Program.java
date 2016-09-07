import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sande on 7/11/2016.
 */
public class Program {
    public static void main(String[] args) {

        List list = new List();
        list.input();
        list.arrangeList();

        for(int a = 1;;a++){
            System.out.println("ronde: "+ a +"\n");
            list.play();
        }
    }
}

class List {

    Scanner sc = new Scanner(System.in);

    ArrayList<Char> inputList = new ArrayList<>();
    ArrayList<Char> showList = new ArrayList<>();
    ArrayList<Char> holdList = new ArrayList<>();

    void input(){
        while(true){
            System.out.println("insert name (end+enter to stop adding ");
            String naam = sc.next();
            if(naam.equals("end")) {
                break;
            }
            System.out.println("insert initiative");
            int initiative = sc.nextInt();
            System.out.println("insert initiative bonus");
            int initiativeBonus = sc.nextInt();
            inputList.add(new Char(naam, initiative, initiativeBonus));
        }
    }
    void print(ArrayList<Char> e){
        for(Char x : e){
            System.out.println(x.name +" "+ x.initiative +" "+ x.initiativeBonus);

        }
        System.out.println("");
    }
    void arrangeList(){
        for(int x = 40 ; x > 0 ; x--){
            for(int z = 20 ; z >= 0 ; z--){
                for(int y = 0 ; y < inputList.size() ; y++){
                    if(inputList.get(y).initiative == x && inputList.get(y).initiativeBonus == z){
                        showList.add(inputList.get(y));
                    }
                }
            }
        }
    }
    void play(){
        print(showList);
        print(holdList);
        volgende:
        for(int a = 0 ; a < showList.size() ; a++){
            hold:
            for(int b = 0; b < holdList.size() ; b++){
                System.out.println("Wil "+ holdList.get(b).name +" zijn beurt nu? y+enter als je je beurt wil, n+enter zo niet");
                String input = sc.next();
                if(input.equals("y")){
                    if(a == 0){
                        holdList.get(b).initiative = showList.get(a).initiative;
                        showList.add(a, holdList.get(b));
                        holdList.remove(b);
                        print(showList);
                        print(holdList);
                        break hold;
                    }
                    else{
                        holdList.get(b).initiative = showList.get(a-1).initiative;
                        showList.add(a, holdList.get(b));
                        holdList.remove(b);
                        print(showList);
                        print(holdList);
                        break hold;
                    }

                }

            }
            System.out.println("De beurt is aan: "+ showList.get(a).name +", y+enter voor volgende, h+enter voor hold");
            String input = sc.next();
            switch (input){
                case "y":   break;
                case "h":   holdList.add(showList.get(a));
                            showList.remove(a);
                            a--;
                            break;
                default:    System.out.println("voer juiste letter in");
            }
        }
    }
}

class Char{
    String name;
    int initiative;
    int initiativeBonus;
    Char(String name, int initiative, int initiativeBonus){
        this.name = name;
        this.initiative = initiative;
        this.initiativeBonus = initiativeBonus;
    }
}
