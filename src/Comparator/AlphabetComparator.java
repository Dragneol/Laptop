package Comparator;

import dtos.LaptopDTO;
import java.util.Comparator;

/**
 * Created by DuongPTH
 */
public class AlphabetComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        LaptopDTO lp1 = (LaptopDTO) o1;
        LaptopDTO lp2 = (LaptopDTO) o2;
        int result = lp1.getBrand().compareToIgnoreCase(lp2.getBrand());
        if (result == 0) {
            result = lp1.getSerial().compareToIgnoreCase(lp2.getSerial());
        }
        return result;
    }
}
