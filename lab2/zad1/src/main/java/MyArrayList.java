import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-27.
 */
public class MyArrayList<T> {
    private Object[] list;
    private int size;

    @Override
    public String toString() {
        if(size > 0 ) {
            String sb = ("[");
            for (int i = 0; i < size - 1; i++) {
                sb += list[i] + ", ";
            }
            sb += list[size - 1] + "]";
            return sb;
        } else {
            return null;
        }
    }

    public int getSize() {
        return list.length;
    }

    public void add(Object... items){
        for (Object item : items) {
            if (list == null) {
                list = new Object[1];
                list[0] = item;
                size = 1;
            } else {
                list = Arrays.copyOf(list, size + 1);
                list[size] = item;
                size++;
            }
        }
    }

    public Object getItem(int index){
        Object obj = null;
        for(int i = 0; i < size; i++) {
            if(Objects.equals(index,i))
                obj = list[i];
        }
        return obj;
    }

    public void setItem(int index, T item){
        list[index] = item;
    }

    public void clear() {
        list = null;
        size = 0;
    }

    public void removeItemByIndex(int... index) {
        Object[] temp1, temp2;
        for (int anIndex : index) {
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (Objects.equals(anIndex, i)) {
                        temp1 = new Object[anIndex];
                        temp2 = new Object[size - anIndex];
                        System.arraycopy(list, 0, temp1, 0, anIndex);
                        System.arraycopy(list, anIndex + 1, temp2, 0, size - anIndex - 1);
                        System.arraycopy(temp1, 0, list, 0, temp1.length);
                        System.arraycopy(temp2, 0, list, temp1.length, temp2.length);
                    }
                }
                size--;
            }
        }
    }

    public void removeItemByValue(T... item) {
        Object[] temp1;
        Object[] temp2;
        for (T anItem : item) {
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (Objects.equals(anItem, list[i])) {
                        temp1 = new Object[i];
                        temp2 = new Object[size - i];
                        System.arraycopy(list, 0, temp1, 0, i);
                        System.arraycopy(list, i + 1, temp2, 0, size - i - 1);
                        System.arraycopy(temp1, 0, list, 0, temp1.length);
                        System.arraycopy(temp2, 0, list, temp1.length, temp2.length);
                    }
                }
                size--;
            }
        }
    }


}
