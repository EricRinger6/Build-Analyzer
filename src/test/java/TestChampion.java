
import org.junit.*;
import java.sql.*;

public class TestChampion {

    private String champ;
    private Champion champion;
    static String username = "eric";
    static String password = "password";
    static String url = "jdbc:mysql://localhost:3306/League";

    @Before
    public void setup(){
        Connection conn = null;
            try{
                conn = DriverManager.getConnection(url, username, password);

                champ = "Mordekaiser";

                Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet set = state.executeQuery("SELECT * FROM Champs WHERE name LIKE '%" + champ + "%'");
                set.first();

                champion = new Champion(set.getString("name"), set.getInt("health"), set.getInt("ad"),
                        set.getInt("ap"), set.getInt("desiredHealth"), set.getInt("desiredAd"), set.getInt("desiredAp"));
            }
            catch (SQLException e){
                Console.println("Champion does not exist, please try another.");
            }
    }

    @Test
    public void testName(){
        Assert.assertEquals(champion.getName(), champ);
    }

    @Test
    public void testAD(){
        Integer actual = 100;
        Assert.assertEquals(champion.getAd(), actual);
    }

    @Test
    public void testAP(){
        Integer actual = 0;
        Assert.assertEquals(champion.getAp(), actual);
    }

    @Test
    public void testHealth(){
        Integer actual = 1900;
        Assert.assertEquals(champion.getHealth(), actual);
    }

    @Test
    public void testGoals(){
        Integer health = 3000;
        Integer ad = 100;
        Integer ap = 200;
        Assert.assertEquals(champion.getHealthGoal(), health);
        Assert.assertEquals(champion.getAdGoal(), ad);
        Assert.assertEquals(champion.getApGoal(), ap);
    }

    @Test
    public void testGoalsReached(){
        Assert.assertFalse(champion.checkGoals());
    }
}
