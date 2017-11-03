/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author DuongPTH
 */
public class LaptopDTO implements Serializable, Comparable<LaptopDTO> {

    private String brand, serial, chipSet, chipSetSerial, ramType, ramCapacity, hddCapacity, graphicCard, screen, img;
    private int price;
    private boolean forSE, forSB, forIA, forGD, haveDVD, Deleted;

    public LaptopDTO() {
    }

    public LaptopDTO(String brand, String serial, String chipSet, String chipSetSerial, String ramType, String ramCapacity, String hddCapacity, String graphicCard, String screen, int price, boolean forSE, boolean forSB, boolean forIA, boolean forGD, boolean haveDVD, boolean Deleted) {
        this.brand = brand;
        this.serial = serial;
        this.chipSet = chipSet;
        this.chipSetSerial = chipSetSerial;
        this.ramType = ramType;
        this.ramCapacity = ramCapacity;
        this.hddCapacity = hddCapacity;
        this.graphicCard = graphicCard;
        this.screen = screen;
        this.price = price;
        this.forSE = forSE;
        this.forSB = forSB;
        this.forIA = forIA;
        this.forGD = forGD;
        this.haveDVD = haveDVD;
        this.Deleted = Deleted;
    }

    public LaptopDTO(String brand, String serial, String chipSet, String chipSetSerial, String ramType, String ramCapacity, String hddCapacity, String graphicCard, String screen, String img, int price, boolean forSE, boolean forSB, boolean forIA, boolean forGD, boolean haveDVD, boolean Deleted) {
        this.brand = brand;
        this.serial = serial;
        this.chipSet = chipSet;
        this.chipSetSerial = chipSetSerial;
        this.ramType = ramType;
        this.ramCapacity = ramCapacity;
        this.hddCapacity = hddCapacity;
        this.graphicCard = graphicCard;
        this.screen = screen;
        this.img = img;
        this.price = price;
        this.forSE = forSE;
        this.forSB = forSB;
        this.forIA = forIA;
        this.forGD = forGD;
        this.haveDVD = haveDVD;
        this.Deleted = Deleted;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHddCapacity() {
        return hddCapacity;
    }

    public void setHddCapacity(String hddCapacity) {
        this.hddCapacity = hddCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getChipSet() {
        return chipSet;
    }

    public void setChipSet(String chipSet) {
        this.chipSet = chipSet;
    }

    public String getChipSetSerial() {
        return chipSetSerial;
    }

    public void setChipSetSerial(String chipSetSerial) {
        this.chipSetSerial = chipSetSerial;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(String ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isForSE() {
        return forSE;
    }

    public void setForSE(boolean forSE) {
        this.forSE = forSE;
    }

    public boolean isForSB() {
        return forSB;
    }

    public void setForSB(boolean forSB) {
        this.forSB = forSB;
    }

    public boolean isForIA() {
        return forIA;
    }

    public void setForIA(boolean forIA) {
        this.forIA = forIA;
    }

    public boolean isForGD() {
        return forGD;
    }

    public void setForGD(boolean forGD) {
        this.forGD = forGD;
    }

    public boolean isHaveDVD() {
        return haveDVD;
    }

    public void setHaveDVD(boolean haveDVD) {
        this.haveDVD = haveDVD;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean Deleted) {
        this.Deleted = Deleted;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(brand);
        v.add(serial);
        v.add(chipSetSerial);
        v.add(price);
        return v;
    }

    @Override
    public int compareTo(LaptopDTO that) {
        return (this.getPrice() - that.getPrice());
    }
}
