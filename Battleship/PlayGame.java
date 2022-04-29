package Project3;

import java.io.*;
import java.util.*;
/**
 *
 *
 * HW-03 Challenge -- VIN Generator
 *
 * This program accepts user input, does some processing, then outputs to the console.
 *
 * @author Shivli Agrawal, lab sec 10
 *
 * @version February 3, 2022
 *
 */

public class PlayGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, Welcome to Battleship!\nPlease enter a Game mode:\n1. Automated\n2. Active");
        int input = scan.nextInt();
        scan.nextLine();
        if (input == 1) {
            int playerOneHits = 0;
            int playerTwoHits = 0;
            int playerOneTurns = 0;
            int playerTwoTurns = 0;
            int hitNumber11 = 0;
            int hitNumber21 = 0;
            int hitNumber12 = 0;
            int hitNumber22 = 0;
            int hitNumber13 = 0;
            int hitNumber23 = 0;
            System.out.println("Enter the filename with the game data:");
            String newFileName = scan.nextLine();
            try {
                File input2 = new File(newFileName);
                BufferedReader br = new BufferedReader(new FileReader(input2));
                ArrayList<String> gameData = new ArrayList<>();
                String line = br.readLine();
                while (line != null) {
                    gameData.add(line);
                    line = br.readLine();
                }

                File input3 = new File("ShipPositionsPlayerOne.txt");
                BufferedReader br2 = new BufferedReader(new FileReader(input3));
                ArrayList<String> positionsOne = new ArrayList<>();
                String line2 = br2.readLine();
                while (line2 != null) {
                    positionsOne.add(line2);
                    line2 = br2.readLine();
                }

                File input4 = new File("ShipPositionsPlayerTwo.txt");
                BufferedReader br3 = new BufferedReader(new FileReader(input4));
                ArrayList<String> positionsTwo = new ArrayList<>();
                String line3 = br3.readLine();
                while (line3 != null) {
                    positionsTwo.add(line3);
                    line3 = br3.readLine();
                }

                ArrayList<String> tempOne = new ArrayList<>();
                ArrayList<String> tempTwo = new ArrayList<>();

                for (int i = 0; i < positionsOne.size(); i++) {
                    tempOne.set(i, new String(positionsOne.get(i)));
                }
                for (int i = 0; i < positionsTwo.size(); i++) {
                    tempTwo.set(i, new String(positionsTwo.get(i)));
                }

                for (int i = 0; i < gameData.size(); i += 4) {
                    char result = positionsTwo.get(gameData.get(i).charAt(0)
                            - 65).charAt(Integer.parseInt(gameData.get(i + 1)) - 1);
                    if (result == 'H') {
                        playerOneHits += 1;
                        positionsTwo.set(gameData.get(i).charAt(0) - 65, positionsTwo.get(gameData.get(i).charAt(0)
                                - 65).substring(0, Integer.parseInt(gameData.get(i + 1)) - 1) + 'X' +
                                positionsTwo.get(gameData.get(i).charAt(0)
                                        - 65).substring(Integer.parseInt(gameData.get(i + 1))));
                    }
                    playerOneTurns += 1;
                }

                for (int j = 2; j < gameData.size(); j += 4) {
                    char result1 = positionsOne.get(gameData.get(j).charAt(0)
                            - 65).charAt(Integer.parseInt(gameData.get(j + 1)) - 1);
                    if (result1 == 'H') {
                        playerTwoHits += 1;
                        positionsOne.set(gameData.get(j).charAt(0) - 65, positionsOne.get(gameData.get(j).charAt(0)
                                - 65).substring(0, Integer.parseInt(gameData.get(j + 1)) - 1) + 'X' +
                                positionsOne.get(gameData.get(j).charAt(0)
                                        - 65).substring(Integer.parseInt(gameData.get(j + 1))));
                    }
                    playerTwoTurns += 1;
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber11++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber21++;
                        }
                    }
                }

                for (int a = 3; a < 7; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber12++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber22++;
                        }
                    }
                }

                for (int a = 7; a < 10; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber13++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber23++;
                        }
                    }
                }
                String boardPatternOne;
                String boardPatternTwo;
                if (hitNumber11 >= 9) {
                    boardPatternOne = "Top Heavy";
                } else if (hitNumber12 >= 9) {
                    boardPatternOne = "Middle Heavy";
                } else if (hitNumber13 >= 9) {
                    boardPatternOne = "Bottom Heavy";
                } else {
                    boardPatternOne = "Scattered";
                }

                if (hitNumber21 >= 9) {
                    boardPatternTwo = "Top Heavy";
                } else if (hitNumber22 >= 9) {
                    boardPatternTwo = "Middle Heavy";
                } else if (hitNumber23 >= 9) {
                    boardPatternTwo = "Bottom Heavy";
                } else {
                    boardPatternTwo = "Scattered";
                }



                File f2 = new File("GameLog.txt");
                PrintWriter pw = new PrintWriter(new FileOutputStream(f2));
                GameLog gameLog;

                if (playerOneHits == 17) {
                    System.out.println("Enemy fleet destroyed. Congratulations player 1!");
                    gameLog = new GameLog(1, playerTwoHits, playerOneTurns, boardPatternOne,
                            boardPatternTwo);
                    pw.print(gameLog.toString());
                } else if (playerTwoHits == 17) {
                    System.out.println("Enemy fleet destroyed. Congratulations player 2!");
                    gameLog = new GameLog(2, playerOneHits, playerTwoTurns, boardPatternOne,
                            boardPatternTwo);
                    pw.print(gameLog.toString());
                }
                br.close();
                br2.close();
                br3.close();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (input == 2) {
            try {
                int playerOneHits = 0;
                int playerTwoHits = 0;
                int playerOneTurns = 0;
                int playerTwoTurns = 0;
                int hitNumber11 = 0;
                int hitNumber21 = 0;
                int hitNumber12 = 0;
                int hitNumber22 = 0;
                int hitNumber13 = 0;
                int hitNumber23 = 0;
                File input3 = new File("ShipPositionsPlayerOne.txt");
                BufferedReader br2 = new BufferedReader(new FileReader(input3));
                ArrayList<String> positionsOne = new ArrayList<>();
                String line2 = br2.readLine();
                while (line2 != null) {
                    positionsOne.add(line2);
                    line2 = br2.readLine();
                }

                File input4 = new File("ShipPositionsPlayerTwo.txt");
                BufferedReader br3 = new BufferedReader(new FileReader(input4));
                ArrayList<String> positionsTwo = new ArrayList<>();
                String line3 = br3.readLine();
                while (line3 != null) {
                    positionsTwo.add(line3);
                    line3 = br3.readLine();
                }

                ArrayList<String> tempOne = new ArrayList<>();
                ArrayList<String> tempTwo = new ArrayList<>();

                for (int i = 0; i < positionsOne.size(); i++) {
                    tempOne.set(i, new String(positionsOne.get(i)));
                }
                for (int i = 0; i < positionsTwo.size(); i++) {
                    tempTwo.set(i, new String(positionsTwo.get(i)));
                }

                int turn = 1;

                while (true) {
                    if (turn == 1) {
                        System.out.println("Player 1 - Enter a row letter from A - J");
                        char row = scan.nextLine().charAt(0);
                        System.out.println("Player 1 - Enter a column number from 1 - 14");
                        int col = scan.nextInt();
                        scan.nextLine();
                        playerOneTurns++;
                        if (positionsTwo.get(row - 65).charAt(col - 1) == 'H') {
                            playerOneHits++;
                            positionsTwo.set(row - 65, positionsTwo.get(row - 65).substring(0, col - 1) + "X" +
                                    positionsTwo.get(row - 65).substring(col));
                            System.out.println("Value:H");
                        } else {
                            System.out.println("Value:M");
                        }
                        turn = 2;
                    } else {
                        System.out.println("Player 2 - Enter a row letter from A - J");
                        char row = scan.nextLine().charAt(0);
                        System.out.println("Player 2 - Enter a column number from 1 - 14");
                        int col = scan.nextInt();
                        scan.nextLine();
                        playerTwoTurns++;
                        if (positionsOne.get(row - 65).charAt(col - 1) == 'H') {
                            playerTwoHits++;
                            positionsOne.set(row - 65, positionsOne.get(row - 65).substring(0, col - 1) + "X" +
                                    positionsOne.get(row - 65).substring(col));
                            System.out.println("Value:H");
                        } else {
                            System.out.println("Value:M");
                        }
                        turn = 1;
                    }
                    if (playerOneHits == 17 || playerTwoHits == 17) {
                        break;
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber11++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber21++;
                        }
                    }
                }

                for (int a = 3; a < 7; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber12++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber22++;
                        }
                    }
                }

                for (int a = 7; a < 10; a++) {
                    for (int b = 0; b < 14; b++) {
                        char hitNum = tempOne.get(a).charAt(b);
                        if (hitNum == 'H') {
                            hitNumber13++;
                        }
                        char hitNum2 = tempTwo.get(a).charAt(b);
                        if (hitNum2 == 'H') {
                            hitNumber23++;
                        }
                    }
                }
                String boardPatternOne;
                String boardPatternTwo;
                if (hitNumber11 >= 9) {
                    boardPatternOne = "Top Heavy";
                } else if (hitNumber12 >= 9) {
                    boardPatternOne = "Middle Heavy";
                } else if (hitNumber13 >= 9) {
                    boardPatternOne = "Bottom Heavy";
                } else {
                    boardPatternOne = "Scattered";
                }

                if (hitNumber21 >= 9) {
                    boardPatternTwo = "Top Heavy";
                } else if (hitNumber22 >= 9) {
                    boardPatternTwo = "Middle Heavy";
                } else if (hitNumber23 >= 9) {
                    boardPatternTwo = "Bottom Heavy";
                } else {
                    boardPatternTwo = "Scattered";
                }



                File f2 = new File("GameLog.txt");
                PrintWriter pw = new PrintWriter(new FileOutputStream(f2));
                GameLog gameLog;

                if (playerOneHits == 17) {
                    System.out.println("Enemy fleet destroyed. Congratulations player 1!");
                    gameLog = new GameLog(1, playerTwoHits, playerOneTurns, boardPatternOne,
                            boardPatternTwo);
                    pw.print(gameLog.toString());
                } else if (playerTwoHits == 17) {
                    System.out.println("Enemy fleet destroyed. Congratulations player 2!");
                    gameLog = new GameLog(2, playerOneHits, playerTwoTurns, boardPatternOne,
                            boardPatternTwo);
                    pw.print(gameLog.toString());
                }
                br2.close();
                br3.close();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
