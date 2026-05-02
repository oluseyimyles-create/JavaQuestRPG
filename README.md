Java Quest RPG 
A text-based role-playing game built in Java as part of the Programming Principles I Honours Contract project.  create a hero, explore a world map, battle enemies, manage an inventory, and face an Ancient Dragon boss to win the game.

How to Run
Download file
Open the project in Eclipse
Make sure all three files are in the same project:Main.java, Player.Java, Enemy.java
Run Main.java
Follow the on-screen messages to play

Features
Character creation — enter your hero's name at the start
World map exploration — navigate a 3x3 grid of locations using W/A/S/D
Random combat — fight Goblins, Skeletons, Trolls, and Dark Knights with damage variance
Boss fight — travel to Dragon's Lair to face the Ancient Dragon and win the game
Potion system — use Health Potions and Strong Potions during combat
Shop system — spend gold to buy potions, upgrade attack, or fully heal
Inventory — collect items from defeated enemies and the shop
Win and lose conditions — defeat the boss to win, reach 0 HP to lose

Project Structure
JavaQuestRPG/
 Main.java       # Game loop, map, combat, shop, and all game logic
Player.java     # Player class with private fields and public methods
Enemy.java      # Enemy class with private fields and public methods

Concepts from Programming Principles I
Chatr
Concept
Where Used
Ch. 1–2
Variables, data types, expressions
Player stats, gold, damage calculations
Ch. 3
Conditional statements (if/else, switch)
Menu choices, combat decisions, boundary checks
Ch. 4
Strings and Math functions
Player name input, Math.random() for damage and drops
Ch. 5
Loops (while, for)
Main game loop, combat loop, shop loop, inventory display
Ch. 6
Methods with parameters and return values
calcDamage(), heal(), takeDamage(), findItem()
Ch. 7
ArrayLists
Player inventory stored as ArrayList<String>
Ch. 8
2D arrays
World map stored as a 3x3 String[][] array
Ch. 9
Objects and Classes
Player and Enemy custom classes with encapsulation
Ch. 12
Exception handling
try/catch around user input in the combat menu

Reflection
I feel the concept I strengthened the most was loops. A challenge I encountered was implementing a working movement system. I first created a  3x4 multidimensional array and assigned areas of the map to it. I then assigned common moving keys “W, A, S, D”  that would add to the row or column depending on the input. This project aids in exercising all the skills needed to excel in future programming courses. If I had more time, I would improve my ability to execute objects and classes more efficiently. 
Author
Name: Joseph Oluseyi
Course: Programming Principles I — Honours Project
Semester: Spring 2026

