

import java.sql.*;


public class ConnectionDB {
    static String username = "eric";
    static String password = "password";
    static String url = "jdbc:mysql://localhost:3306/League";
    private Champion champion;

    public void run(){
        Console.println("Welcome to Elo Hell build analyzer!");
        createChamp();
        createItem();
        printBuild();
        buildVerdict();
    }

    public void createChamp(){
        Connection conn = null;
        Boolean loop = true;
        while(loop){
            try{
                conn = DriverManager.getConnection(url, username, password);


                    String champ = Console.getString("Which champion will you be playing today?");

                    Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet set = state.executeQuery("SELECT * FROM Champs WHERE name LIKE '%" + champ + "%'");
                    set.first();

                    champion = new Champion(set.getString("name"), set.getInt("health"), set.getInt("ad"),
                            set.getInt("ap"), set.getInt("desiredHealth"), set.getInt("desiredAd"), set.getInt("desiredAp"));
                    loop = false;
            }
            catch (SQLException e){
                Console.println("Champion does not exist, please try another.");
            }
        }
    }

    public void createItem(){
        Connection connect = null;
        Boolean loop = true;
        while(loop) {
            try {
                connect = DriverManager.getConnection(url, username, password);
                Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                while (champion.getBuild().size() < 6) {
                    String itemName = Console.getString("Which item would you like to build");

                    ResultSet set = state.executeQuery("SELECT * FROM Items WHERE name LIKE '%" + itemName + "%'");
                    set.first();

                    Item item = new Item(set.getString("name"), set.getInt("health"), set.getInt("ad"), set.getInt("ap"));

                    champion.addItems(item);
                }
                loop = false;
            } catch (SQLException e) {
                Console.println("Item does not exist, please try another.");
            }
        }
    }

    public void buildVerdict(){
        champion.addStats();
        if(champion.checkGoals()){
            Console.println("Congrats you don't suck, i'm sure you belong in challenger and it's your teammates holding you back every game.");
        } else{
            Console.println("That build is some hot garbage, stay iron and please dodge if you see me on your team.");
        }
    }

    public void printBuild(){
        Console.println(champion.getName());
        for(Item i : champion.getBuild()){
            Console.println(i.getName());
        }
    }
}
