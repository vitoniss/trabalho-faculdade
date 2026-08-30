package br.com.inventory.servlet;

import java.io.IOException;

import br.com.inventory.dao.EquipamentoDAO;
import br.com.inventory.dao.JPAUtil;
import br.com.inventory.dao.LaboratorioDAO;
import br.com.inventory.modelo.Equipamento;
import br.com.inventory.modelo.Laboratorio;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/equipamentos", "/novo-equipamento"})
public class EquipamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
    private final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/novo-equipamento".equals(request.getServletPath())) {
            request.setAttribute("laboratorios", laboratorioDAO.listarTodos());
            request.getRequestDispatcher("/novo-equipamento.jsp").forward(request, response);
            return;
        }
        request.setAttribute("equipamentos", equipamentoDAO.listarTodos());
        request.getRequestDispatcher("/equipamentos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        Equipamento equipamento = new Equipamento();
        equipamento.setNumeroSerie(request.getParameter("numeroSerie"));
        equipamento.setTipo(request.getParameter("tipo"));

        Long laboratorioId = Long.valueOf(request.getParameter("laboratorio_id"));
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            Laboratorio laboratorio = entityManager.find(Laboratorio.class, laboratorioId);
            if (laboratorio == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Laboratório não encontrado");
                return;
            }
            equipamento.setLaboratorio(laboratorio);
        } finally {
            entityManager.close();
        }

        equipamentoDAO.salvar(equipamento);
        response.sendRedirect(request.getContextPath() + "/equipamentos");
    }
}
