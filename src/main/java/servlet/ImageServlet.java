package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    public static class Imagination {
        public static BufferedImage drawGraphics() {
            BufferedImage imgBuff = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imgBuff.createGraphics();
            g2.setFont(new Font("Serif", Font.BOLD, 48));
            g2.setColor(Color.GREEN);
            g2.drawString("Welcome !", 100, 100);
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, new float[]{10.0f}, 0.0f));
            Polygon triangles = new Polygon();
            triangles.addPoint(30, 30);
            triangles.addPoint(60, 30);
            triangles.addPoint(50, 70);
            g2.drawPolygon(triangles);
            g2.setStroke(new BasicStroke());
            g2.setColor(Color.red);
            int[] x = {100, 110, 110, 110, 105};
            int[] y = {100, 110, 120, 120, 105};
            g2.drawPolygon(x, y, 5);

            g2.setPaint(new GradientPaint(250, 100, Color.GREEN, 350, 150, Color.YELLOW));
            g2.fill(new Ellipse2D.Double(250, 100, 100, 50));
            g2.dispose();
            return imgBuff;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(Imagination.drawGraphics(), "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}