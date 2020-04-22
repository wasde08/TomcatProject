package DAO;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

import java.io.*;
import java.sql.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class Service {

    public static void main(String[] args) {
        save("e.doc");

        //createTXT();
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
            String n = name.replaceAll("\\.", "|");
            if (n.split("\\|")[1].equals("doc")){
                FileInputStream fis = new FileInputStream(name);
                HWPFDocument document = new HWPFDocument(fis);
                WordExtractor extractor = new WordExtractor(document);
                String[] fileData = extractor.getParagraphText();
                for (int i = 0; i < fileData.length; i++)
                {
                    if (fileData[i] != null)
                        str+= fileData[i] +" ";
                }

            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
                while ((st = br.readLine()) != null) {
                    str += st + " ";
                }
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
