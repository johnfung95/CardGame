# Card Game Project

Duration: approximately 2.5 md

# Spec
- can read build.gradle for detail
Android Emulator: Pixel 5  API
SDK version: 21
---

- Player will be drawing cards againsts a robot and try to get higher marks to win.
- The scoring system is as below:
	- Pair of A = 100 points
	- Pair of K,Q,J = 50 points
	- Pair of 10 = 20 points
	- Any other pairs = 10 points
	- Single Ace in the cards = 5 points
	- No pair of no Ace = -2 points
- Player and Robot will draw cards in turn, both scores will be calculated immediately.
- Player can choose to stop at any time of the process.
- When stopped or the cards are all drawn, the game will go to calculation of scores immediately.
- The one with the higher score wins.
---

# Features
- full deck of poker cards (i.e. 52 cards)
- animation on score calculation process
- animation for different ending scenarios (i.e. win, lose, draw)
- alert to user before the user chooses to stop the game
- import external package (confetti) for effects
---

# Improvements 
- can consider the use of audio effects at ending page
- improve the game to multiple players or robots
- use of design patterns (e.g. factory, interface) and refactor the program
- add new card drawing mechanisms in the future, (e.g. play cards like real poker)
- create animations and highlights when minus points to notify user of the hit.
