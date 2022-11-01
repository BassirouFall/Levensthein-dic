import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Dictionnary {
    private  Hashtable<Integer, String> dictionary = new Hashtable<>();
    private BufferedReader readDictionnary;
    public Hashtable<Integer, String> getDictionary() {
        return dictionary;
    }
    public Dictionnary(){
        /*****lecture fichier*****/
        try {
            FileReader fileReader = new FileReader("dico.txt");
            readDictionnary = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("impossible d'ouvrir le fichier");
            e.printStackTrace();
        }

        this.addToHashtable();

    }

    private void addToHashtable()  {
        try {
            String line = readDictionnary.readLine();
            int numberWord= 1;
            while (line != null){
                dictionary.put(numberWord, line);
                numberWord++;
                line = readDictionnary.readLine();


            }
            readDictionnary.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void printToHastable(){
        for(String word : dictionary.values()){
            System.out.println(word);
        }
    }

    public int DistanceDeLevenshtein(String chaine1 , String chaine2){
        int[][] D = new int[chaine1.length()+1][chaine2.length()+1];
        int coutSubstitution;
        for (int i = 0; i <= chaine1.length() ; i++) {
            D[i][0]=i;
        }

        for (int j = 0; j <= chaine2.length() ; j++) {
            D[0][j]=j;
        }
        System.out.println(Arrays.deepToString(D));
        for (int i = 1; i <= chaine1.length(); i++) {
            for (int j = 1; j <= chaine2.length(); j++) {
                if(chaine1.charAt(i-1)==chaine2.charAt(j-1)){
                    coutSubstitution =0;
                }
                else{

                    coutSubstitution =1;
                }

                D[i][j]= Math.min( Math.min(D[i-1][j] + 1 , D[i][j-1]+1) , D[i-1][j-1] + coutSubstitution) ;

            }
        }
        return D[chaine1.length()][chaine2.length()];


    }





}
