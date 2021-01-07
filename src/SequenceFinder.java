import java.util.ArrayList;
import java.util.List;

public class SequenceFinder {
    int[] maxes; //holds the number of elements after the current element that are larger
    boolean[] possible;//keeps track of which elements have been eliminated
    List<Integer> values; //holds the initial sequence

    //stores the initial sequence, and creates the two arrays
    public SequenceFinder(List<Integer> full){
        values=full;
        maxes= new int[values.size()];
        possible=new boolean[values.size()];
        maxes=initMaxArray(maxes);

    }
    //returns the first instance of a consecutive subsequence of the longest length
    public  List<Integer> longestConsecIncrSequence(){
        //creates and initializes local variables
        int startLength,start,tempStart,tempLength;
        startLength=1;start=0;tempStart=0;tempLength=1;
        boolean inSequence=false;
        //the two values being compared
        Integer curr,next;

        //goes through the list, looking for increasing values
        for(int i=0; i<values.size()-1;i++){
            curr= values.get(i);
            next= values.get(i+1);
            //if the two values are not increasing, it breaks the sequence
            if(curr>=next){
                inSequence=false;
                tempLength=1;
            }else { //(curr<next), the values are increasing
                tempLength++;
                //checks to see if this is part of an existing sequence
                if(!inSequence){
                    //sets the start of the current sequence
                    tempStart=i;
                    inSequence=true;
                }
                //checks to see if the current sequence is longer than
                //the previously longest sequence
                if(tempLength>startLength){
                    //adjusts the start and length accordingly
                    start=tempStart;
                    startLength=tempLength;
                }
            }
        }
        //creates the list with just the first subsequence that is the longest increasing
        List<Integer> result=values.subList(start,start+startLength);
        return result;
    }

    //creates a list of the longest increasing non-consecutive sequence
    //there can be other sequences of the same or shorter length
    public  List<Integer> longestNonConsecIncrSequence(){
        List<Integer> result=new ArrayList<>(values.size());
        //set all possible to true, and sets the current index being added to 0
        possible=initPosArray(possible);
        boolean allDone=false;
        int max=0;
        //fills the array with the amount of values larger than the current value after it
        maxes=populateArray(maxes);

        //goes through the list, and finds the longest subsequence
        while((!allDone)&&(max<values.size())){

            //finds the next value with the highest number of larger numbers after it
            for(int i=max;i<values.size()-1;i++){
                if((possible[i])&&(maxes[max]<maxes[i])){
                    max=i;

                }
            }
            //checks to see if the current value can be added, then adds it
            if(possible[max]) {
                result.add(values.get(max));
            }
            //removes the current value from being added again
            possible[max]=false;
            //removes values that cannot be added anymore
            possible= realignPossible(values.get(max),max);
            //checks to see if all values can no longer be added
            allDone=checkCompletion(possible);
            //increments the index to the next index
            max++;
        }
        return result;
    }

    //sets all values in the integer array to one
    private  int[] initMaxArray(int[] array){
        for(int i=0;i<array.length;i++){
            array[i]=1;
        }
        return array;
    }

    //sets all values in the boolean array to true
    private  boolean[] initPosArray(boolean[] array){
        for(int i=0;i<array.length;i++){
            array[i]=true;
        }
        return array;
    }
    //goes through the array, and counts the amount of elements
    //after the current element that are larger than the current element
    //and then populates the integer array with them
    private int[] populateArray(int[] array){
        int temp;
        for(int i=0;i<array.length;i++){
            temp=1;
            for(int j=i;j<array.length;j++){
                if(values.get(j)>values.get(i)){
                    temp++;
                }
            }
            array[i]=temp;
        }
        return array;
    }
    //checks to see if all values in the boolean array are false
    private boolean checkCompletion(boolean[] array){
        boolean result=true;
        for(int i=0;i<array.length;i++){
            if(array[i]){
                result=false;
            }
        }
        //System.out.println("b");
        return result;
    }
    //sets values that cannot be added to the list to false
    private boolean[] realignPossible(Integer current, int index){
        for(int i=0;i<possible.length;i++){
            if(current>=values.get(i)||(i<index)){
                possible[i]=false;
            }
        }
        return possible;
    }
}
