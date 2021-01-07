import java.util.ArrayList;
import java.util.List;

public class SequenceFinder {
    int[] maxes;
    boolean[] possible;
    List<Integer> values;
    public SequenceFinder(List<Integer> full){
        values=full;
        maxes= new int[values.size()];
        possible=new boolean[values.size()];
        maxes=initMaxArray(maxes);

    }

    public  List<Integer> longestConsecIncrSequence(){
        int startLength,start,tempStart,tempLength;
        startLength=1;start=0;tempStart=0;tempLength=1;
        boolean inSequence=false;
        Integer curr,next;
        for(int i=0; i<values.size()-1;i++){
            curr= values.get(i);
            next= values.get(i+1);
            if(curr>=next){
                inSequence=false;
                tempLength=1;
            }else { //curr<next
                tempLength++;
                if(!inSequence){
                    tempStart=i;
                    inSequence=true;
                }
                if(tempLength>startLength){
                    start=tempStart;
                    startLength=tempLength;
                }
            }
        }
        List<Integer> result=values.subList(start,start+startLength);
        return result;
    }
    //creates a list of the longest increasing non-consecutive sequence
    //there can be other sequences of the same or shorter length
    public  List<Integer> longestNonConsecIncrSequence(){
        List<Integer> result=new ArrayList<>(values.size());
        possible=initPosArray(possible);
        boolean allDone=false;
        int max=0;int count=0;;
        maxes=populateArray(maxes);
        //printPossible();
        while((!allDone)&&(count<values.size())&&(max<values.size())){
            //printPossible();
            //System.out.println(max +" " +count);
            for(int i=max;i<values.size()-1;i++){
                if((possible[i])&&(maxes[max]<maxes[i])){
                    //System.out.print("e"+max);
                    max=i;
                    //System.out.print("f"+max);

                }
            }
            //System.out.println("");
            if(possible[max]) {
                result.add(values.get(max));
            }
            possible[max]=false;
            possible= realignPossible(values.get(max),max);
            allDone=checkCompletion(possible);
            max++;
            count++;


        }
        return result;
    }
    private  int[] initMaxArray(int[] array){
        for(int i=0;i<array.length;i++){
            array[i]=1;
        }
        return array;
    }
    private  boolean[] initPosArray(boolean[] array){
        for(int i=0;i<array.length;i++){
            array[i]=true;
        }
        return array;
    }
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
        //System.out.println("c");
        return array;
    }
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
    private boolean[] realignPossible(Integer current, int index){
        for(int i=0;i<possible.length;i++){
            if(current>=values.get(i)||(i<index)){
                possible[i]=false;
            }
        }
        //System.out.println("a");
        return possible;
    }
    private void printPossible(){
        int j=0;
        for(int i=0;i<possible.length;i++){


            if(possible[i]){
                System.out.print("t");
            }else {
                System.out.print("f");
            }
        }
       // System.out.println("");
        System.out.println("");
    }
}
