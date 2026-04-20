public abstract class Character{
    private String characterName;
    private int characterHP;
    private int characterMP;
    private int characterGP;

    //Constructors
    public Character(){ 
    }

    public Character(String characterName, int characterHP, int characterMP, int characterGP){
        setCharacterName(characterName);
        setCharacterHP(characterHP);
        setCharacterMP(characterMP);
        setCharacterGP(characterGP);
    }

    //Get methods
    public String getCharacterName(){
        return this.characterName;
    }

    public int getCharacterHP(){
        return this.characterHP;
    }

    public int getCharacterMP(){
        return this.characterMP;
    }

    public int getCharacterGP(){
        return this.characterGP;
    }

    //Set methods
    public void setCharacterName(String characterName){
        this.characterName = characterName;
    }

    public void setCharacterHP(int characterHP){
        this.characterHP = characterHP;
    }

    public void setCharacterMP(int characterMP){
        this.characterMP = characterMP;
    }

    public void setCharacterGP(int characterGP){
        this.characterGP = characterGP;
    }

    //Special purpose methods
    public String toString(){
        return "Character name: " + getCharacterName() + "\n" +
        "Current HP: " + getCharacterHP() + "\n" +
        "Current MP: " + getCharacterMP() + "\n" +
        "Current GP: " + getCharacterGP();
    }
}