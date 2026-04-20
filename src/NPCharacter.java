public class NPCharacter extends Character{
    private String dialogue;

    //Constructors
    public NPCharacter(){
    }

    public NPCharacter(String characterName, int characterHP,
    int characterMP, int characterGP, String dialogue){
        super(characterName, characterHP, characterMP, characterGP);
        setDialogue(dialogue);
    }

    //Get method
    public String getDialogue(){
        return this.dialogue;
    }

    //Set method
    public void setDialogue(String dialogue){
        this.dialogue = dialogue;
    }

    //Special purpose methods
    @Override
    public String toString(){
        return super.toString() + "\nDialogue: " + getDialogue();
    }

    public String talk(String dialogue){
        return this.getCharacterName() + " Says: " + getDialogue();
    }
}
