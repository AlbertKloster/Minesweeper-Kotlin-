# Stage 4/5: Prepare for battle
## Description
We managed to create the minefield and fill it with clues: now it's time to play! Let's give our player the opportunity to guess where the mines are with the help of our hints.

All the numbers are still shown to the player, but now the mines are not. To win, the player must find all the mines on the field by marking them.

Update the field input and add the coordinate grid like in the examples so that the player can mark cells by entering their coordinates.

## Objectives
Your upgraded program should meet the following requirements:

1. After initializing the field, all the numbers are shown to the player, but not the positions of the mines.

2. The player sees the message “`Set/delete mine marks (x and y coordinates):`” and enters two numbers as coordinates on the field.

3. The user input is treated according to the rules:

   1. If the player enters the coordinates of a non-marked cell, the program marks the cell, which means that the player thinks a mine is located there.

   2. If the player enters the coordinates of a cell with a number, the program should print the message “`There is a number here!`” and ask the player again without printing the minefield, since cells with numbers are guaranteed to be free of mines.

   3. If the player enters the coordinates of a marked cell, the cell becomes unmarked. This is necessary because the game ends only if all the marks are correct, but the player can mark more cells than there are mines.

4. After successfully marking or unmarking a cell, the new minefield state is printed. The symbol `.` is used to represent non-marked cells, and `*` is for marked ones. The prompt for the player's next move is printed until the game is finished.

5. When the player marks all the mines correctly without marking any empty cells, they win and the game ends. If the player has marked extra cells that do not contain mines, they continue playing. After clearing all the excess mine marks, the player wins. Print the message “`Congratulations! You found all the mines!`” after the final field state.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<b>Example 1:</b> <i>the user enters the coordinates of a cell that contains a number</i>
```
How many mines do you want on the field? > 5

│123456789│
—│—————————│
1│.111.....│
2│.1.1.....│
3│.1221....│
4│..1.1....│
5│.1221....│
6│.1.21....│
7│.12.1....│
8│..1221...│
9│...1.1...│
—│—————————│
Set/delete mines marks (x and y coordinates): > 2 1
There is a number here!
Set/delete mines marks (x and y coordinates): > 3 2

│123456789│
—│—————————│
1│.111.....│
2│.1*1.....│
3│.1221....│
4│..1.1....│
5│.1221....│
6│.1.21....│
7│.12.1....│
8│..1221...│
9│...1.1...│
—│—————————│
Set/delete mines marks (x and y coordinates): > 4 4

│123456789│
—│—————————│
1│.111.....│
2│.1*1.....│
3│.1221....│
4│..1*1....│
5│.1221....│
6│.1.21....│
7│.12.1....│
8│..1221...│
9│...1.1...│
—│—————————│
Set/delete mines marks (x and y coordinates): > 3 6

│123456789│
—│—————————│
1│.111.....│
2│.1*1.....│
3│.1221....│
4│..1*1....│
5│.1221....│
6│.1*21....│
7│.12.1....│
8│..1221...│
9│...1.1...│
—│—————————│
Set/delete mines marks (x and y coordinates): > 4 7

│123456789│
—│—————————│
1│.111.....│
2│.1*1.....│
3│.1221....│
4│..1*1....│
5│.1221....│
6│.1*21....│
7│.12*1....│
8│..1221...│
9│...1.1...│
—│—————————│
Set/delete mines marks (x and y coordinates): > 5 9

│123456789│
—│—————————│
1│.111.....│
2│.1*1.....│
3│.1221....│
4│..1*1....│
5│.1221....│
6│.1*21....│
7│.12*1....│
8│..1221...│
9│...1*1...│
—│—————————│
Congratulations! You found all the mines!
```

<b>Example 2:</b> <i>the user wins after removing excessive mine marks</i>
```
How many mines do you want on the field? > 1

│123456789│
—│—————————│
1│.........│
2│.........│
3│.........│
4│....111..│
5│....1.1..│
6│....111..│
7│.........│
8│.........│
9│.........│
—│—————————│
Set/delete mines marks (x and y coordinates): > 1 1

│123456789│
—│—————————│
1│*........│
2│.........│
3│.........│
4│....111..│
5│....1.1..│
6│....111..│
7│.........│
8│.........│
9│.........│
—│—————————│
Set/delete mines marks (x and y coordinates): > 6 5

│123456789│
—│—————————│
1│*........│
2│.........│
3│.........│
4│....111..│
5│....1*1..│
6│....111..│
7│.........│
8│.........│
9│.........│
—│—————————│
Set/delete mines marks (x and y coordinates): > 1 1

│123456789│
—│—————————│
1│.........│
2│.........│
3│.........│
4│....111..│
5│....1*1..│
6│....111..│
7│.........│
8│.........│
9│.........│
—│—————————│
Congratulations! You found all the mines!
```
