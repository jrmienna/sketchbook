package exercises14.exercise08.asteroids;

import javafx.scene.paint.Color;

public class Asteroids1 extends Asteroids {

	public void init() {
		super.init();

		SpaceObject a1 = createAsteroid();
		a1.setSpeed(2, 0);
		add(a1, 0, getPrefHeight() / 5 - 10);

		SpaceObject a2 = createAsteroid();
		a2.setSpeed(-1, 0);
		add(a2, getPrefWidth(), getPrefHeight() / 5 + 10);
		
		SpaceObject a3 = createAsteroid();
		a3.setSpeed(2, 0);
		add(a3, 0, getPrefHeight() / 2 - 20);
		
		SpaceObject a4 = createAsteroid();
		a4.setSpeed(-2, 0);
		add(a4, getPrefWidth(), getPrefHeight() / 2 + 20);
		
		SpaceObject a5 = createAsteroid();
		a5.setSpeed(1, 0);
		add(a5, 0, getPrefHeight() * 4 / 5 - 30);
		
		SpaceObject a6 = createAsteroid();
		a6.setSpeed(-2, 0);
		add(a6, getPrefWidth(), getPrefHeight() * 4 / 5 + 30);
	}

	private Asteroid createAsteroid() {
		Asteroid asteroid = new Asteroid(10, 2, 10, 0.3);
		asteroid.setFill(Color.WHITE);
		return asteroid;
	}
	
	//

	public static void main(String[] args) {
		AsteroidsProgram.main(new String[]{exercises14.exercise08.asteroids.Asteroids1.class.getName()});
	}
}
