public  class NBody{
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int N = in.readInt();
        double RADIUS = in.readDouble();
        return RADIUS;
    }

    public  static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int N = in.readInt();
        double RADIUS = in.readDouble();
        Planet[] planets = new Planet [N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass ,imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double RADIUS = readRadius(filename);
        Planet [] p = readPlanets(filename);
        StdDraw.setScale(-2 * RADIUS, 2 * RADIUS);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet b : p) {
            b.draw();
        }

        StdDraw.enableDoubleBuffering();
        for (double time = 0;time <= T;time += dt) {
            double [] xForces = new double[p.length];
            double [] yForces = new double[p.length];
            for (int i = 0;i < p.length; i ++) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }

            for (int i = 0;i < p.length; i ++) {
                p[i].update(dt, xForces[i], yForces[i]);
            }

            /** Draw the background. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /** Draw the all planets. */
            for (Planet b : p) {
                b.draw();
            }
            /** Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }
        /** Print the contend of the txt.*/
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", RADIUS);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }


    }
}
