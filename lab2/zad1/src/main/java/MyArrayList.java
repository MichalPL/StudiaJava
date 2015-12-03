import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-27.
 */
public class MyArrayList<T> {
    private Object[] list;
    private int size;
    private int eleCount;

    public MyArrayList() {
        size = 10;
        list = new Object[size];
        eleCount = 0;
    }

    @Override
    public String toString() {
        if(eleCount > 0 ) {
            String sb = ("[");
            for (int i = 0; i < eleCount - 1; i++) {
                sb += list[i] + ", ";
            }
            sb += list[eleCount - 1] + "]";
            return sb;
        } else {
            return "[]";
        }
    }

    public boolean isEmpty() {
        return (eleCount == 0);
    }

    public boolean contains(Object item) {
        for (int i = 0; i < eleCount; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return eleCount;
    }

    public void add(Object... items){
        for (Object item : items) {
            if(eleCount >= size) {
                size *= 2;
                list = Arrays.copyOf(list, size);
            }
            list[eleCount] = item;
            increaseElementCount();
        }
    }

    private void increaseElementCount() {
        eleCount++;
    }

    public Object getItem(int index){
        return list[index];
    }

    public void setItem(int index, T item) throws Exception{
        if(list.length < index)
            throw new Exception("Out of index!");
        list[index] = item;
    }

    public void clear() {
        size = 10;
        list = new Object[size];
        eleCount = 0;
    }

    public int indexOf(Object item) {
        for (int i = 0; i < eleCount; i++) {
            if (list[i].equals(item))
                return i;
        }
        return -1;
    }

    public void removeItemByValue(T... item) throws Exception {
        if(isEmpty()) throw new Exception("Lista jest pusta!");

        for (T anItem : item) {
            if (!contains(anItem)) throw new Exception("Brak elementu " + anItem);
            for (int i = indexOf(anItem); i < eleCount - 1; i++) {
                list[i] = list[i + 1];
            }
            list[eleCount - 1] = null;
            eleCount--;
        }
    }

}
