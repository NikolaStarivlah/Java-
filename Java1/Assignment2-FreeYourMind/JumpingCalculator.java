package assignment2;

/*Horizontal velocity component: vx = v * cos(angle)
Vertical velocity component: vy = v * sin(angle)
Time of flight: t = 2 * vy / g
Range of the projectile: R = 2* vx * vy / g
Maximum height: ymax = vy^2 / (2 * g) */
public class JumpingCalculator {

  public static final double g = 9.8; //the gravity

  public static double distance(double velocity, double angle) {
    double angleRadians = Math.toRadians(angle);
    double vy = velocity * Math.sin(angleRadians);
    double vx = velocity * Math.cos(angleRadians);
    return 2 * vx * vy / g;
  }

  public static double timeOfFlight(double velocity, double angle) {
    double angleRadians = Math.toRadians(angle);
    double vy = velocity * Math.sin(angleRadians);
    return 2 * vy / g;
  }

  public static double height(double velocity, double angle) {
    double angleRadians = Math.toRadians(angle);
    double vy = velocity * Math.sin(angleRadians);
    return vy * vy / (2 * g);
  }
}
