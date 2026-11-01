# Java Text Adventure RPG

A feature-rich, object-oriented console RPG written in Java. This project demonstrates core computer science principles such as polymorphism, encapsulation, inheritance, dynamic scaling, state management, and object serialization for saving/loading progress.

---

## 🎮 Features

* **Turn-Based Combat System:** Battle scaling enemies with randomized damage calculations and turn options (Attack, Heal, Flee).
* **Player Progression:** Gain EXP, level up, boost base stats, and restore HP automatically upon leveling up.
* **Inventory & Equipment:** Collect potions and weapons; dynamically equip gear to boost combat performance.
* **Procedural Exploration:** Explore dungeon rooms with randomized events (Combat encounters, Treasure chests, and Safe rooms).
* **Game Persistence:** Built-in save/load engine powered by Java Object Serialization (`java.io.Serializable`).
* **Zero External Dependencies:** Built entirely using standard Java SE library.

---

## 🏗️ Architecture & OOP Concepts Demonstrated

* **Abstract Base Classes (`Entity`):** Shared properties (`hp`, `defense`, `attack`) and behaviors between `Player` and `Enemy`.
* **Polymorphism:** Unique implementations of `calculateAttackDamage()` across different entity types.
* **Encapsulation:** Controlled state modification through getters/setters and specialized methods (e.g., `takeDamage()`, `gainExp()`).
* **State Management (`GameState`):** Keeps track of room depth, inventory management, and game loops.

---

## 📂 Project Structure

```text
src/
└── rpg/
    ├── entity/
    │   ├── Entity.java       # Abstract base entity
    │   ├── Player.java       # Player stats, levelling, inventory
    │   └── Enemy.java        # Dungeon enemy mechanics
    ├── item/
    │   └── Item.java         # Consumables and equipment
    ├── engine/
    │   ├── GameState.java    # Game loop & serialization
    │   └── CombatEngine.java # Turn-based battle loop
    └── Main.java             # Entry point / Menu system
```

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Git

### Installation & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/java-text-adventure-rpg.git
   cd java-text-adventure-rpg
   ```

2. **Compile the source files:**
   ```bash
   javac -d bin src/rpg/*.java src/rpg/*/*.java
   ```

3. **Run the game:**
   ```bash
   java -cp bin rpg.Main
   ```

---

## 🖥️ Sample Console Output

```text
=================================
    JAVA TEXT ADVENTURE RPG      
=================================
1. Start New Game
2. Load Saved Game
3. Exit
Choose an option: 1

Enter your hero's name: Arthas

================================================
 DUNGEON ROOM 1
================================================
1. Explore Deeper
2. View Player Stats
3. Inventory & Equipment
4. Save Game
5. Exit to Main Menu
Select an option: 1

------------------------------------------------
  A wild Goblin appeared! (HP: 30 | ATK: 8)
------------------------------------------------

Your HP: 100/100 | Enemy HP: 30/30
1. Attack
2. Use Potion
3. Attempt to Flee
Choose an action: 1

 You strike the Goblin!
  -> Goblin took 14 damage! (Remaining HP: 16/30)
 The Goblin attacks back!
  -> Arthas took 3 damage! (Remaining HP: 97/100)

Your HP: 97/100 | Enemy HP: 16/30
1. Attack
2. Use Potion
3. Attempt to Flee
Choose an action: 1

 You strike the Goblin!
  -> Goblin took 17 damage! (Remaining HP: 0/30)

 You defeated the Goblin!
 You gained 25 EXP!
 The monster dropped a Health Potion!
```