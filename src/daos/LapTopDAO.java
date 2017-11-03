/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.LaptopDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DuongPTH
 */
public class LapTopDAO implements Serializable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<LaptopDTO> list;
    private static String standardSelection = "SELECT[Brand]\n"
            + "      ,[Serial]\n"
            + "      ,[Chipset]\n"
            + "      ,[ChipsetSerial]\n"
            + "      ,[RAMType]\n"
            + "      ,[RAMCapacity]\n"
            + "      ,[HDDCapacity]\n"
            + "      ,[GraphicCard]\n"
            + "      ,[Screen]\n"
            + "      ,[SE]\n"
            + "      ,[SB]\n"
            + "      ,[GD]\n"
            + "      ,[IA]\n"
            + "      ,[DVD]\n"
            + "      ,[Price]\n"
            + "      ,[Deleted]\n"
            + "      ,[Img]\n"
            + "FROM [Laptop].[dbo].[tbl_Laptop]\n";

    private static String standardInsertion = "INSERT [dbo].[tbl_Laptop] ("
            + "[Brand], [Serial], [Chipset], [ChipsetSerial], [RAMType], [RAMCapacity], [HDDCapacity], [GraphicCard], [Screen], [SE], [SB], [GD], [IA], [DVD], [Price],[Deleted],[Img]) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String standardUpdate = "update tbl_Laptop set "
            + "Brand=?,"
            + "Chipset=?,"
            + "ChipsetSerial=?,"
            + "RAMType=?,"
            + "RAMCapacity=?,"
            + "HDDCapacity=?,"
            + "GraphicCard=?,"
            + "Screen=?,"
            + "SE=?,"
            + "SB=?,"
            + "GD=?,"
            + "IA=?,"
            + "DVD=?,"
            + "Price=?,"
            + "Deleted=?,"
            + "Img=?"
            + "  where Serial=?";

    public LapTopDAO() {
        list = new ArrayList<>();
    }

    public List<LaptopDTO> getList() {
        return list;
    }

    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewRecord() throws Exception {
        while (resultSet.next()) {
            String brand, serial, chipSet, chipSetSerial, ramType, ramCapacity, hddCapacity, graphicCard, screen;
            int price;
            boolean del, se, sb, ia, gd, dvd;
            String img;
            brand = resultSet.getString("Brand");
            serial = resultSet.getString("Serial");
            chipSetSerial = resultSet.getString("ChipsetSerial");
            chipSet = resultSet.getString("Chipset");
            ramType = resultSet.getString("RAMType");
            ramCapacity = resultSet.getString("RAMCapacity");
            hddCapacity = resultSet.getString("HDDCapacity");
            graphicCard = resultSet.getString("GraphicCard");
            screen = resultSet.getString("Screen");
            price = resultSet.getInt("Price");
            del = resultSet.getBoolean("Deleted");
            se = resultSet.getBoolean("SE");
            sb = resultSet.getBoolean("SB");
            gd = resultSet.getBoolean("GD");
            ia = resultSet.getBoolean("IA");
            dvd = resultSet.getBoolean("DVD");
            img = resultSet.getString("Img");
//            LaptopDTO dto = new LaptopDTO(brand, serial, chipSet, chipSetSerial, ramType, ramCapacity, hddCapacity, graphicCard, screen, price, se, sb, ia, gd, dvd, del);
            LaptopDTO dto = new LaptopDTO(brand, serial, chipSet, chipSetSerial, ramType, ramCapacity, hddCapacity, graphicCard, screen, img, price, se, sb, ia, gd, dvd, del);
            list.add(dto);
        }
    }

    public List<LaptopDTO> showTable(String request) {
        try {
            // important, remember to clear first then add
            list.clear();
            String sql = standardSelection + request;
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            addNewRecord();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public LaptopDTO getLaptopByRow(int row) {
        return list.get(row);
    }

    public List<LaptopDTO> search(String brand, String serial, String chipSet, String chipSetSerial, String ramType, String ramCapacity, String hddCapacity, String graphicCard, String screen, int from, int to, boolean forSE, boolean forSB, boolean forIA, boolean forGD, boolean haveDVD) {
        try {
            String sql, require = "";
            list.clear();
            sql = standardSelection + "WHERE Brand like ?\n"
                    + "and Serial like ?\n"
                    + "and Chipset like ?\n"
                    + "and ChipsetSerial like ?\n"
                    + "and RAMType like ?\n"
                    + "and RAMCapacity like ?\n"
                    + "and HDDCapacity like ?\n"
                    + "and Screen like ?\n"
                    + "and Price >= ? and Price <= ?\n"
                    + "and DVD = ?\n";
            if (forSE) {
                require += "SE = '1'\n";
            }
            if (require.length() != 0 && forSB) {
                require += "or ";
            }
            if (forSB) {
                require += "SB = '1'\n";
            }
            if (require.length() != 0 && forGD) {
                require += "or ";
            }
            if (forGD) {
                require += "GD = '1'\n";
            }
            if (require.length() != 0 && forIA) {
                require += "or ";
            }
            if (forIA) {
                require += "IA = '1'\n";
            }
            if (require.length() != 0) {
                require = "and(\n" + require + ')';
                sql += require;
            }
            sql += "Order by Price";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, '%' + brand + '%');
            preparedStatement.setString(2, '%' + serial + '%');
            preparedStatement.setString(3, '%' + chipSet + '%');
            preparedStatement.setString(4, '%' + chipSetSerial + '%');
            preparedStatement.setString(5, '%' + ramType + '%');
            preparedStatement.setString(6, '%' + ramCapacity + '%');
            preparedStatement.setString(7, '%' + hddCapacity + '%');
            preparedStatement.setString(8, '%' + screen + '%');
            preparedStatement.setInt(9, from);
            preparedStatement.setInt(10, to);
            preparedStatement.setBoolean(11, haveDVD);
            resultSet = preparedStatement.executeQuery();
            addNewRecord();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertNewLaptop(String brand, String serial, String chipSet, String chipSetSerial, String ramType, String ramCapacity, String hddCapacity, String graphicCard, String screen, String img, int price, boolean forSE, boolean forSB, boolean forIA, boolean forGD, boolean haveDVD, boolean Deleted) {
        boolean success = false;
        try {
            String sql = standardInsertion;
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, serial);
            preparedStatement.setString(3, chipSet);
            preparedStatement.setString(4, chipSetSerial);
            preparedStatement.setString(5, ramType);
            preparedStatement.setString(6, ramCapacity);
            preparedStatement.setString(7, hddCapacity);
            preparedStatement.setString(8, graphicCard);
            preparedStatement.setString(9, screen);
            preparedStatement.setBoolean(10, forSE);
            preparedStatement.setBoolean(11, forSB);
            preparedStatement.setBoolean(12, forGD);
            preparedStatement.setBoolean(13, forIA);
            preparedStatement.setBoolean(14, haveDVD);
            preparedStatement.setInt(15, price);
            preparedStatement.setBoolean(16, Deleted);
            preparedStatement.setString(17, img);
            list.add(new LaptopDTO(brand, serial, chipSet, chipSetSerial, ramType, ramCapacity, hddCapacity, graphicCard, screen, img, price, forSE, forSB, forIA, forGD, haveDVD, Deleted));
            success = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return success;
    }

    public boolean updateNewLaptop(String brand, String serial, String chipSet, String chipSetSerial, String ramType, String ramCapacity, String hddCapacity, String graphicCard, String screen, String img, int price, boolean forSE, boolean forSB, boolean forIA, boolean forGD, boolean haveDVD, boolean Deleted) {
        boolean success = false;
        try {
            String sql = standardUpdate;
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, chipSet);
            preparedStatement.setString(3, chipSetSerial);
            preparedStatement.setString(4, ramType);
            preparedStatement.setString(5, ramCapacity);
            preparedStatement.setString(6, hddCapacity);
            preparedStatement.setString(7, graphicCard);
            preparedStatement.setString(8, screen);
            preparedStatement.setBoolean(9, forSE);
            preparedStatement.setBoolean(10, forSB);
            preparedStatement.setBoolean(11, forGD);
            preparedStatement.setBoolean(12, forIA);
            preparedStatement.setBoolean(13, haveDVD);
            preparedStatement.setInt(14, price);
            preparedStatement.setBoolean(15, Deleted);
            preparedStatement.setString(16, img);
            preparedStatement.setString(17, serial);
            success = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return success;
    }
}
