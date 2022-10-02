import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	public static ArrayList<Ball> balls = new ArrayList<>();
	public static Table table = new Table();


	public static void parse(String path) {

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) object;

			JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

			Color tableColour = (Color) Paint.valueOf((String) jsonTable.get("colour"));

			Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
			Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

			Double tableFriction = (Double) jsonTable.get("friction");


			table.setColor(tableColour);
			table.setWidth(tableX);
			table.setHeight(tableY);
			table.setArcWidth(50);
			table.setArcHeight(50);
			table.setFriction(tableFriction);
			double borderW = 0.056*table.getWidth();
			double borderH = 0.105*table.getHeight();
			table.setX(borderW);
			table.setY(borderH);
			table.bounds = table.getBoundsInLocal();




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

				balls.add(ball);



			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// if a command line argument is provided, that should be used as the path
		// if not, you can hard-code a default. e.g. "src/main/resources/config.json"
		// this makes it easier to test your program with different config files
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "src/main/resources/config.json";
		}
		// parse the file:
		ConfigReader.parse(configPath);
	}
}
