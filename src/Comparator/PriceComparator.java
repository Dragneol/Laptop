package Comparator;

import dtos.LaptopDTO;
import java.util.Comparator;

/**
 * Created by DuongPTH
 */
public class PriceComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        LaptopDTO lp1 = (LaptopDTO) o1;
        LaptopDTO lp2 = (LaptopDTO) o2;
        if (lp1.getPrice() == lp2.getPrice()) {
            return 0;
        } else if (lp1.getPrice() > lp2.getPrice()) {
            return 1;
        } else {
            return -1;
        }
    }
}
