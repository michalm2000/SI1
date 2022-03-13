import models.Population;
import models.Specimen;
import services.RandomMethod;

public class FLOSolver {
    public static final String easyCostPath = "src/main/resources/easy_cost.json";
    public static final String easyFlowPath = "src/main/resources/easy_flow.json";
    public static final String flatCostPath = "src/main/resources/flat_cost.json";
    public static final String flatFlowPath = "src/main/resources/flat_flow.json";
    public static final String hardCostPath = "src/main/resources/hard_cost.json";
    public static final String hardFlowPath = "src/main/resources/hard_flow.json";
    public static void main(String[] args) {
        Population population = new Population(10, 3, 3, 9, easyCostPath, easyFlowPath);
        Population population2 = new Population(10, 1, 12, 12, flatCostPath, flatFlowPath);
        Population population3 = new Population(10, 5, 6, 24, hardCostPath, hardFlowPath);
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
//        System.out.println(RandomMethod.getBestRandomSpecimen(1000, 3, 3, 9, easyCostPath, easyFlowPath).getFitness());
//        System.out.println(RandomMethod.getBestRandomSpecimen(1000, 1, 12, 12, flatCostPath, flatFlowPath).getFitness());
//        System.out.println(RandomMethod.getBestRandomSpecimen(1000, 5, 6, 24, hardCostPath, hardFlowPath).getFitness());
        population3.roulette();
        System.out.println("dupa");
    }
}
