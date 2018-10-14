package orderedshuffle;

/**
 * To determine whether or not a given string, S, is a cover-string for a given
 * list of strings.
 * @author Daniel Banks
 */
public class OrderedShuffle {

    public static void main(String[] args) {
        
        String a = "cooking";
        String b = "cooling";
        String c = "cooclookinging";
        
        // Timing test
        long start = System.nanoTime();
        
        if(myIOS(a, b, c)){
            System.out.println(c+" is an Ordered Shuffle of "+a+" and "+b);
        }else System.out.println(c+" is NOT an Ordered Shuffle of "+a+" and "
                +b);
        
        long end = System.nanoTime();
        System.out.println("");
        long res1 = end-start;
        System.out.println(res1);
        
    }
    
    /**
     *
     * @param a 
     * @param b 
     * @param c 
     * @return boolean depending on if c is a cover string of a & b
     */
    public static boolean myIOS(String a, String b, String c){
//        System.out.println("");
        System.out.println("a = "+a+" b = "+b+" c = "+c);
        
        // Check if c is length of a + b
        if(c.length()!= a.length()+b.length()){
            System.out.println("C Incorrect length");
            return false;
        }
        
        // Check if c is 0
        if(c.length()==0){
            System.out.println("C length = 0");
            return true;
        }
        
        //check if c is a & b togeather
        if(c.equals(a+b)||c.equals(b+a)){
            System.out.println("C = a+b/b+a");
            return true;
        }
        
        //If first letter of a and b are the same
        if(a.substring(0, 1).equals(b.substring(0, 1))){
            //If a or b are 1 letter long
            if(a.length()==1 || b.length()==1){
                //recursive a - c comaprison
                if(a.substring(0, 1).equals(c.substring(0, 1))){
                    return myIOS(a.substring(1, a.length()), b, c.substring(1,
                            c.length()));
                }
                //recursive b - c comparison
                if(b.substring(0, 1).equals(c.substring(0, 1))){
                    return myIOS(a, b.substring(1, b.length()), c.substring(1,
                            c.length()));
                }
            }
            
            /**
             * Loop through a & b whilst they are the same, and increment i
             *
             * This will speed up the algorithm in cases where a and b share 
             * multiple characters in order, e.g. a=cooking, b=cooling, 
             * c=coolcookinging, passing multiple letters instead of one 
             * at a time.
            **/
            if(!a.equals(b)){    
                int i = 0;
                while(b.length()>i && a.length()>i && a.substring(0, i).equals(
                        b.substring(0, i))){
                    i++;
                }
                //recursive a - c comaprison from first character to i
                if(a.substring(0, i).equals(c.substring(0, i))){
                    return myIOS(a.substring(i, a.length()), b, c.substring(i,
                            c.length()));
                }
                //recursive b - c comparison from first character to i
                if(b.substring(0, i).equals(c.substring(0, i))){
                    return myIOS(a, b.substring(i, b.length()), c.substring(i,
                            c.length()));
                }
            } 
        }
        
        //recursive a - c comaprison
        if(a.substring(0, 1).equals(c.substring(0, 1))){
            return myIOS(a.substring(1, a.length()), b, c.substring(1,
                    c.length()));
        }
        //recursive b - c comaprison
        if(b.substring(0, 1).equals(c.substring(0, 1))){
            return myIOS(a, b.substring(1, b.length()), c.substring(1,
                    c.length()));
        }
        return false;
    }
}