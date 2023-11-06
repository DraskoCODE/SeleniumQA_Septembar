package helper;

import java.util.List;

public class ListManager {

    public static boolean isListSortedAsc(List<Double> list) {
        boolean isSorted = true;
        for(int i = 0; i < list.size() - 1; i++) {
            int j = i + 1;
            if(list.get(i) <= list.get(j)) {

            }
            else {
                System.out.println("Cene nisu u dobrom redosledu");
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

}
