import java.util.*;

public class Trigramme {

    /**
     * Cette methode teste si le mot existe dans le dictinnaire, si oui elle nous renvoie le mot sinon construire la liste des trigrammes du mot word
     * @param dict Dictionnary
     * @param word String
     * @return Map
     */

    public Map<String, List<String>> trig(Dictionnary dict, String word){
        Map<String, List<String>> map = new HashMap<>();
        if (dict.getDictionary().containsValue(word)){
            map.put(word, new ArrayList<>());
            map.get(word).add(word);
        }
        else {
            List<String> wordSeparate = separate(word, new ArrayList<>());
            for (String string: wordSeparate){
                map.put(string, new ArrayList<>());
            }
            for (String string: wordSeparate){
                for (String value : dict.getDictionary().values()){
                    if (value.contains(string)) map.get(string).add(value);
                }
            }
        }
        return map;
    }

    /**
     * Cette methode sépare le mot en une liste de trigramme
     * @param word String
     * @param trigramme List<String>
     * @return List<String>
     */
    public List<String> separate(String word, List<String> trigramme){
        if (word !=null) {
            if (word.length() >= 3){
                String value = word.substring(0, 3);
                trigramme.add(value);
                word = word.substring(1);
            }else {
                trigramme.add(word);
                word = null;
            }
        }else{
            return trigramme;
        }
        return separate(word, trigramme);
    }

    /**
     * Cette methode renvoie un dictionnaire constitué des mots déjà sélectionnés et leur occurence
     * @param dict Dictionnary
     * @param word String
     * @return Map<String, Integer>
     */
    public Map<String, Integer> occurence(Dictionnary dict, String word){
        Map<String, Integer> map1 = new HashMap<>();
        List<String> wordSeparate = separate(word, new ArrayList<>());
        int count = 0;
        for (List<String> list: trig(dict, word).values()){
            for (String element: list){
                for (String value: wordSeparate){
                    if (element.contains(value)){
                        count = count +1;
                    }
                }
                map1.put(element, count);
                count = 0;
            }
        }
        return map1;
    }

    /**
     * Sélectionne les mots qui ont le plus de trigrammes communs avec word <b>le mot donné en argument</b>
     * @param dict Dictionnary
     * @param word String
     * @return Map
     */

    public Map<String, Integer> maxTri(Dictionnary dict, String word){
        Map<String, Integer> map1 = occurence(dict, word);
        Map<String, Integer> map2 = new HashMap<>();
        if (map1.values().stream().max(Integer::compare).isPresent()){
            Integer maxTri = map1.values().stream().max(Integer::compare).get();
            for (String string: map1.keySet()){
                if (map1.get(string).equals(maxTri)){
                    map2.put(string, map1.get(string));
                }
            }
        }
        return map2;
    }

    /**
     * Sélectionne les 5 mots les plus proches de <b><b>word</b></b> au sens de la distance
     * d’édition
     * @param dict Dictionnary
     * @param word String
     * @return Map
     */

    public Map<String, Integer> wordWtihLev(Dictionnary dict, String word){
        Map<String, Integer > map = new HashMap<>();
        Map<String, Integer > map2 = new HashMap<>();
        Map<String, Integer> map1 = this.maxTri(dict, word);

        for (String value : map1.keySet()){
            int distance = Levenshtein.DistanceDeLevenshtein(value, word);
            map.put(value, distance );
        }
        for (int index = 0; index < 5; index++){
            if (map.values().stream().min(Integer::compare).isPresent()){
                Integer minTri = map.values().stream().min(Integer::compare).get();
                for (String key: map.keySet()){
                    if (map.get(key).equals(minTri)){
                        map2.put(key, minTri);
                        map.remove(key);
                        break;
                    }
                }

            }
        }
        return map2;
    }


}
