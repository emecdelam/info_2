package basics;
import java.util.*;
public class FourInARow {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final ArrayList<ArrayList<Character>> BOARD = new ArrayList<>();
    private static final ArrayList<Line> LINKS = new ArrayList<>();
    private static final char EMPTY = '-';
    private static final char[] PLAYERS = {'X', 'O'};
    public FourInARow() {
        for (int i = 0; i < COLUMNS; i++) {
            ArrayList<Character> line = new ArrayList<>();
            for (int j = 0; j < ROWS; j++) {
                line.add(EMPTY);
            }
            BOARD.add(line);
        }
    }
    /**
     * Play a piece in column j for the given player.
     * @param j the column index
     * @param player the player (X or O)
     * @throws IllegalArgumentException if j is not a valid column index or if the column is full or if the player is not X or O
     */
    public void play(int j, char player) {
        if (j < 0 || j >= COLUMNS) {
            throw new IllegalArgumentException();
        }
        if (!(new String(PLAYERS).contains(String.valueOf(player)))) {
            throw new IllegalArgumentException();
        }
        ArrayList<Character> column = BOARD.get(j);
        int current = 0;
        boolean done = true;
        Coordinate coordinate = null;
        while (current < ROWS && done){
            if ( column.get(current) == EMPTY){
                column.set(current,player);
                done = false;
                coordinate = new Coordinate(current,j,player);
            }
            current++;
        }
        if (done){
            throw new IllegalArgumentException();
        } boolean added = false;
        for (Line l : LINKS) {
            if (l.canAddCoordinate(coordinate)){
                l.addCoordinate(coordinate);
                added = true;
            }
        }
        if (!added){
            LINKS.add(new Line(coordinate));
        }
    }
    /**
     * Returns true if the player has won the game.
     * @param player the player (X or O)
     * @return true if the player has won the game
     * @throws IllegalArgumentException if the player is not X or O
     */
    public boolean hasWon(char player) {
        for (Line l : LINKS){
            if (l.getType() == player && l.getLength() >= 4){
                return true;
            }
        }
        return false;
    }
}

class Line {
    private Integer length;
    private final ArrayList<Coordinate> coordinates;
    private int[] dir = new int[]{0,0};
    private final Character type;
    public Line(Coordinate c) {
        this.coordinates = new ArrayList<>();
        this.coordinates.add(c);
        this.length = 0;
        this.type = c.getType();
    }
    public void addCoordinate(Coordinate c) {
        if (Arrays.equals(this.dir, new int[]{0, 0})){
            this.dir = coordinates.get(0).substract(c);
        }
        this.coordinates.add(c);
        this.length = this.coordinates.size();
    }
    public boolean canAddCoordinate(Coordinate coordinate){
        if (coordinate.getType() != this.type){
            return false;
        }
        if (Arrays.equals(this.dir, new int[]{0, 0})){
            return true;
        }
        for (Coordinate c : coordinates ){
            if (c.areNexTo(coordinate,this.dir)){
                return true;
            }
        }
        return false;
    }
    public Character getType(){
        return this.type;
    }
    public Integer getLength() {
        return length;
    }
}
class Coordinate {
    private Integer x;
    private Integer y;
    private final Character type;
    public Coordinate(Integer x, Integer y, Character type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public int[] substract(Coordinate c){
        return new int[]{x-c.getX(),y-c.getY()};
    }
    public void inverse(){
        this.x = -1*this.x;
        this.y = -1*this.y;
    }
    public boolean areNexTo(Coordinate c, int[] dir){
        if (Arrays.equals(this.substract(c), dir)){
            return true;
        }
        c.inverse();
        return Arrays.equals(this.substract(c), dir);
    }
    public Integer getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }
    public Character getType(){
        return this.type;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x.equals(that.x) && y.equals(that.y) && type.equals(that.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
