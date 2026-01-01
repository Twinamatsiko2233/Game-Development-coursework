
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
		
		
		
		
		

	12. AUTHOR
	Mugundho Robin Percy
	TWINAMATSIKO PATRICK
	Software Engineering Students — Victoria University
	
	
	
	
	
	
	
	
	

