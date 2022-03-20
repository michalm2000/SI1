package models;


import java.util.*;
import java.util.stream.IntStream;

public class Specimen implements Comparable<Specimen>{
    private Integer[] board;
    private int width;
    private int height;
    private List<Parameter> parameterList;


    public Specimen(int width, int height, int machineCount, List<Parameter> parameterList) {

        if (machineCount > width * height) throw new IllegalArgumentException("Too many machines to allocate");
        this.width = width;
        this.height = height;
        this.parameterList = parameterList;
        this.board = new Integer[width * height];
        Arrays.fill(board, -1);
        ArrayList<Integer> indexes = new ArrayList<>(IntStream.range(0, width * height).boxed().toList());
        Collections.shuffle(indexes);

        for (int i = 0; i < machineCount; i++) {
            this.board[indexes.get(i)] = i;
        }
    }




    public void mutate(){
        Random random = new Random();
        int index1 = random.nextInt(board.length);
        int index2;
        do {
            index2 = random.nextInt(board.length);
        }
        while (index2 == index1);
        int pom = board[index2];
        board[index2] = board[index1];
        board[index1] = pom;
    }

    public double getFitness() {
        double result = 0;
        for (Parameter parameter : parameterList) {
            result += parameter.getCost() * parameter.getAmount() * (double) getDistanceBetweenMachines(parameter.getSource(), parameter.getDest());
        }
        return result;
    }

    private int getDistanceBetweenMachines(int machineA, int machineB) {
        int indexA = Arrays.asList(this.board).indexOf(machineA);
        int indexB = Arrays.asList(this.board).indexOf(machineB);
        int coordAX = indexA % width;
        int coordAY = indexA / width;
        int coordBX = indexB % width;
        int coordBY = indexB / width;
        return (Math.abs(coordAX - coordBX) + Math.abs(coordAY - coordBY));
    }

    public int getBoardCoords(int x, int y) {
        if (x >= width || y >= height) {
            throw new IllegalArgumentException("Coordinates exceed array dimension");
        }
        return board[width * y + x];

    }

    private Specimen(){}



    public Integer[] getBoard(){
        return board;
    }

    public void setPlaceOnBoard(int index, int value){
        board[index] = value;
    }
    public int getPlaceFromBoard(int index){ return board[index];}

    @Override
    public int compareTo(Specimen o) {
        return compare(this, o);
    }

    public static int compare(Specimen x, Specimen y) {
        return Double.compare(x.getFitness(), y.getFitness()) * -1;
    }

   public Specimen clone(){
        Specimen specimen = new Specimen();
        specimen.height = height;
        specimen.width = width;
        specimen.parameterList = parameterList;
        specimen.board = board.clone();
        return specimen;
    }

    @Override
    public String toString() {
        return "fitness: " +  getFitness();
    }
}

