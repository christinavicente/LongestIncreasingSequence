import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongestSequenceFinder {

    public static void main(String args[]){
        int size=20; int range=15;
        List<Integer> values= createSequence(size,range);
        List<Integer> subsequence1,subsequence2;
        Integer[] arrayValues=new Integer[size];
        Integer[] subsequence3=new Integer[size];
        arrayValues=values.toArray(arrayValues);
        System.out.println("The full sequence");
        printSequence(values);

        SequenceFinder sequenceFinder=new SequenceFinder(values);
        subsequence1=sequenceFinder.longestConsecIncrSequence();
        System.out.println("The longest Consecutive Increasing Subsequence");
        printSequence(subsequence1);

        System.out.println("The longest NonConsecutive Increasing Subsequence");
        subsequence2 =sequenceFinder.longestNonConsecIncrSequence();
        printSequence(subsequence2);

        LongestIncreasingSubsequence lis=new LongestIncreasingSubsequence(arrayValues);
        System.out.println("The longest Increasing Subsequence found from class example");
        subsequence3=lis.getLongestIncreasingSubsequence();
        printLiveLessonExample(subsequence3);
    }
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
    public static void printLiveLessonExample(Integer[] result){
        System.out.println("Winning sequence is");
        for (Integer i : result) {
            System.out.println("number: " + i);
        }
        System.out.println("End of sequence");
    }
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
