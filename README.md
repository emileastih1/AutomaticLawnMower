# MowItNow - Automatic Lawn Mower

## Project Overview

MowItNow has developed an automatic lawn mower designed for rectangular surfaces. The mower can be programmed to navigate the entire surface using a set of simple commands. This project implements the specification for the mower's movement in a Java program, utilizing **Craftsmanship**, **Clean Code**, and **SOLID principles** with **TDD** (Test Driven Development) and **DDD** (Domain Driven Design).

## Problem Description

The mower's position is represented by a combination of coordinates `(x, y)` and a cardinal direction `(N, E, W, S)`. The lawn is divided into a grid to facilitate navigation.

### Initial Conditions

- Example initial position: `0, 0, N` (bottom-left corner of the lawn, facing North).
- The mower can receive commands to rotate or move:
    - `D`: Rotate 90° to the right (clockwise).
    - `G`: Rotate 90° to the left (counterclockwise).
    - `A`: Move forward by one grid square in the current direction.

### Movement Constraints

- If a movement would cause the mower to move outside the grid, it will ignore the command and remain in place, but will still process the next command.

### Input Format

A text file provides the following inputs:
1. The first line specifies the upper-right corner coordinates of the lawn (the bottom-left corner is assumed to be `0,0`).
2. For each mower deployed:
    - The first line contains the initial position and orientation of the mower (two integers and a letter separated by spaces).
    - The second line contains a sequence of instructions for the mower to follow (a series of `D`, `G`, and `A` without spaces).

### Execution Flow

Mowers execute their instructions sequentially. After completing its instructions, each mower reports its final position and orientation.

### Example Input

```plaintext
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
```

### Ouput
The output will provide the final position and orientation of each mower after processing all instructions.
```plaintext
1 3 N
5 1 E
```

### Objective
The objective of this project is to:

- Design and implement a Java program that:
    - Reads the input file.
    - Simulates the mowers' movements on the grid.
    - Outputs the final position and orientation of each mower.
- Follow TDD (Test Driven Development) by creating tests before implementation, ensuring that the solution adheres to Clean Code principles and SOLID design.
- Apply Craftsmanship practices to deliver a solution that is maintainable, testable, and extensible.

## Requirements

- Java Development Kit (JDK) 21 or higher
- Maven
- Git (optional, for cloning the repository)

## Running the Program

#### Clone the repository:
```bash 
git clone https://github.com/emileastih1/AutomaticLawnMower.git
```
#### Compile the program:
```bash  
mvn clean install
```
#### Run the program with an input file:
```bash  
java -jar target/AutomaticLawnMower.jar mowing-instructions.txt
```
