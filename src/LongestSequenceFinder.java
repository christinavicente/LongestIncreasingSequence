import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongestSequenceFinder {

    public static void main(String args[]){
        //the initial random sequence is initialized
        int size=20; int range=15;
        List<Integer> values= createSequence(size,range);
        //the subsequences created by my code
        List<Integer> subsequence1,subsequence2;
        //the random sequence and the subsequence created by the in class example
        Integer[] arrayValues=new Integer[size]; Integer[] subsequence3=new Integer[size];
        arrayValues=values.toArray(arrayValues);

        //prints out the full sequence
        System.out.println("The full sequence");
        printSequence(values);

        //finds the first instance of the longest increasing subsequence in the sequence
        SequenceFinder sequenceFinder=new SequenceFinder(values);
        subsequence1=sequenceFinder.longestConsecIncrSequence();
        System.out.println("The longest Consecutive Increasing Subsequence");
        printSequence(subsequence1);

        //class example, if there are multiple increasing subsequences in the initial
        //sequence, it finds a different one than the previous subsequence found
        LongestIncreasingSubsequence lis=new LongestIncreasingSubsequence(arrayValues);
        System.out.println("The longest Increasing Subsequence found from class example");
        subsequence3=lis.getLongestIncreasingSubsequence();
        printLiveLessonExample(subsequence3);

        //finds the longest possible increasing subsequence if numbers can be skipped in the sequence
        System.out.println("The longest NonConsecutive Increasing Subsequence");
        subsequence2 =sequenceFinder.longestNonConsecIncrSequence();
        printSequence(subsequence2);


    }
    //creates the initial sequence of length size and with integers from 1-range
    static List<Integer> createSequence( int size, int range){
        List<Integer> values=new ArrayList<Integer>(size);
        Random r=new Random();
        int temp;
        for(int i=0;i<size;i++){
            temp=(int)(r.nextDouble()*15);
            values.add(temp+1);
        }
        return values;
    }
    //the print statement for the example from class
    public static void printLiveLessonExample(Integer[] result){
        System.out.println("Winning sequence is");
        for (Integer i : result) {
            System.out.println("number: " + i);
        }
        System.out.println("End of sequence");
    }
    //the print statement for the sequences I came up with
    public static void printSequence(List<Integer> values){
        int j=0;
        for(int i=0;i<values.size();i++){

            if(j%5==0){
                System.out.println("");
            }
            System.out.print(values.get(i)+ "\t");
            j++;
        }
        System.out.println("");
    }


}
