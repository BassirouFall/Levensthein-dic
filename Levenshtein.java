import java.util.Arrays;

public class Levenshtein {

      public static int DistanceDeLevenshtein(String chaine1 , String chaine2){
          int coutSubstitution;
        int[][] D = new int[chaine1.length()+1][chaine2.length()+1];
        for (int i = 0; i <= chaine1.length() ; i++) {
            D[i][0]=i;
        }

        for (int j = 0; j <= chaine2.length() ; j++) {
            D[0][j]=j;
        }
        for (int i = 1; i <= chaine1.length(); i++) {
            for (int j = 1; j <= chaine2.length(); j++) {
                if(chaine1.charAt(i-1)==chaine2.charAt(j-1)){
                     coutSubstitution=0;
                }
                else{

                   coutSubstitution=1;
                }

                D[i][j]= Math.min( Math.min(D[i-1][j] + 1 , D[i][j-1]+1) , D[i-1][j-1] + coutSubstitution) ;

            }
        }
        return D[chaine1.length()][chaine2.length()];


    }
}
