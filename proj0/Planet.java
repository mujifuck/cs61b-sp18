public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67 * 10e-12;

	public Planet(double xP , double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b) {
	    this.xxPos = b.xxPos;
	    this.xxVel = b.xxVel;
	    this.yyPos = b.yyPos;
	    this.yyVel = b.yyVel;
	    this.mass = b.mass;
	    this.imgFileName = b.imgFileName;
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

	public double calcForceExertedByX(Planet b) {
		double f = this.calcForceExertedBy(b);
		double dx = b.xxPos - this.xxPos;
		double r = this.calcDistance(b);
		double fx = f * dx / r;
		return fx;
	}

	public double calcForceExertedByY(Planet b) {
		double f = this.calcForceExertedBy(b);
		double dy = b.yyPos - this.yyPos;
		double r = this.calcDistance(b);
		double fy = f * dy / r;
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] PlanetArray) {
		int SIZE = PlanetArray.length;
		double FxNet = 0;
		for (int i = 0;i < SIZE;i += 1) {
			if (!this.equals(PlanetArray[i]))
			{
				FxNet += this.calcForceExertedByX(PlanetArray[i]);
			}
		}
		return FxNet;
	}

	public double calcNetForceExertedByY(Planet[] PlanetArray) {
		int SIZE = PlanetArray.length;
		double FyNet = 0;
		for (int i = 0;i < SIZE;i += 1) {
			if (!this.equals(PlanetArray[i]))
			{
				FyNet += this.calcForceExertedByY(PlanetArray[i]);
			}
		}
		return FyNet;
	}

	public  void update(double dt, double fx, double fy) {
		double xxAcc = fx / this.mass;
		double yyAcc = fy / this.mass;
		this.xxVel += dt * xxAcc;
		this.yyVel += dt * yyAcc;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
