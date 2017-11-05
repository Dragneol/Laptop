/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
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
public class UserDAO implements Serializable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<UserDTO> userList;

    public UserDAO() {
        userList = new ArrayList<>();
    }

    public List<UserDTO> getUserList() {
        return userList;
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

    public String login(String user, String pass) {
        String role = "failed";
        boolean avail = false;
        try {
            String sql = "select Role,Available from tbl_User where Username = ? and Password = ?";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("Role");
                avail = resultSet.getBoolean("Available");
                if (!avail) {
                    role = "not activated";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return role;
    }

    public List<UserDTO> showTable() {
        userList.clear();
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Password]\n"
                    + "      ,[Role]\n"
                    + "      ,[Deletable]\n"
                    + "      ,[Available]\n"
                    + "  FROM [Laptop].[dbo].[tbl_User]";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("Username");
                String pass = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                boolean del = resultSet.getBoolean("Deletable");
                boolean avail = resultSet.getBoolean("Available");
                userList.add(new UserDTO(user, pass, role, del, avail));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return userList;
    }

    public boolean delAccount(String account) {
        boolean deleted = false;
        try {
            String sql = "delete from tbl_User where Username = ? and Deletable ='1'";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return deleted;
    }

    public boolean chmodAccount(String account, boolean capability) {
        boolean success = false;
        try {
            String sql = "update tbl_User set Available = ? where Username = ?";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, !capability);
            preparedStatement.setString(2, account);
            success = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return success;
    }

    public boolean chpassAccount(String account, String pass, String newPass) {
        boolean success = false;
        try {
            String sql = "update tbl_User set Password = ? where Username = ? and Password = ?";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, account);
            preparedStatement.setString(3, pass);
            success = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return success;
    }

    public UserDTO getAccountByUser(String account) {
        UserDTO dto = null;
        try {
            String sql = "select Password from tbl_User where Username = ?";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pass = resultSet.getString("Password");
                dto = new UserDTO(account, pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean inserNew(UserDTO dto) {
        boolean success = false;
        try {
            String sql = "insert into tbl_User values (?,?,'Editor','1','0')";
            connection = db.DatabseConnector.getConnection("Laptop", "sa", "123");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getUser());
            preparedStatement.setString(2, dto.getPass());
            success = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return success;
    }
}
