

import java.util.ArrayList;


public class Champion {

    private String name;
    private Integer health;
    private Integer ad;
    private Integer ap;
    private Integer healthGoal;
    private Integer adGoal;
    private Integer apGoal;
    private ArrayList<Item> build;


   public Champion(String name, Integer health, Integer ad, Integer ap, Integer healthGoal, Integer adGoal, Integer apGoal){
       this.name = name;
       this.health = health;
       this.ad = ad;
       this.ap = ap;
       this.healthGoal = healthGoal;
       this.adGoal = adGoal;
       this.apGoal = apGoal;
       this.build = new ArrayList<Item>();
   }

    public String getName() {
        return name;
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

    public Integer getHealthGoal() {
        return healthGoal;
    }

    public Integer getAdGoal() {
        return adGoal;
    }

    public Integer getApGoal() {
        return apGoal;
    }

    public void addItems(Item item){
       this.build.add(item);
    }

    public ArrayList<Item> getBuild(){
       return build;
    }

    public void addStats(){
       for(Item i : build){
           this.health += i.getHealth();
           this.ad += i.getAd();
           this.ap += i.getAp();
       }
    }

    public Boolean checkGoals(){
       return health >= healthGoal && ad >= adGoal && ap >= apGoal;
    }

}
