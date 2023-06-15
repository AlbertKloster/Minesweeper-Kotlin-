# Stage 3/5: Look around you
## Description
The player needs hints to be able to win, and we want them to have a chance to win! Let's show the number of mines around the empty cells so that our players have something to work with.

## Objectives
As in the previous step, you need to initialize the field with mines. Then, calculate how many mines there are around each empty cell. Check 8 cells if the current cell is in the middle of the field, 5 cells if it's on the side, and 3 cells if it's in the corner.

If there are mines around the cell, display the number of mines (from 1 to 8) instead of the symbol representing an empty cell. The symbols for empty cells and mines stay the same.

Check all the possibilities carefully.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

### Example 1:
```
How many mines do you want on the field? > 10
.........
.111111..
.1X22X211
.112X33X1
...12X211
....1221.
..1111X1.
123X1222.
1XX211X1.
```

### Example 2:
```
How many mines do you want on the field? > 15
1221.....
2XX21....
X34X2..11
112X2..2X
11211..3X
1X1....2X
12321..11
12XX11232
X22211XXX
```

### Example 3:
```
How many mines do you want on the field? > 20
.2X3X23XX
13X43X3X3
1X3X32211
2232X1...
2X2221...
X32X1..11
X32331.1X
X21XX2.22
1113X2.1X
```
