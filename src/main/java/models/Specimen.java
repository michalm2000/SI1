package models;


import java.util.*;
import java.util.stream.IntStream;

public class Specimen implements Comparable<Specimen> {
    private final Integer[] board;
    private final int width;
    private final int height;
    private final List<Parameter> parameterList;
    private int fitness;


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
        calculateFitness();
    }

    private void calculateFitness(){
        int result = 0;
        for (Parameter parameter : parameterList) {
            result += parameter.getCost() * parameter.getAmount() * getDistanceBetweenMachines(parameter.getSource(), parameter.getDest());
        }
        fitness = result;
    }

    public int getFitness() {
        return fitness;
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

    public Integer getBoardCoords(int x, int y) {
        if (x >= width || y >= height) {
            throw new IllegalArgumentException("Coordinates exceed array dimension");
        }
        return board[width * y + x];

    }

    @Override
    public int compareTo(Specimen o) {
        return compare(this, o);
    }

    public static int compare(Specimen x, Specimen y) {
        return Integer.compare(x.getFitness(), y.getFitness()) * -1;
    }
}

