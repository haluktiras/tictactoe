# TicTacToe Game #
by Mustafa Haluk Tiras

# CircleCI #
[![CircleCI](https://circleci.com/gh/haluktiras/event-processor.svg?style=svg)](https://app.circleci.com/pipelines/github/haluktiras/tictactoe)

## Test Coverage
[![codecov](https://codecov.io/gh/haluktiras/tictactoe/branch/feature/docker-implementation/graph/badge.svg?token=C5PJBT3IXL)](https://codecov.io/gh/haluktiras/tictactoe)

## Getting started ##
For the matter of dockerizing the project in local, type the following command to the console.

```
docker build -t myimage:latest .
```
For running;
```
docker run -it myimage
```

Apart from these, circleci is implemented for dockerizing purpose. Once something has pushed to master branch, 
circleci will have built the project and run all tests and get the coverage then upload them to codecov.com.

### Prerequisites ###
* JDK 1.8
* Maven 4

### Product BackLog ###
* [x] Implement CircleCI.
* [x] Implement Testing Framework (JUnit 4).
* [x] Implement Mockito.
* [x] Create the gaming board.
* [x] User Interactions and Input Scanners.
* [x] BOT implementation based on random interactions.
* [x] Drawing boards after each round.
* [x] Thread 2 sec sleep implementation after each round.
* [x] Dockerize the project.

