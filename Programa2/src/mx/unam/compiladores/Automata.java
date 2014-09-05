package mx.unam.compiladores;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class Automata  {

    public static int[][] tabla_estados={

            {1,8,1,1,7,1,5,4,2,6,3,3,-1},
            {1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,4,2,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {1,1,1,1,1,1-1,-1,-1,1,-1,-1,-1,-1},
            {1,1,11,1,1,1,-1,-1,1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1},
            {1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1},
            {1,1,1,12,1,1,-1,-1,1,-1,-1,-1,-1},
            {1,13,1,1,1,1,-1,-1,1,-1,-1,-1,-1},
            {1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1},


    };


    public static int getClaseToken(int i){
        switch (i){
            case 13:
                return 0;
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 2;
            case 5:
                return 4;
            case 6:
                return 3;
            case 7:
                return 1;
            case 8:
                return 1;
            case 9:
                return 6;
            case 10:
                return 0;
            case 11:
                return 1;
            case 12:
                return 1;
        }

        return -1;
    }
    public static boolean isEdo13(int i){
        return i == 13;

    }
}
