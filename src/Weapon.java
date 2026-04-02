public abstract class Weapon{
    private String name;
    private String material;
    private double damage;
    private int value;

    //Constructors
    public Weapon(){

    }

    public Weapon(String name, String material, double damage, int value){
        setName(name);
        setMaterial(material);
        setDamage(damage);
        setValue(value);
    }

    //Get methods
    public String getName(){
        return this.name;
    }

    public String getMaterial(){
        return this.material;
    }

    public double getDamage(){
        return this.damage;
    }

    public int getValue(){
        return this.value;
    }

    //Set methods
    public void setName(String name){
        this.name = name;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public void setDamage(double damage){
        this.damage = damage;
    }

    public void setValue(int value){
        this.value = value;
    }
}