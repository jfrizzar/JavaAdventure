public abstract class Character{
    private String name;
    private double health;
    private double mana;
    private int GP;

    //Constructors
    public Character(){

    }

    public Character(String name, double health, double mana, int GP){
        setName(name);
        setHealth(health);
        setMana(mana);
        setGP(GP);
    }

    //Get methods
    public String getName(){
        return this.name;
    }

    public double getHealth(){
        return this.health;
    }

    public double getMana(){
        return this.mana;
    }

    public int getGP(){
        return this.GP;
    }

    //Set methods
    public void setName(String name){
        this.name = name;
    }

    public void setHealth(double health){
        this.health = health;
    }

    public void setMana(double mana){
        this.mana = mana;
    }

    public void setGP(int GP){
        this.GP = GP;
    }

    //Special purpose methods
    public String characterDescription(){
        return "Character name: " + getName() + "\n" +
        "Current HP: " + getHealth() + "\n" +
        "Current MP: " + getMana() + "\n" +
        "Current GP: " + getGP();
    }
}