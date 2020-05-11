public class Planet {
	double xxPos = 1.0;
	double yyPos = 2.0;
	double xxVel = 3.0;
	double yyVel = 4.0;
	double mass = 5.0;
	String imgFileName = "jupiter.gif";
	static final double G = 6.67 * 10e-12;
	public Planet(double xP , double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		img = imgFileName;
	}
	public Planet(Planet b) {

	}

	public double calcDistance(Planet b) {
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double distance = dx * dx + dy * dy;
		return Math.sqrt(distance);
	}

	public double calcForceExertedBy(Planet c) {
		double r = this.calcDistance(c);
		double f = (G * this.mass * c.mass)/(r * r);
		return f;
	}
}
