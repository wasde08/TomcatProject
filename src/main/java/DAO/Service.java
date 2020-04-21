package DAO;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

import java.io.*;
import java.sql.*;

public class Service {

    public static void main(String[] args) {
        //save(new File("t.txt"));
        createTXT();
    }

    public static void createTXT() {
        String q = "SELECT * FROM txt";
        int i = 0;
        try (Connection con = JDBCConnection.getDBconnection()) {
            PreparedStatement pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery();
            FileWriter writer = new FileWriter("r.txt");
            while (rs.next()) {
                q = rs.getString("qwe");
                for (int j = 0; j < q.split(" ").length; j++) {
                    if (j+1 ==q.split(" ").length){
                        writer.append(q.split(" ")[j]);
                    }else {
                    writer.append(q.split(" ")[j] + "\n");
                    }
                }
            }
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public static void save(String name) {
        String query = "INSERT INTO txt (id, qwe) VALUES(?, ?)";
        String st;
        String str = "";
        try (Connection con = JDBCConnection.getDBconnection()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            while ((st = br.readLine()) != null) {
                str += st + " ";
            }
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, 1);
            pst.setString(2, str);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
