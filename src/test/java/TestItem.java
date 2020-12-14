import org.junit.*;
import java.sql.*;


public class TestItem {

    private String itemName;
    private Item item;
    static String username = "eric";
    static String password = "password";
    static String url = "jdbc:mysql://localhost:3306/League";

    @Before
    public void setup(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);

            itemName = "Rabadons Deathcap";

            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet set = state.executeQuery("SELECT * FROM Items WHERE name LIKE '%" + itemName + "%'");
            set.first();

            item = new Item(set.getString("name"), set.getInt("health"), set.getInt("ad"),
                    set.getInt("ap"));
        }
        catch (SQLException e){
            Console.println("Item does not exist, please try another.");
        }
    }

    @Test
    public void testName(){
        Assert.assertEquals(itemName, item.getName());
    }

    @Test
    public void testHealth(){
        Integer actual = 0;
        Assert.assertEquals(item.getHealth(), actual);
    }

    @Test
    public void testAD(){
        Integer actual = 0;
        Assert.assertEquals(item.getAd(), actual);
    }

    @Test
    public void testAP(){
        Integer actual = 120;
        Assert.assertEquals(item.getAp(), actual);
    }
}
