package models;
import data.Constants;
import data.ParameterList;
import data.ProblemType;
import services.JsonLoader;

import java.util.*;


public class Population {
    private ArrayList<Specimen> specimens;

    protected ArrayList<Parameter> parameterList;
    private int width;
    private int height;
    private int machineCount;

    public Population(int specimenQuantity, int width, int height, int machineCount, ProblemType problemType) {
        this.specimens = new ArrayList<>();
        parameterList = ParameterList.accessParameterList(problemType);
        for (int i = 0; i < specimenQuantity; i++) {
            this.specimens.add(new Specimen(width, height, machineCount, parameterList));
        }

    }

    public Population(int width, int height, int machineCount, ArrayList<Parameter> parameterList) {
        this.specimens = new ArrayList<Specimen>();
        this.parameterList = parameterList;
        this.width = width;
        this.height = height;
        this.machineCount = machineCount;
    }

    public Population generateNewPopulation(){
        Population result = new Population(width, height, machineCount,parameterList);
        result.specimens = new ArrayList<>();
        while (result.specimens.size() < specimens.size()) {
            Specimen specimenA = roulette();
            Specimen specimenB = roulette();

            if(Math.random() > Constants.CROSSOVER_PROBABILITY){
                Specimen child = crossover(specimenA, specimenB);
                if(Math.random() > Constants.MUTATION_PROBABILITY){
                    child.mutate();
                }
                result.specimens.add(child);
            }
            else {
                if (Math.random() > Constants.MUTATION_PROBABILITY) {
                    specimenA.mutate();
                    specimenB.mutate();
                }
                result.specimens.add(specimenA);
                if (result.specimens.size() < specimens.size()) {
                    result.specimens.add(specimenB);
                }
            }
        }
        return result;
    }

    public Specimen tournament(int numberOfParticipants){
        Random random = new Random();
        ArrayList<Integer> chosenIndexes = new ArrayList<>();
        while (chosenIndexes.size() < numberOfParticipants) {
            int index = random.nextInt(specimens.size());
            if (!chosenIndexes.contains(index)){
                chosenIndexes.add(index);
            }
        }
        Specimen result = specimens.get(chosenIndexes.get(0));
        for (int i = 1; i < numberOfParticipants; i++) {
            Specimen nextSpecimen = specimens.get(chosenIndexes.get(i));
            if (result.compareTo(nextSpecimen) < 0){
                result = nextSpecimen;
            }
        }
        return result;
    }

    public Specimen roulette(){
        double denominator = 0;
        for(int i = 0; i < specimens.size(); i++){
            denominator += 1/  specimens.get(i).getFitness();
        }

        double[] endOfEachPeriod = new double[specimens.size()];
        endOfEachPeriod[0] = (1/ specimens.get(0).getFitness())/denominator;
        for(int i = 1; i < specimens.size(); i++){
            endOfEachPeriod[i] = endOfEachPeriod[i-1] + (1/ specimens.get(i).getFitness())/denominator;
        }
        double selectedValue = Math.random();
        int i = 0;
        while (i < endOfEachPeriod.length){
            if (endOfEachPeriod[i] > selectedValue) {
                return specimens.get(i);
            }
            i++;
        }
        return specimens.get(i);
    }

    public Specimen crossover(Specimen parentA, Specimen parentB){
        Random random = new Random();
        int cutPlace = random.nextInt(parentA.getBoard().length);
        Specimen result = parentA.clone();
//        ArrayList<Integer> availableMachines = new ArrayList<>();
//
//        for (int i = cutPlace; i < result.getBoard().length; i++){
//            availableMachines.add(result.getPlaceFromBoard(i));
//        }
//
//        availableMachines = getAvailableMachinesInOrder(availableMachines, parentB.getBoard());
//
//
//        for (int i = cutPlace; i < result.getBoard().length ; i++) {
//            result.setPlaceOnBoard(i, availableMachines.get(i-cutPlace));
//        }

        for (int i = cutPlace; i < result.getBoard().length; i++){
            boolean possibleSwap = true;
            for (int j = 0; j < cutPlace; j++){
                if (parentA.getBoard()[j] == -1 || parentB.getBoard()[i] == -1 || Objects.equals(parentA.getBoard()[j], parentB.getBoard()[i])){
                    possibleSwap = false;
                }
            }
            if (possibleSwap){
                result.setPlaceOnBoard(i, parentB.getBoard()[i]);
            }
        }
        return result;
    }

    private double averageFitness(){
        double result = 0;
        for (int i = 0; i < specimens.size(); i++) {
            result += specimens.get(i).getFitness();
        }
        return result;
    }


    public void mutate(double probability){
        for(int i = 0; i < this.specimens.size(); i++){
            double random = Math.random();
            if (random <= probability){
                specimens.get(i).mutate();
            }
        }
    }

    private ArrayList<Integer> getAvailableMachinesInOrder(List<Integer> availableMachines,  Integer[] array){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            int element = array[i];
            if (availableMachines.contains(element)) {
                result.add(element);
                availableMachines.remove((Object) element);
            }
        }
        return result;
    }



    public List<Specimen> getSpecimens() {
        return specimens;
    }

    public Specimen getSpecimen(int index){
        return specimens.get(index);
    }

    public double bestSpecimenFitness() {
        double result = Double.MAX_VALUE;
        for (int i = 0; i < specimens.size(); i++){
            result = Double.min(result, specimens.get(i).getFitness());
        }
        return result;
    }
}
