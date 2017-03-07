package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;

public class Facade implements IFacade {
	
	public double[] getShipPosition(Ship ship){
		double [] positionArray = {ship.getPosX(),ship.getPosY()};
		return positionArray;
	}

}
