package design.minstack;

import java.util.ArrayList;
import java.util.List;

class MinStack {

    /** initialize your data structure here. */
    List<Integer> mainList;
    Integer min = Integer.MAX_VALUE;

    public MinStack() {
        mainList=new ArrayList<>();
    }

    public void push(int val) {
        mainList.add(val);
        if(val<min){
            min=val;
        }
    }

    public void pop() {
        if(mainList.size()==0){
            return;
        }

        if(mainList.get(mainList.size()-1).intValue()==min){
            min = Integer.MAX_VALUE;
            for(int i=0;i<mainList.size()-1;i++){
                min=Math.min(min,mainList.get(i));
            }
        }

        mainList.remove(mainList.size()-1);
    }

    public int top() {
        return mainList.get(mainList.size()-1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {

        MinStack min = new MinStack();
        min.push(512);
        min.push(-1024);
        min.push(-1024);
        min.push(512);

        min.pop();
        System.out.println(min.getMin());
        min.pop();
        System.out.println(min.getMin());
        min.pop();
        System.out.println(min.getMin());
    }
}