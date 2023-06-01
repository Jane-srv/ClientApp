package com.example.clientapp.servlets;

import com.example.clientapp.model.AddressEntity;
import com.example.clientapp.model.ClientEntity;
import com.example.clientapp.services.DbManager;
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
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @EJB
    private SelectBean selectBean;

    @EJB
    DbManager dbManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbManager.deleteClient(selectBean.getClientById(Long.getLong(request.getParameter("deleteId"))));
        getServletContext().getRequestDispatcher("/ViewListServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendHtml(request, response);
    }

    private void sendHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //С этими строками получаю ошибку при попытке перехода на этот сервлет
//        String idStr = request.getParameter("deleteId");
//        long id = Long.getLong(idStr);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //Это лишнее сделано для проверки открываемости сервлета вообще
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<body align=\"center\">");
        out.println("<h2>" + "Удаление" + "</h2>");
        out.println("</body></html>");
    }

    private void deleteClient(ClientEntity client){
        updateBean.deleteClient(client);
    }

}