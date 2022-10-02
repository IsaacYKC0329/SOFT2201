import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TableFactory extends ConfigFactory {

    @Override
    public ArrayList<Object> readFile(String path) {

        JSONParser parser = new JSONParser();
        ArrayList<Object> tableelements = new ArrayList<>();
        try {
            Object object = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) object;

            JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

            Color tableColour = (Color) Paint.valueOf((String) jsonTable.get("colour"));

            Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
            Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

            Double tableFriction = (Double) jsonTable.get("friction");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return tableelements;
    }
}
