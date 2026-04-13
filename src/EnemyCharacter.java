public class EnemyCharacter extends Character {
    private Item loot;
    private int expReward;

    //Constructors
    public EnemyCharacter(){
        super();
    }

    public EnemyCharacter(String characterName, int characterHP, 
    int characterMP, int characterGP, Item loot, int expReward){
        super(characterName, characterHP,characterMP, characterGP);
        setLoot(loot);
        setExpReward(expReward);
    }

    //Get methods
    public Loot getLoot(){
        return this.loot;
    }

    public int getExpReward(){
        return this.expReward;
    }

    //Set methods
    public void setLoot(Item loot){
        this.loot = loot;
    }

    public void setExpReward(int expReward){
        this.expReward = expReward;
    }

    //Special purpose methods
    @Override
    public String characterDescription(){
        return super.characterDescription() + "\nLoot: " + getLoot() + "\n Experience reward: " +getExpReward();
    }

    //TODO
    public Item dropLoot(){

    }
}
