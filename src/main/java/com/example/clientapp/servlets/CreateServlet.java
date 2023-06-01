package com.example.clientapp.servlets;

import com.example.clientapp.model.AddressEntity;
import com.example.clientapp.model.ClientEntity;
import com.example.clientapp.services.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "CreateServlet", value = "/CreateServlet")
public class CreateServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sendHtml(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createClient(request);
        getServletContext().getRequestDispatcher("/ViewListServlet").forward(request, response);
    }



    private void sendHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"CreateServlet\" method=\"get\">");
        out.println("<h3>" + "Ввод нового клиента" + "</h3>");
        out.println("<label>Имя</label><br>");
        out.println("<input type=\"text\" name=\"name\" maxlength=\"100\" required><br><br>");
        out.println("<label>Тип клиента</label><br>");
        out.println("<input list=\"types\" name=\"type\" id=\"type\">");
        out.println("<datalist id=\"types\">");
        out.println("<option value=\"Физическое лицо\">");
        out.println("<option value=\"Юридическое лицо\">");
        out.println("</datalist>\n" + "<br><br>");
        out.println("<label>Дата добавления</label><br>");
        out.println("<input type=\"date\" name=\"added\" required><br><br>");
        out.println("<label>IP-адрес</label><br>");
        out.println("<input type=\"number\" name=\"ip1\" min=\"0\" max=\"255\" maxlength=\"3\" required>");
        out.println("<label>.</label>");
        out.println("<input type=\"number\" name=\"ip2\" min=\"0\" max=\"255\" maxlength=\"3\" required>");
        out.println("<label>.</label>");
        out.println("<input type=\"number\" name=\"ip3\" min=\"0\" max=\"255\" maxlength=\"3\" required>");
        out.println("<label>.</label>");
        out.println("<input type=\"number\" name=\"ip4\" min=\"0\" max=\"255\" maxlength=\"3\" required><br><br>");
        out.println("<label>MAC-адрес</label><br>");
        out.println("<input type=\"text\" name=\"mac1\" size=\"2\" maxlength=\"2\" required>");
        out.println("<label>-</label>");
        out.println("<input type=\"text\" name=\"mac2\" size=\"2\" maxlength=\"2\" required>");
        out.println("<label>-</label>");
        out.println("<input type=\"text\" name=\"mac3\" size=\"2\" maxlength=\"2\" required>");
        out.println("<label>-</label>");
        out.println("<input type=\"text\" name=\"mac4\" size=\"2\" maxlength=\"2\" required>");
        out.println("<label>-</label>");
        out.println("<input type=\"text\" name=\"mac5\" size=\"2\" maxlength=\"2\" required>");
        out.println("<label>-</label>");
        out.println("<input type=\"text\" name=\"mac6\" size=\"2\" maxlength=\"2\" required><br><br>");
        out.println("<label>Модель устройства</label><br>");
        out.println("<input type=\"text\" name=\"model\" maxlength=\"100\" required><br><br>");
        out.println("<label>Адрес места нахождения</label><br>");
        out.println("<input type=\"text\" name=\"address\" maxlength=\"200\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Создать\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    private void createClient(HttpServletRequest request) throws IOException, NumberFormatException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Date added = Date.valueOf(request.getParameter("added"));
        String ip1 = request.getParameter("ip1");
        String ip2 = request.getParameter("ip2");
        String ip3 = request.getParameter("ip3");
        String ip4 = request.getParameter("ip4");
        String ip = (ip1 + "." + ip2 + "." + ip3 + "." + ip4);
        String mac1 = request.getParameter("mac1");
        String mac2 = request.getParameter("mac2");
        String mac3 = request.getParameter("mac3");
        String mac4 = request.getParameter("mac4");
        String mac5 = request.getParameter("mac5");
        String mac6 = request.getParameter("mac6");
        String mac = (mac1 + "-" + mac2 + "-" + mac3 + "-" + mac4 + "-" + mac5 + "-" + mac6);
        String model = request.getParameter("model");
        String address = request.getParameter("address");

        ClientEntity temp = new ClientEntity(name, type, added);
        AddressEntity tempAd = new AddressEntity(ip, mac, model, address);
        temp.setAddress(tempAd);
        updateBean.createClient(temp);
    }
}