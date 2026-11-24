# Minesweeper Console

[![Java](https://img.shields.io/badge/Java-100%25-orange.svg)](https://www.java.com/)
[![License](https://img.shields.io/badge/license-not%20specified-lightgrey.svg)](https://github.com/N4DU/buscaminas-consola)
[![GitHub last commit](https://img.shields.io/github/last-commit/N4DU/buscaminas-consola)](https://github.com/N4DU/buscaminas-consola/commits/main)
[![GitHub repo size](https://img.shields.io/github/repo-size/N4DU/buscaminas-consola)](https://github.com/N4DU/buscaminas-consola)

A configurable Minesweeper game written in Java and played from the command line.

The program builds a square or rectangular grid, randomly places mines according to a selected difficulty level, and lets the player reveal cells or mark them with a flag. Empty areas are expanded automatically, including diagonally, until they reach numbered cells.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Install](#install)
- [Usage](#usage)
- [Controls](#controls)
- [Board Symbols](#board-symbols)
- [Difficulty Levels](#difficulty-levels)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Features

- Custom grid width and height
- Five difficulty levels
- Random mine placement
- Numbers indicating nearby mines
- Recursive revealing of empty areas
- Diagonal expansion
- Mine flags
- Automatic victory detection
- Minefield reveal after winning or losing
- Coordinate labels and dynamically aligned console output

## Requirements

- A Java Development Kit (JDK)
- A terminal or IDE with console input/output

Check your Java installation:

```sh
java -version
javac -version
```

## Install

Clone the repository:

```sh
git clone https://github.com/N4DU/buscaminas-consola.git
cd buscaminas-consola
```

Compile the source file:

```sh
javac Buscaminas.java
```

Alternatively, open the repository in a Java IDE and run the `Buscaminas` class.

## Usage

Start the game:

```sh
java Buscaminas
```

The program will request:

1. The grid width
2. The grid height
3. A difficulty level from `1` to `5`

Only positive integer dimensions are accepted.

Example setup:

```text
Ancho cuadrícula: 10
Alto cuadrícula: 10
Nivel (1-5): 3
```

The interface and prompts are currently in Spanish.

## Controls

Enter a column and row separated by any text or whitespace:

```text
(x) (y) si/no:
3 4
```

Coordinates are shown by the board and begin at `1`.

To place or toggle a mine flag, add `si` to the input:

```text
3 4 si
```

Without `si`, the selected cell is revealed.

## Board Symbols

| Symbol | Meaning |
|---|---|
| `[]` | Hidden, unrevealed cell |
| `0` | Empty revealed cell with no adjacent mines |
| `1`–`8` | Number of mines adjacent to the cell |
| `X` | Mine or flagged cell |

When the game ends, every unrevealed safe cell is replaced by its adjacent-mine count and every mine is shown with `X`.

## Difficulty Levels

The mine count is randomly selected from a percentage range. The final mine count is rounded to the nearest whole number and is based on the total number of grid cells.

| Level | Approximate mine percentage |
|---:|---:|
| 1 | 5%–8% |
| 2 | 10%–13% |
| 3 | 15%–17% |
| 4 | 20%–23% |
| 5 | 27%–33% |

Mine placement is repeated until the rounded number of mines has been placed. The same cell cannot contain more than one mine.

## Project Structure

```text
buscaminas-consola/
└── Buscaminas.java
```

The project currently consists of a single Java source file.

## How It Works

- `main()` initializes the scanner, grid dimensions, difficulty, visible board, and solution board.
- `llenarArraySolucion()` selects the mine percentage and places mines randomly.
- `mostrarArrayVisible()` renders the visible board with aligned X and Y coordinates.
- `pedirCoordenadas()` reads the user's coordinates and optional flag command.
- `CantBombasAlrededor()` counts mines in the eight neighboring positions.
- `limpiarAlrededor()` recursively reveals empty connected areas and diagonal neighbors.
- `modificarArrayVisible()` reveals a cell or places a flag.
- `comprobarGanaste()` detects whether every safe cell is revealed.
- `mostrarSolucion()` reveals mines and nearby-mine counts at the end of the game.

The player wins by revealing every cell that does not contain a mine. Revealing a mine ends the game in a loss.

## Contributing

Contributions, bug reports, and feature suggestions are welcome.

1. Fork the repository.
2. Create a focused feature or fix branch.
3. Commit your changes with a descriptive message.
4. Open a pull request explaining what you changed and why.

Please keep changes consistent with the existing console-based Java implementation.

## License

No license has been specified for this repository. All rights remain with the author unless a license is added.

## Author

Created by [N4DU](https://github.com/N4DU).
