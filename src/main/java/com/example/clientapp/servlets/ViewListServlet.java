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
import java.util.*;

@WebServlet(name = "ViewListServlet", value = "/ViewListServlet")
public class ViewListServlet extends HttpServlet {

    @EJB
    private SelectBean selectBean;
    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sendHtml(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sendHtml(request, response);
    }

    private void sendHtml(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Map<ClientEntity, List<AddressEntity>> clientMap = createMap(selectBean.getAllClient());
        String filterParam = request.getParameter("filter");
        String typesParam = request.getParameter("type");
        String resetParam = request.getParameter("reset");
        String deleteParam = request.getParameter("deleteId");
        if (filterParam != null && !filterParam.isEmpty()) {
            clientMap = createMap(selectBean.getClientsParam(filterParam, typesParam));
        } if (resetParam != null && !resetParam.isEmpty()) {
            clientMap = createMap(selectBean.getAllClient());
        }
        else selectBean.getAllClient();
        sendTable(request, response, clientMap);
    }

    private void sendTable(HttpServletRequest request, HttpServletResponse response, Map<ClientEntity, List<AddressEntity>> clientMap) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<html>" +
                "<body align=\"center\">");
        out.println("<h2>" + "Все клиенты" + "</h2>");
        out.println("<form action=\"ViewListServlet\" method=\"get\" align=\"center\">\n" +
                "<input list=\"types\" name=\"type\" id=\"type\">\n" +
                "<datalist id=\"types\">\n" +
                "<option value=\"Физическое лицо\">\n" +
                "<option value=\"Юридическое лицо\">\n" +
                "</datalist>\n" +
                "<form action=\"ViewListServlet\" method=\"get\" align=\"center\">\n" +
                "    <input type=\"text\" name=\"filter\">\n" +
                "    <input type=\"submit\" value=\"Filter\">\n" +
                "</form>");
        out.println("<form action=\"ViewListServlet\" method=\"get\" align=\"center\">\n" +
                "    <input type=\"submit\" name=\"reset\" value=\"Reset\">\n" +
                "</form><br>");
        out.println("<table align=\"center\" border=\"1\" cellpadding=\"5\">");
        out.println("<tr>");
        out.println("<td align=\"center\">ID</td>");
        out.println("<td align=\"center\">Имя клиента</td>");
        out.println("<td align=\"center\">Тип клиента</td>");
        out.println("<td align=\"center\">Дата добавления</td>");
        out.println("<td align=\"center\">Ip-адрес</td>");
        out.println("<td align=\"center\">MAC-адрес</td>");
        out.println("<td align=\"center\">Модель устройства</td>");
        out.println("<td align=\"center\">Адрес места нахождения</td>");
        out.println("<td align=\"center\">Изменить</td>");
        out.println("<td align=\"center\">Удалить</td>");
        out.println("</tr>");
        for (ClientEntity client : clientMap.keySet()) {
            if (client.getAddresses().size() > 0) {
                for (AddressEntity address : clientMap.get(client)) {
                    out.println("<tr>");
                    out.println("<td>" + client.getId() + "</td>");
                    out.println("<td>" + client.getName() + "</td>");
                    out.println("<td>" + client.getType() + "</td>");
                    out.println("<td>" + client.getAdded() + "</td>");
                    out.println("<td>" + address.getIp() + "</td>");
                    out.println("<td>" + address.getMac() + "</td>");
                    out.println("<td>" + address.getModel() + "</td>");
                    out.println("<td>" + address.getAddress() + "</td>");
                    out.println("<td align=\"middle\"><form action=\"UpdateServlet\" method=\"post\">" +
                            "<button><img src=\"update.png\" alt=\"Изменить\" width=\"20\" height=\"20\"></button>" +
                            "<input type=\"hidden\" name=\"updateId\" value=\"" + client.getId() + "\">" +
                            "</form></td>");
                    out.println("<td align=\"middle\"><form action=\"DeleteServlet\" method=\"get\">" +
                            "<button><img src=\"delete.png\" alt=\"Изменить\" width=\"20\" height=\"20\"></button>" +
                            "<input type=\"hidden\" name=\"deleteId\" value=\"" + client.getId() + "\">" +
                            "</form></td>");
                    out.println("</tr>");
                }
            } else {
                out.println("<tr>");
                out.println("<td>" + client.getId() + "</td>");
                out.println("<td>" + client.getName() + "</td>");
                out.println("<td>" + client.getType() + "</td>");
                out.println("<td>" + client.getAdded() + "</td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td align=\"middle\"><form action=\"UpdateServlet\" method=\"post\">" +
                        "<button><img src=\"update.png\" alt=\"Изменить\" width=\"20\" height=\"20\"></button>" +
                        "<input type=\"hidden\" name=\"updateId\" value=\"" + client.getId() + "\">" +
                        "</form></td>");
                out.println("<td align=\"middle\"><form action=\"DeleteServlet\" method=\"get\">" +
                        "<button><img src=\"delete.png\" alt=\"Удалить\" width=\"20\" height=\"20\"></button>" +
                        "<input type=\"hidden\" name=\"deleteId\" value=\"" + client.getId() + "\">" +
                        "</form></td>");
                out.println("</tr>");
            }
        }
        out.println("</table>");
        out.println("</body></html>");
    }

    private Map<ClientEntity, List<AddressEntity>> createMap(Set<ClientEntity> clients) {
        Map<ClientEntity, List<AddressEntity>> clientMap = new HashMap<>();
        for (ClientEntity client : clients) {
            clientMap.put(client, client.getAddresses());
        }
        return clientMap;
    }

}