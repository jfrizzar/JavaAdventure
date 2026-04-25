## Java Text-Based Adventure Game by Wyatt Chamberlain, Zara Hassan, and Jamil Frizzar

Welcome to our Java Text-Based Adventure Game implemented in Java.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources, the source folder contains cubfolders for each class

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Design

**To access the game manual the player can type "man" to discover all available player interactions.**

Our text-based adventure game uses common object oriented programming principles to include: inheritance, aggregation, abstract classes, etc.

The three main superclasses for our program are: Character, Item, and Location

Sub classes for Character: MainCharacter, EnemyCharacter, NPCharacter, Gambler

Sub classes for Item: Consumable, Weapon

There are no subclasses for Location.

We have managed to create character movement in our program by allowing the user to specify what cardinal direction they want to go in.

Each location has an exit array with four exits to the North, East, South and West which hold location objects. Upon player command to move into a certain direction the players current location is updated with said location object.

There are two overarching methods: initialize() which creates the entire world with all associated objects and their properties, and start() which contains the actual gameplay loop that requests and handles user input.

Within the play() method user input is requested, parsed, and checked for. If the user input matches a valid string detailed within the game manual the associated action is executed.
