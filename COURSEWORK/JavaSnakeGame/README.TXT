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
    





