package coverstring;

import java.util.Random;

/**
 * To determine whether or not c is an ordered shuffle of a and b.
 * @author Daniel Banks
 */
public class CoverString {

    public static void main(String[] args) {
        int len = 4;
        
        //Timing Experiment
        while(len<=16){
            int n = 2;
            int k = 1;
            System.out.println("");
            System.out.println("String Length = "+len);
            System.out.printf("%s\t%s\t%s\t%s\n", "length", "Repetitions",
                    "elapsedTime", "timePerSearch" );
            
            // this represents 2^k
            while(k<=20){
                final long LIMIT = 1000000000;
                long startTime = System.nanoTime();
                long counter = 0;
                do{
                    counter++;
                    n = (int)Math.pow(2, k);
                    String[] list = new String[n];
                    
                    for(int i=0;i<n;i++){
                        list[i]=generateStrings(len);
                    }
                    
                    String coverString = generateCover(list, len);
                    isCoverString(list, coverString);

                }while(System.nanoTime() - startTime < LIMIT);
                k++;

                long elapsedTime = System.nanoTime() - startTime;
                double timePerSearch = (double) elapsedTime/counter;
                System.out.printf("%5d\t%10d\t%12d\t%13.0f\n", n, counter,
                        elapsedTime, timePerSearch );
            }
        // double length of strings in array
        len*=2;
        }
    }
    
    // Generate Strings to compare to the cover string

    /**
     *
     * @param len
     * @return String that is randomly generated from the alphabet
     */
        public static String generateStrings(int len){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] n = new char[len];
        Random r = new Random();
        
        for(int i =0; i < len; i++){
            n[i] = chars[r.nextInt(chars.length)];
        }
        String b = new String(n);
        return b;
    }
    
    // Generate the cover string

    /**
     *
     * @param list
     * @param len
     * @return String of the generated cover string.
     */
        public static String generateCover(String[] list, int len){
        String cover = "";
        String end = "";
        for(String element: list){
            //place the last letter of every string at the end of the cover 
            //string to get a pretty bad case, but not absolute worst
            cover += element.substring(0, len-1);
            end += element.substring(len-1, len);
        }
        cover+=end;
        return cover;
    }
        
    /**
     *
     * @param checkList
     * @param checkCover
     * @return boolean depending on if the cover string is a cover for the 
     *         list of strings
     */
        public static boolean isCoverString(String[] checkList,
                String checkCover){
        
        boolean isCover = false;
        
        for(String element: checkList){
            int a = 0;
            isCover = false;
            for(int i =0; i<checkCover.length() && a<element.length(); i++){
                if(element.substring(a, a+1).equals(
                        checkCover.substring(i,i+1))){
                    a++;
                }
                if(a == element.length()){
                    isCover = true;
                    // Commenting out this break will produce the worst 
                    // possible case for this agorithm
//                    break;
                }
            }
            if(isCover==false){
                return false;
            }
        }
        return isCover;
    }
}
