package com.example.clientapp.servlets;

import com.example.clientapp.model.AddressEntity;
import com.example.clientapp.model.ClientEntity;
import com.example.clientapp.services.SelectBean;
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
import java.util.*;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;
    @EJB
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //jakarta.ejb.EJBException: java.lang.NullPointerException при попытке использования
//        updateClient(request);
        getServletContext().getRequestDispatcher("/ViewListServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("updateId");
        sendSimple(request, response, id);
        //jakarta.ejb.EJBException: java.lang.NullPointerException при переходе на сервлет с этой строкой
//        ClientEntity client = selectBean.getClientById(Long.getLong(id));
        //Это лишнее, создано для проверки сервлета
        AddressEntity address = new AddressEntity("111.111.111.111", "11-11-11-11-11-11", "Модель0", "Россия");
        ClientEntity client = new ClientEntity("Клиент 0", "Физическое лицо", new Date(2020, 12,10));
        client.setAddress(address);

        sendHtml(request, response, client);
    }

    private void sendHtml(HttpServletRequest request, HttpServletResponse response, ClientEntity client) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String id = request.getParameter("updateId");

        PrintWriter out = response.getWriter();
        out.println("<html><body align=\"center\">");
        out.println("<h2>" + "Изменение записи о клиенте" + "</h2>");
        out.println("<form action=\"UpdateServlet\" method=\"get\" align=\"center\">\n");
        out.println("<table align=\"center\" border=\"1\" cellpadding=\"5\">");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Имя клиента</td>");
        out.println("<td>Тип клиента</td>");
        out.println("<td>Дата добавления</td>");
        out.println("<td>Ip-адрес</td>");
        out.println("<td>MAC-адрес</td>");
        out.println("<td>Модель устройства</td>");
        out.println("<td>Адрес места нахождения</td>");
        out.println("</tr>");
        if (client.getAddresses().size() > 0) {
            for (int i = 0; i < client.getAddresses().size(); i++) {
                out.println("<tr>");
                out.println("<td>" + client.getId() + "</td>");
                out.println("<td><input type=\"text\" name =\"name\" value=\"" + client.getName() + "\"></td>");
                out.println("<td><input type=\"text\" name =\"type\" value=\"" + client.getType() + "\"></td>");
                out.println("<td><input type=\"date\" name =\"added\" value=\"" + client.getAdded() + "\"></td>");
                out.println("<td><input type=\"text\" name =\"ip\" value=\"" + client.getAddresses().get(i).getIp() + "\"></td>");
                out.println("<td><input type=\"text\" name =\"mac\" value=\"" + client.getAddresses().get(i).getMac() + "\"></td>");
                out.println("<td><input type=\"text\" name =\"model\" value=\"" + client.getAddresses().get(i).getModel() + "\"></td>");
                out.println("<td><input type=\"text\" name =\"address\" value=\"" + client.getAddresses().get(i).getAddress() + "\"></td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr>");
            out.println("<td>" + client.getId() + "</td>");
            out.println("<td><input type=\"text\" name =\"name\" value=\"" + client.getName() + "\"></td>");
            out.println("<td><input type=\"text\" name =\"type\" value=\"" + client.getType() + "\"></td>");
            out.println("<td><input type=\"date\" name =\"added\" value=\"" + client.getAdded() + "\"></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("</tr>");
        }
        out.println("</table><br>");
        out.println("<input type=\"submit\" name=\"update\" value=\"Обновить\">\n" +
                "</form><br>");
        out.println("</body></html>");
    }

    public void updateClient(HttpServletRequest request){
        ClientEntity temp = new ClientEntity(request.getParameter("name"), request.getParameter("type"),
                Date.valueOf(request.getParameter("added")));
        temp.setAddress(new AddressEntity(request.getParameter("ip"), request.getParameter("mac"),
                request.getParameter("model"), request.getParameter("address")));
        updateBean.updateClient(temp);
    }

    private void sendSimple(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<body align=\"center\">");
        out.println("<h2>" + "Редактирование" + id + "</h2>");
        out.println("</body></html>");
    }
}