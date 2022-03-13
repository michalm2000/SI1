package services;

import models.Parameter;
import models.Specimen;

import java.util.List;

public class RandomMethod {

    public static Specimen getBestRandomSpecimen(int count, int width, int height, int machineCount, String costPath, String flowPath ) {
        List<Parameter> parameterList = JsonLoader.getParameters(costPath, flowPath);
        Specimen result = new Specimen(width, height, machineCount, parameterList);
        for (int i = 1; i < count; i++){
            Specimen newSpecimen = new Specimen(width, height, machineCount, parameterList);
            if (result.compareTo(newSpecimen) < 0){
                result = newSpecimen;
            }
        }
        return result;
    }

}
