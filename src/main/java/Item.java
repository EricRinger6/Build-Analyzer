

public class Item {

    private String name;
    private Integer health;
    private Integer ad;
    private Integer ap;

    public Item(String name, Integer health, Integer ad, Integer ap) {
        this.name = name;
        this.health = health;
        this.ad = ad;
        this.ap = ap;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getAd() {
        return ad;
    }

    public Integer getAp() {
        return ap;
    }

    public String getName() {
        return name;
    }
}
