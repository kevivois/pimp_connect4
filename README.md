# Puissance 4 Game Project

## Overview

This Scala project implements the classic game Puissance 4 (Connect Four) with a graphical user interface using FunGraphics. The primary goal is to create an interactive and visually appealing version of the Puissance 4 game where two players take turns placing colored discs in a grid.

## Classes and Objects

### 1. Game Object

The `Game` object is the central component of the Puissance 4 game, responsible for managing the game state, user interactions, and the graphical interface. It orchestrates the interactions between different elements and represents the overall game logic.

### 2. Circle Class

The `Circle` class represents an individual colored circle used in the game. Circles are part of the game grid and serve as the visual elements that players interact with during the game.

### 3. CircleColumn Class

The `CircleColumn` class models a vertical column in the game grid. It consists of an array of circles, and its purpose is to manage the placement and state of circles within the column. This class is crucial for handling player moves within a specific column.

### 4. CustomMouseListener Class

The `CustomMouseListener` class is a custom implementation of the `MouseListener` interface. It listens for mouse events, such as clicks, and triggers corresponding actions in the game. It is responsible for detecting user input and deciding whether to allow players to choose colors or make moves based on the timing of clicks.

## Usage

To play the Puissance 4 game:

1. Run the `Main` object.
2. The graphical interface will be displayed.
3. Players take turns clicking on the game grid to drop their colored discs.
4. Connect four discs vertically, horizontally, or diagonally to win the game.
5. Optionally, players can click rapidly to set their preferred colors at the beginning of the game.

Enjoy playing Puissance 4!
