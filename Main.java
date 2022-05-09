package minesweeper;
import java.util.Scanner;

public class Main {
    // arr is the original array
    public static char[][] arr = new char[9][9];
    // arr1 contain the modification
    public static char[][] arr1 = new char[9][9];
    public static void main(String[] args) {
        // write your code here
        intilialize();
        fill();
        calcul();
        display();
        play();
    }
    
    public static int count1() {
        int nb = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr1[i][j] != '.' && arr1[i][j] != 'X') {
                    nb++;
                } else if (arr[i][j] == '/') {
                    nb++;
                }
            }
        }
        return 81 - nb;
    }
    
    public static int count() {
        int nb = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 'X') {
                    nb++;
                }
            }
        }
        return nb;
    }
    
    public static void play() {
        Scanner sc = new Scanner(System.in);
        int x, y, nb = 0, val = count();
        String s;
        while (true) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            x = sc.nextInt();
            y = sc.nextInt();
            s = sc.next();
            x--;
            y--;
            if (s.equals("free")) {
                if (arr[y][x] == '.') {
                    floodFill(arr, new boolean[arr.length][arr[0].length], y, x);
                } else if (arr[y][x] >= '1' && arr[y][x] <= '8') {
                    arr1[y][x] = arr[y][x];
                } else if (arr1[y][x] == 'X') {
                    display1();
                    System.out.println("You stepped on a mine and failed!");
                    break;
                }
                display();
            } else if (s.equals("mine")) {
                if (arr1[y][x] == '*') {
                    arr1[y][x] = '.';
                } else if (arr[y][x] == '*') {
                    if (arr1[y][x] == 'X') {
                        nb--;
                        arr[y][x] = 'X';
                    } else {
                        arr[y][x] = '.';
                    }
                } else if (arr[y][x] == 'X') {
                    nb++;
                    arr[y][x] = '*';
                } else if (arr[y][x] >= '1' && arr1[y][x] <= '8') {
                    arr1[y][x] = '*';
                }else {
                    arr[y][x] = '*';
                }
                display();
            }
            // display();
            if (nb == val || count1() == val) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }
    }
    
    public static void display() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 9; j++) {
                if (arr1[i][j] != '.' && arr1[i][j] != 'X') {
                    System.out.print(arr1[i][j]);
                } else if (arr[i][j] == '/' || arr[i][j] == '*') {
                    System.out.print(arr[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
    
    public static void display1() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < 9; j++) {
                if (arr1[i][j] != '.') {
                    System.out.print(arr1[i][j]);
                } else if (arr[i][j] == '/') {
                    System.out.print("/");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
    
    public static void intilialize() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = '.';
                arr1[i][j] = '.';
            }
        }
    }
    
    public static void fill() {
        System.out.print("How many mines do you want on the field? ");
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        int row, column;
        for (int i = 0; i < nb; i++) {
            row = (int)(Math.random() * 9);
            column = (int)(Math.random() * 9);
            if (arr[row][column] == 'X') {
                i--;
            } else {
                arr[row][column] = 'X';
                arr1[row][column] = 'X';
            }
        }
    }
    
    public static void calcul() {
        int c = 0;
        for (int i = 0; i < 9; i++) {
            c = 0;
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] != 'X') {
                    if (i == 0 && j == 0) {
                        if (arr[i + 1][j] == 'X') {
                            c++;
                        }
                        if (arr[i][j + 1] == 'X') {
                            c++;
                        }
                        if (arr[i + 1][j + 1] == 'X') {
                            c++;
                        }
                        if (c > 0) {
                            arr[i][j] = (char)(c + '0');
                        }
                    }
                    c = 0;
                    if (i == 0 && j == 8) {
                        if (arr[i][j - 1] == 'X') {
                            c++;
                        }
                        if (arr[i + 1][j] == 'X') {
                            c++;
                        }
                        if (arr[i + 1][j - 1] == 'X') {
                            c++;
                        }
                        if (c > 0) {
                            arr[i][j] = (char)(c + '0');
                        }
                    }
                    c = 0;
                    if (i == 8 && j == 0) {
                        if (arr[i - 1][j] == 'X') {
                            c++;
                        }
                        if (arr[i][j + 1] == 'X') {
                            c++;
                        }
                        if (arr[i - 1][j + 1] == 'X') {
                            c++;
                        }
                        if (c > 0) {
                            arr[i][j] = (char)(c + '0');
                        }
                    }
                    c = 0;
                    if (i == 8 && j == 8) {
                        if (arr[i - 1][j] == 'X') {
                            System.out.println("test 1");
                            c++;
                        }
                        if (arr[i][j - 1] == 'X') {
                            System.out.println("test 2");
                            c++;
                        }
                        if (arr[i - 1][j - 1] == 'X') {
                            System.out.println("test 3");
                            c++;
                        }
                        if (c > 0) {
                            arr[i][j] = (char)(c + '0');
                        }
                    }
                    if (!(i == 0 && j == 0) && !(i == 8 && j == 8) && !(i == 8 && j == 0) && !(i == 0 && j == 8)) {  
                        if (j - 1 >= 0 && arr[i][j - 1] == 'X') {
                            c++;
                        }
                        if (j + 1 <= 8 && arr[i][j + 1] == 'X') {
                            c++;
                        }
                        if (i - 1 >= 0 && arr[i - 1][j] == 'X') {
                            c++;
                        }
                        if (i + 1 <= 8 && arr[i + 1][j] == 'X') {
                            c++;
                        }
                        if (i - 1 >= 0 && j + 1 <= 8 && arr[i - 1][j + 1] == 'X') {
                            c++;
                        }
                        if (i - 1 >= 0 && j - 1 >= 0 && arr[i - 1][j - 1] == 'X') {
                            c++;
                        }
                        if (i + 1 <= 8 & j + 1 <= 8 && arr[i + 1][j + 1] == 'X') {
                            c++;
                        }
                        if (i + 1 <= 8 && j - 1 >= 0 && arr[i + 1][j - 1] == 'X') {
                            c++;
                        }
                        if (i != 4 && j != 4) {
                            if (c > 5) {
                                arr[i][j] = '5';
                            } else if (c > 0) {
                                arr[i][j] = (char)(c + '0');
                            }
                        } else if (c > 0) {
                            arr[i][j] = (char)(c + '0');
                        }
                    }
                }
            }
        }
    }
    
    public static void floodFill(char[][] grid,boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }
        if (visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        if (arr1[r][c] == 'X') {
            return;
        }
        
        if (grid[r][c] >= '1' && grid[r][c] <= '8') {
            arr1[r][c] = grid[r][c];
            return;
        }
        
        if (grid[r][c] == '.' || grid[r][c] == '*') {
            grid[r][c] = '/';
        }
        floodFill(grid, visited, r, c + 1);
        floodFill(grid, visited, r, c - 1);
        floodFill(grid, visited, r + 1, c);
        floodFill(grid, visited, r - 1, c);
        floodFill(grid, visited, r + 1, c + 1);
        floodFill(grid, visited, r + 1, c - 1);
        floodFill(grid, visited, r - 1, c - 1);
        floodFill(grid, visited, r - 1, c + 1);
    }
}
