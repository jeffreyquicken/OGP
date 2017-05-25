package asteroids.model;

import asteroids.model.*;
public class BulletExpression extends Expression<Bullet> {
	public Bullet getValue(){
		Ship user = this.getProgram().getUser();
		return user.getNearestBullet();
	}
}
