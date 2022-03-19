package data;

import models.Parameter;
import services.JsonLoader;

import java.util.ArrayList;


public  class ParameterList {
    private static final String easyCostPath = "src/main/resources/easy_cost.json";
    private static final String easyFlowPath = "src/main/resources/easy_flow.json";
    private static final String flatCostPath = "src/main/resources/flat_cost.json";
    private static final String flatFlowPath = "src/main/resources/flat_flow.json";
    private static final String hardCostPath = "src/main/resources/hard_cost.json";
    private static final String hardFlowPath = "src/main/resources/hard_flow.json";

    private static final ArrayList<Parameter> easyList = JsonLoader.getParameters(easyCostPath, easyFlowPath);
    private static final ArrayList<Parameter> flatList = JsonLoader.getParameters(flatCostPath, flatFlowPath);
    private static final ArrayList<Parameter> hardList = JsonLoader.getParameters(hardCostPath, hardFlowPath);

    public static ArrayList<Parameter> accessParameterList(ProblemType problemType){
        return switch (problemType) {
            case EASY -> ParameterList.easyList;
            case FLAT -> ParameterList.flatList;
            default -> ParameterList.hardList;
        };
    }
}
