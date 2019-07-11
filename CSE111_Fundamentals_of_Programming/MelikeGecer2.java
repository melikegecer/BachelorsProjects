public class MelikeGecer2 {
    public static void main(String[] args) {
        int sim1 = (int) (Math.random() * 100 + 1);
        int sec = (int) (Math.random() * 100 + 1);
        
        int[] antennaX = {3, 7, 5};
        int[] antennaY = {3, 3, 7};
        
        //simulation1
        int[] sim1_inRange = {0, 0, 0};
        System.out.println("*-*-*-*-*-*-*-*-*-* Simulation 1 results *-*-*-*-*-*-*-*-*-*");
        for(int i=0; i<sim1; i++) {
            int[] inRange = simulation(antennaX, antennaY);
            sim1_inRange[0] += inRange[0];
            sim1_inRange[1] += inRange[1];
            sim1_inRange[2] += inRange[2];
        }
        int sim1Sum = sim1_inRange[0] + sim1_inRange[1] + sim1_inRange[2];
        displayResults(antennaX, antennaY, sim1_inRange, sim1Sum, sim1);
        
        //simulation2
        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-* Simulation 2 results *-*-*-*-*-*-*-*-*-*");
        int[] out = {0, 0, 0};
        int t = 0;
        for(int i=0; i<sec; i++) {
            int request = (int) (Math.random() * 6 + 1);
            for(int j=0; j<request; j++) {
                int[] inRange = simulation(antennaX, antennaY);
                sim1_inRange[0] += inRange[0];
                sim1_inRange[1] += inRange[1];
                sim1_inRange[2] += inRange[2];
                
                if(sim1_inRange[0] >= 3) {
                    out[0] = sim1_inRange[0] - 3;
                    sim1_inRange[0] = 3;
                } else {
                    sim1_inRange[0] = sim1_inRange[0];
                }
                if(sim1_inRange[1] >= 3) {
                    out[1] = sim1_inRange[1] - 3;
                    sim1_inRange[1] = 3;
                } else {
                    sim1_inRange[1] = sim1_inRange[1];
                }
                if(sim1_inRange[2] >= 3) {
                    out[2] = sim1_inRange[2] - 3;
                    sim1_inRange[2] = 3;
                } else {
                    sim1_inRange[2] = sim1_inRange[2];
                }
            }
            t += request;
        }
        int sumServed = sim1_inRange[0] + sim1_inRange[1] + sim1_inRange[2];
        int sumout = out[0] + out[1] + out[2];
        
        displayResults2(antennaX, antennaY, sim1_inRange, sec, out, t, sumServed);
    }
    
    
    public static int[] simulation(int[] antennaX, int[] antennaY) {
        double[] result = randomInRange(10);
        double distance;
        int inRange1 = 0;
        int inRange2 = 0;
        int inRange3 = 0;
        
        for(int i=0; i<3; i++) {
            distance = distance(result[0], result[1], antennaX[i], antennaY[i]);
            
            if(distance <= 3) {
                if(antennaX[i] == 3 && antennaY[i] == 3) {
                    inRange1++;
                } else if(antennaX[i] == 7 && antennaY[i] == 3) {
                    inRange2++;
                } else if(antennaX[i] == 5 && antennaY[i] == 7) {
                    inRange3++;
                } else {
                    System.out.println("No such possibility.");
                }
            }
        }
        return new int[] {inRange1, inRange2, inRange3};
    }
    
    public static double[] randomInRange(double r1) {
        double x = Math.random() * r1;
        double y = Math.random() * r1;
        return new double[] {x, y};
    }
    
    public static double distance(double x, double y, int antennaX, int antennaY) {
        double distanceX = Math.pow((x - antennaX), 2);
        double distanceY = Math.pow((y - antennaY), 2);
        
        double distance = Math.sqrt(distanceX + distanceY);
        
        return distance;
    }
    
    public static void displayResults(int[] antennaX, int[] antennaY, int[] sim_inRange, int simSum, int sim) {
        System.out.println(sim + " communication requests were generated, " + simSum + " of them were served, " + 
                           (sim-simSum) + " of them were unserved.");
        
        for(int i=0; i<3; i++) {
            System.out.println("Antenna position is (" + antennaX[i] + "," + antennaY[i] + ") " + sim_inRange[i] + 
                           " of them were served, " + (sim-sim_inRange[i]) + 
                           " of them were served by other Antennas or out of distance.");
        }
    }
    
    public static void displayResults2(int[] antennaX, int[] antennaY, int[] sim_inRange, int sec, int[] out, int t, int sumServed) {
        System.out.println("Time: " + sec);
        System.out.println((sec*t) + " communication requests were generated, " + sumServed + 
                           " of them were served, " + ((sec*t)-sumServed) + " of them were unserved.");
        
        for(int i=0; i<3; i++) {
            System.out.println("Antenna position is (" + antennaX[i] + "," + antennaY[i] + ") " + sim_inRange[i] + 
                           " of them were served, " + out[i] + 
                           " of them were denied because of capacity.");
        }
    }
}