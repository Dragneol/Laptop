/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dtos.LaptopDTO;
import java.util.List;

/**
 *
 * @author DuongPTH
 */
public class Tools {

    public static int getInt(String input, int min, int max) throws Exception {
        int price = Integer.MIN_VALUE;
        try {
            price = Integer.parseInt(input);
            if (price < min || price > max) {
                throw new Exception("The amount must be an positive integer with in range[" + min + ".." + max + ']');
            }
        } finally {
            if (price == Integer.MIN_VALUE) {
                throw new Exception("Integer Input Expected");
            }
        }
        return price;
    }

    public static boolean checkRAMType(String input) {
        return input.equals("DDR3") || input.equals("DDR4");
    }

    public static boolean checkRAM(String input) throws Exception {
        int kernel = Integer.MIN_VALUE;
        try {
            kernel = Integer.parseInt(input);
            long temp = Math.round(Math.log(kernel) / Math.log(2));
            if (kernel != Math.pow(2, temp)) {
                throw new Exception("RAM Capacity invalid");
            }
        } finally {
            if (kernel == Integer.MIN_VALUE) {
                throw new Exception("Integer Input Expected");
            }
        }
        return false;
    }

    public static boolean checkBrand(String brand) {
        boolean isCorrect = false;
        for (BrandModel value : BrandModel.values()) {
            if (brand.equals(value.name())) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    public static boolean isContain(List<LaptopDTO> list, String serial) {
        boolean isContain = false;
        for (LaptopDTO laptopDTO : list) {
            if (laptopDTO.getSerial().equals(serial)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    public static boolean checkScreen(String input) {
        return input.equals("14") || input.equals("15.6") || input.equals("13") || input.equals("17.3");
    }
    
}
