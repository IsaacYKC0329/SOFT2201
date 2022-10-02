import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BallFactory extends ConfigFactory {

    JSONParser parser = new JSONParser();


    @Override
    public ArrayList<Object> readFile(String path) {
        try {
            Object object = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) object;

            JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");
            JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");
            for (Object obj : jsonBallsBall) {
                JSONObject jsonBall = (JSONObject) obj;

                Color ballColour = (Color) Paint.valueOf((String) jsonBall.get("colour"));

                Double posX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
                Double posY = (Double) ((JSONObject) jsonBall.get("position")).get("y");

                Double velX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
                Double velY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");

                Double mass = (Double) jsonBall.get("mass");


                Ball ball = new Ball(posX, posY, 10, ballColour);

                ball.c = (String) jsonBall.get("colour");
                ball.setColor(ballColour);
                ball.setoPoxX(posX);
                ball.setoPoxy(posY);
                ball.setPosX(posX);
                ball.setPosY(posY);
                ball.setVelX(velX);
                ball.setVelY(velY);
                ball.setMass(mass);

                ConfigReader.balls.add(ball);



            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
