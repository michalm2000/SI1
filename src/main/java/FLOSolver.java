import data.ProblemType;
import models.Population;
import services.RandomMethod;

public class FLOSolver {
    public static final String easyCostPath = "src/main/resources/easy_cost.json";
    public static final String easyFlowPath = "src/main/resources/easy_flow.json";
    public static final String flatCostPath = "src/main/resources/flat_cost.json";
    public static final String flatFlowPath = "src/main/resources/flat_flow.json";
    public static final String hardCostPath = "src/main/resources/hard_cost.json";
    public static final String hardFlowPath = "src/main/resources/hard_flow.json";
    public static void main(String[] args) throws CloneNotSupportedException {
        Population population = new Population(100, 3, 3, 9, ProblemType.EASY);
        Population population2 = new Population(100, 1, 12, 12, ProblemType.FLAT);
        Population population3 = new Population(100, 5, 6, 24, ProblemType.HARD);
//        for (Specimen specimen: population.getSpecimens()){
//            System.out.println(specimen.getFitness());
//        }
//        System.out.println("///////////");
//        for (Specimen specimen: population2.getSpecimens()){
//            System.out.println(specimen.getFitness());
//        }
//        System.out.println("///////////");
//        for (Specimen specimen: population3.getSpecimens()){
//            System.out.println(specimen.getFitness());
//        }
//        System.out.println("test");
//        System.out.println(RandomMethod.getBestRandomSpecimen(100, 3, 3, 9, easyCostPath, easyFlowPath).getFitness());
//        System.out.println(RandomMethod.getBestRandomSpecimen(1000, 1, 12, 12, flatCostPath, flatFlowPath).getFitness());
//        System.out.println(RandomMethod.getBestRandomSpecimen(100, 5, 6, 24, hardCostPath, hardFlowPath).getFitness());
        System.out.println(population3.bestSpecimenFitness());
        Population newPopulation = population3.generateNewPopulation();
        double bestFitness = newPopulation.bestSpecimenFitness();
        for(int i = 0; i < 1000000; i++) {
            newPopulation = newPopulation.generateNewPopulation();
            bestFitness = Double.min(bestFitness,newPopulation.bestSpecimenFitness());
            System.out.println("Generation Number: " + i +" " + newPopulation.bestSpecimenFitness() + " " + bestFitness);
        }



    }
}
