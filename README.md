# Game-Development-coursework
Includes two games. Game breaker and Snakes
NOTE: Video links are pasted in word document

------------------------------------------------------------------------
BRICK BREAKER GAME — COURSEWORK PROJECT
Engine: Java (BlueJ)
Architecture: Object-Oriented, Java GUI (Swing + AWT)

------------------------------------------------------------------------
A video game demonstrating the play of this game.
https://youtu.be/1L4pvA-VQEg


1. GAME OVERVIEW
The player controls a paddle to bounce a ball and break bricks.
Win a level by breaking all bricks.
Lose a life if the ball falls below the paddle.
Game ends when lives reach zero.
Progress through 4 levels, each increasing in difficulty.


2. HOW TO RUN THE GAME IN BLUEJ
a) Open BlueJ
b) Click "Project" → "Open Project"
c) Select the BrickBreaker folder
d) Right-click the class `BrickBreaker` and choose:
   `void main(String[] args)`
e) Click OK to launch the game window
f) Press **SPACEBAR** on the start screen to begin


3. GAME CONTROLS (KEYBOARD INPUTS)
←  Left Arrow  : Move paddle left
→  Right Arrow : Move paddle right
SPACEBAR       : Start / Pause / Resume
P              : Pause / Resume alternative key
ESC            : Exit game immediately
R              : Restart game on Game-Over screen


4. LEVEL PROGRESSION (1 → 4)
Level 1 : Normal speed, 1-hit bricks
Level 2 : Faster ball, some 2-hit bricks
Level 3 : Even faster ball, more 2-hit bricks
Level 4 : Highest speed, many 2-hit bricks

Difficulty increases by:
- Ball speed multiplier per level
- More bricks requiring 2 hits
- Reduced reaction time for player


5. SCORING RULES
Standard brick  = 10 points
2-hit brick     = 20 points (10 for each hit)
Future bricks can be added easily in the `Brick` class


6. PAUSE SYSTEM BEHAVIOUR
When pause is triggered:
- Paddle movement stops
- Ball movement freezes
- Brick collisions stop processing
- The entire game state remains in memory
- No physics updates run until resumed
- Resume unfreezes everything exactly as it was


7. OPTIONAL EXTENSIONS SUPPORTED BY ARCHITECTURE
The code allows easy addition of:
- More levels
- New brick types
- Power-ups (extra life, paddle extension, slow ball)
- Sound effects
- New UI themes


		8. DOs — BEST PRACTICES
		✔ Use classes (`GamePanel`, `Ball`, `Paddle`, `Brick`, `BrickBreaker`)
		✔ Maintain modular code for physics and collision handling
		✔ Use meaningful variable and function names
		✔ Add new features inside their respective classes
		✔ Test each level separately
		✔ Keep UI elements aligned and readable
		✔ Maintain the same control keys if extending the game

		9. DON'Ts — RESTRICTIONS / WARNINGS
		✖ Do not delete the `GamePanel` class (core renderer)
		✖ Do not run the game without calling `main()` from `BrickBreaker`
		✖ Do not modify ball physics inside the `paintComponent()` method
		✖ Do not block the game loop with long operations
		✖ Do not use keys already assigned (unless replacing intentionally)
		✖ Do not remove the pause state logic (`isPaused`)
		✖ Do not place heavy assets in the constructor (load lazily instead)
		

10. TESTING CHECKLIST USED
- Paddle responds to left/right keys
- Ball bounces off walls and paddle correctly
- Bricks break on correct hit count
- Score updates accurately
- Lives reduce when ball falls below paddle
- Level advances when all bricks are cleared
- Pause freezes everything, resume restores state
- Game-Over screen displays final score
- Restart resets lives, score, and level


11. FILE STRUCTURE IN PROJECT
BrickBreaker/
		│ BrickBreaker.java  → main entry point
		│ GamePanel.java     → game rendering & loop
		│ Ball.java          → ball physics
		│ Paddle.java        → paddle controller
		│ Brick.java         → brick object
		│ README.txt         → instructions (this file)
		
		
		
		
		

GAME TWO
    ------------------------------------------------------------------------
    SNAKE GAME — COURSEWORK PROJECT
    Platform: Java (Swing GUI)
    Run with: BlueJ
    ------------------------------------------------------------------------

    1. GAME DESCRIPTION
    This is a level-based Snake game where the player controls a growing snake to eat food and score points.
    The game ends when the snake hits a wall, itself, or an obstacle.
    There are 4 levels, and the speed increases as the player progresses.
    The game includes a full pause system that freezes all movement and game logic.
     
    Youtube video demonstrating how to play this game

	https://youtu.be/7-k_1IQLcIs?si=WtHKa-pYpETHNX1o
   
    

    2. HOW TO RUN IN BLUEJ
    a) Open BlueJ
    b) Click: Project → Open Project
    c) Select the Snake Game folder
    d) Right-click `SnakeGame` class
    e) Choose: `void main(String[] args)`
    f) Click OK to start the game
    
    

    3. GAME CONTROLS
    ←  Left Arrow  : Move snake left
    →  Right Arrow : Move snake right
    ↑  Up Arrow    : Move snake up
    ↓  Down Arrow  : Move snake down
    SPACEBAR       : Pause / Resume the game
    R              : Restart the game on Game-Over screen
    ESC            : Quit the game instantly
    

    4. SCORING SYSTEM
    Food eaten = 10 points
    Level bonus = 50 points added at each level completion
    Milestone reward = Extra 30 points when score reaches multiples of 200
    

    5. LEVEL RULES & SPEED
    Level 1 : No obstacles, slow speed (relaxed gameplay)
    Level 2 : Medium speed, few obstacles appear
    Level 3 : Fast speed, more obstacles added
    Level 4 : Very fast speed, tight grid challenge
    
    

    6. GAME RULES
    - Use arrow keys to control direction.
    - The snake grows longer every time food is eaten.
    - You lose if:
      • Snake hits the screen boundary (wall)
      • Snake hits itself
      • Snake hits an obstacle
    - You progress to the next level by reaching the required score target.
    - Pause freezes:
      • Snake movement
      • Ball movement
      • Collision detection
      • Score updates
      • Entire game loop logic
      

            7. DOs — GOOD PRACTICES
            ✔ Keep class names exactly as they are (`SnakeGame`, `GamePanel`, `Snake`)
            ✔ Store image assets inside the project folder
            ✔ Use PNG format for graphics
            ✔ Add new features inside the correct class (movement → `Snake`, UI → `GamePanel`)
            ✔ Test each level before submission
            ✔ Maintain the pause system if modifying speed or levels

            8. DON'Ts — AVOID THESE
            ✖ Do not rename or delete `GamePanel.java`
            ✖ Do not place long-running tasks inside the game loop
            ✖ Do not remove the `paused` state logic
            ✖ Do not use keys already assigned unless replacing them intentionally
            ✖ Do not hardcode obstacle positions inside `paintComponent()`
            ✖ Do not edit physics logic inside rendering methods
    

    9. FILES REQUIRED IN PROJECT FOLDER
    Snake Game/
    │ SnakeGame.java     → Main entry point
    │ GamePanel.java     → UI, levels, game loop
    │ Snake.java         → Snake movement, growth, collisions
    │ snake_head.png     → Snake head graphic
    │ snake_tail.png     → Snake tail graphic
    │ snake_highscore.dat (auto-generated) → stores high score
    │ README.txt         → (this file)
    

    10. TESTING CHECKLIST
    - Snake moves correctly using arrows
    - Snake grows when food is eaten
    - Score updates accurately
    - Pause freezes all movement and logic
    - Resume restores state exactly
    - Obstacles increase per level
    - Game ends on self/wall/obstacle collision
    - Restart resets score, snake, and level
    - High score saves and reloads



    11. AUTHOR
    Mugundho Robin Percy
	TWINAMATSIKO PATRICK
    Software Engineering StudentS — Victoria University
    






	
	
	
	
	
	
	
	
	


