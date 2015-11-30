/**
 * Created by Michal on 2015-10-27.
 */
public class MainClass {
    static MyArrayList<MyArrayList<Integer>> arraylist = new MyArrayList<MyArrayList<Integer>>();
    public static void main(String[] args) {
        MyArrayList<Integer> a1 = new MyArrayList<Integer>();
        a1.add(0, 1,2,3,4,5,6,7,8,9,5445,11,12);
        MyArrayList<Integer> a2 = new MyArrayList<Integer>();
        a2.add(4343,999);
        MyArrayList<Integer> a3 = new MyArrayList<Integer>();
        a3.add(9);

        arraylist.add(a1, a2, a3);

        System.out.println(arraylist);

        System.out.println(a1);
        a1.removeItemByIndex(11);
        System.out.println(a1);
        a1.removeItemByValue(5445, 2,6,4,8);
        System.out.println(a1);
        System.out.println(a1.size());
        System.out.println(arraylist);
        arraylist.clear();
        System.out.println(arraylist);

    }
}
