/**
 * Created by Michal on 2015-10-27.
 */
public class MainClass {
    static MyArrayList<MyArrayList<Integer>> arraylist = new MyArrayList<MyArrayList<Integer>>();
    public static void main(String[] args) {
        MyArrayList<Integer> a1 = new MyArrayList<Integer>();
        a1.add(2, 445, 101);
        MyArrayList<Integer> a2 = new MyArrayList<Integer>();
        a2.add(4343,999);
        MyArrayList<Integer> a3 = new MyArrayList<Integer>();
        a3.add(9);

        arraylist.add(a1, a2, a3);

        System.out.println(arraylist);

        arraylist.removeItemByIndex(0,1,2);
        arraylist.clear();
        System.out.println(arraylist);

    }
}
