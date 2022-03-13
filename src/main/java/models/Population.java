package models;
import services.JsonLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Population {
    private List<Specimen> specimens;

    protected ArrayList<Parameter> parameterList;

    public Population(int specimenQuantity, int width, int height, int machineCount, String costPath, String flowPath) {
        this.specimens = new ArrayList<>();
        parameterList = JsonLoader.getParameters(costPath, flowPath);
        for (int i = 0; i < specimenQuantity; i++) {
            this.specimens.add(new Specimen(width, height, machineCount, parameterList));
        }

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
            denominator += 1/ (double) specimens.get(i).getFitness();
        }

        double[] endOfEachPeriod = new double[specimens.size()];
        endOfEachPeriod[0] = (1/(double) specimens.get(0).getFitness())/denominator;
        for(int i = 1; i < specimens.size(); i++){
            endOfEachPeriod[i] = endOfEachPeriod[i-1] + (1/(double) specimens.get(i).getFitness())/denominator;
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



    public List<Specimen> getSpecimens() {
        return specimens;
    }

    public Specimen getSpecimen(int index){
        return specimens.get(index);
    }

}
