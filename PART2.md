# Part 2

## A. Member Contributions

No | ID         | Name | Task descriptions | Contribution %
-- | ---------- | ---- | ----------------- | --------------
1  | 1211102687 | Emily Phang Ru Ying | Deal 7 cards to each of the 4 players, players can only play cards that follow the suit or rank of the lead card,Reset the game, Exit the game, Finish a round of game correctly and Display the score of each player |25
2  | 1211102751 | Teo Yu Jie | Calculate winner of the trick by seeing which player played the highest card with the same suit as the lead card, Set winner of the trick as the one who leads the next card, Save and Resume the Game |25
3  | 1211102753 | Lim Cai Qing | First card in deck is the first lead card and placed at center, first lead card determines the first player, When the remaining deck is exhausted and the player cannot play,                 |
   | the player does not play in the trick|25
4  | 1211102975 | Loi Xinyi | Cards are faced up, generate a new game with randomzed 52 cards,If a player cannot follow suit or rank, the player must draw from the deck       |
   | until a card can be played.|25


## B. Part 1 Feature Completion (Latest)

Mark Y for Complete, P for Partial done, N for Not implemented.

No | Feature                                                                         | Completed (Y/P/N)
-- | ------------------------------------------------------------------------------- | -----------------
1  | All cards should be faced up to facilitate checking.                            |          Y
2  | Start a new game with randomized 52 cards.                                      |          Y
3  | The first card in the deck is the first lead card and is placed at the center.  |          Y
4  | The first lead card determines the first player.                                |          Y
5  | Deal 7 cards to each of the 4 players.                                          |          Y
6  | All players must follow the suit or rank of the lead card.                      |          Y
7  | The highest-rank card with the same suit as the lead card wins the trick.       |          Y
8  | The winner of a trick leads the next card.                                      |          Y


## C. Part 2 Feature Completion

Mark Y for Complete, P for Partial done, N for Not implemented.

No | Feature                                                                          | Completed (Y/P/N)
-- | -------------------------------------------------------------------------------- | -----------------
1  | If a player cannot follow suit or rank, the player must draw from the deck       |      Y
   | until a card can be played.                                                      |
2  | When the remaining deck is exhausted and the player cannot play,                 |      Y
   | the player does not play in the trick.                                           |
3  | Finish a round of game correctly. Display the score of each player.              |      Y
4  | Can exit and save the game (use file or database).                               |      Y
5  | Can resume the game. The state of the game is restored when resuming a game      |      P
   | (use file or database).                                                          |
6  | Reset the game. All scores become zero. Round and trick number restart from 1.   |      Y
7  | Support GUI playing mode (cards should be faced up or down as in the real game). |      N
   | The GUI can be in JavaFX, Swing, Spring, or Android.                             |
8  | Keep the console output to facilitate checking.                                  |      N
   | The data in console output and the GUI must tally.                               |


## D. Link to Part 2 GitHub Repo

https://github.com/EmilyPhang/OOPDS-Go-Boom-Game

