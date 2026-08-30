<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Equipamentos | Inventory IT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <main class="container py-5">
        <div class="d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4">
            <div><h1 class="h2 mb-1">Equipamentos</h1><p class="text-secondary mb-0">Consulte onde cada ativo está alocado.</p></div>
            <div class="d-flex gap-2"><a href="laboratorios" class="btn btn-outline-secondary">Laboratórios</a><a href="novo-equipamento" class="btn btn-primary">Novo equipamento</a></div>
        </div>
        <section class="card border-0 shadow-sm"><div class="card-body p-4"><div class="table-responsive">
            <table class="table table-striped align-middle mb-0">
                <thead><tr><th>ID</th><th>Número de série</th><th>Tipo</th><th>Laboratório</th></tr></thead>
                <tbody>
                    <c:forEach var="equipamento" items="${equipamentos}"><tr><td>${equipamento.id}</td><td>${equipamento.numeroSerie}</td><td>${equipamento.tipo}</td><td>${equipamento.laboratorio.nome}</td></tr></c:forEach>
                    <c:if test="${empty equipamentos}"><tr><td colspan="4" class="text-center text-secondary">Nenhum equipamento cadastrado.</td></tr></c:if>
                </tbody>
            </table>
        </div></div></section>
    </main>
</body>
</html>
