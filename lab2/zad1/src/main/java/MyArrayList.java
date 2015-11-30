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

    public int size() {
        return eleCount;
    }

    public void add(Object... items){
        for (Object item : items) {
            if (list == null) {
                list = new Object[1];
                list[0] = item;
                eleCount = 1;
            } else if(eleCount < size){
                list[eleCount] = item;
                eleCount++;
            } else {
                size *= 2;
                list = Arrays.copyOf(list, size);
                list[eleCount] = item;
                eleCount++;
            }
        }
    }

    public Object getItem(int index){
        Object obj = null;
        for(int i = 0; i < eleCount; i++) {
            if(Objects.equals(index,i))
                obj = list[i];
        }
        return obj;
    }

    public void setItem(int index, T item){
        list[index] = item;
    }

    public void clear() {
        size = 10;
        list = new Object[size];
        eleCount = 0;
    }
// 0, 1 ,2,3,4, , , , , ,
    public void removeItemByIndex(int index) {
        Object[] temp1, temp2;
            if (eleCount > 0) {
                for (int i = 0; i < eleCount; i++) {
                    if (Objects.equals(index, i)) {
                        temp1 = new Object[index];
                        temp2 = new Object[size - index - 1];
                        System.arraycopy(list, 0, temp1, 0, index);
                        System.arraycopy(list, index + 1, temp2, 0, size - index - 1);
                        System.arraycopy(temp1, 0, list, 0, temp1.length);
                        System.arraycopy(temp2, 0, list, temp1.length, temp2.length);
                    }
                }
                eleCount--;
            }
    }

    public void removeItemByValue(T... item) {
        Object[] temp1;
        Object[] temp2;
        for (T anItem : item) {
            if (eleCount > 0) {
                for (int i = 0; i < eleCount; i++) {
                    if (Objects.equals(anItem, list[i])) {
                        temp1 = new Object[i];
                        temp2 = new Object[size - i - 1];
                        System.arraycopy(list, 0, temp1, 0, i);
                        System.arraycopy(list, i + 1, temp2, 0, size - i - 1);
                        System.arraycopy(temp1, 0, list, 0, temp1.length);
                        System.arraycopy(temp2, 0, list, temp1.length, temp2.length);
                    }
                }
                eleCount--;
            }
        }
    }


}
