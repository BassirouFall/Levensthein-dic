import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Dictionnary dict = new Dictionnary();
        // dict.printToHastable();
      //  System.out.println(dict.getDictionary());
        //System.out.println(dico.DistanceDeLevenshtein("algorithmique","logarytmique"));
        //System.out.println(test.DistanceDeLevenshtein("aviron","avion"));

       // Trigramme tri = new Trigramme();
       // List<String> list = new ArrayList<>();
       // List<String> list1 = new ArrayList<>();
        //System.out.println(tri.separate("fallou", list));
        //System.out.println(tri.separate("bassirou", list1));
      //  Map<String,  Integer> map = tri.wordWtihLev(new Dictionnary(), "alogarythmique");
      //  Map<String, List<String>> map1 = tri.trig(new Dictionnary(), "abeillon");
      //  Map<String, Integer> map2 = tri.occurence(new Dictionnary(), "réaccout");
       /*for (String key : map.keySet()){
           System.out.println(key + " ===> " + map.get(key)+"\n");
       }*/
      // for (String key : map.keySet()){
      //     System.out.println(key + " ===> " + map.get(key)+"\n");
      // }

       /*********************/
       BufferedReader readFautes;
      /*
        try {
            FileReader fileReader = new FileReader("fautes.txt");
            readFautes = new BufferedReader(fileReader);
        } catch (Exception e) {
            System.out.println("impossible d'ouvrir le fichier");
            e.printStackTrace();
        }
        */

         Hashtable<Integer, String> map = new Hashtable<>();
        try {
            FileReader fileReader = new FileReader("fautes.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int numberWord= 1;
            while (line != null) {
                map.put(numberWord , line);
                numberWord++;
                line = bufferedReader.readLine();

            }
            bufferedReader.close();
            long start = System.nanoTime();
            for (String string : map.values()){
                Map<String,  Integer> anotherMap = (new Trigramme()).wordWtihLev(new Dictionnary(), string);
            }
            long end = System.nanoTime();
            System.out.println("Le temps pour corriger tous les mots du fichier fautes.txt est = " + (end-start));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
