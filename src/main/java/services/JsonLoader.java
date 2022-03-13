package services;

import models.Parameter;
import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class JsonLoader {

    public static ArrayList<Parameter> getParameters(String costPath, String flowPath){

        ArrayList<Parameter> result = new ArrayList<>();
        try {

            JSONTokener costToken = new JSONTokener(new FileReader(costPath));
            JSONArray costArray = new JSONArray(costToken);
            JSONTokener flowToken = new JSONTokener(new FileReader(flowPath));
            JSONArray flowArray = new JSONArray(flowToken);
            for (Object obj : costArray) {
                JSONObject costObj = (JSONObject) obj;
                int source = costObj.getInt("source");
                int dest = costObj.getInt("dest");
                int cost = costObj.getInt("cost");
                int amount = 0;
                for (Object o : flowArray) {
                    JSONObject flowObj = (JSONObject) o;
                    if (flowObj.getInt("source") == source && flowObj.getInt("dest") == dest) {
                        amount = flowObj.getInt("amount");
                    }
                }
                result.add(new Parameter(source, dest, cost, amount));
            }
        }catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return result;


    }


}
