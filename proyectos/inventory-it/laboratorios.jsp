<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Laboratórios | Inventory IT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <main class="container py-5">
        <div class="d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4">
            <div><h1 class="h2 mb-1">Laboratórios</h1><p class="text-secondary mb-0">Cadastre e acompanhe os locais da instituição.</p></div>
            <a href="equipamentos" class="btn btn-outline-primary">Ver equipamentos</a>
        </div>
        <section class="card border-0 shadow-sm mb-4"><div class="card-body p-4">
            <h2 class="h5 mb-3">Novo laboratório</h2>
            <form action="laboratorios" method="post" class="row g-3">
                <div class="col-md-7"><label for="nome" class="form-label">Nome</label><input id="nome" name="nome" class="form-control" required maxlength="120"></div>
                <div class="col-md-3"><label for="bloco" class="form-label">Bloco</label><input id="bloco" name="bloco" class="form-control" required maxlength="40"></div>
                <div class="col-md-2 d-flex align-items-end"><button type="submit" class="btn btn-primary w-100">Salvar</button></div>
            </form>
        </div></section>
        <section class="card border-0 shadow-sm"><div class="card-body p-4">
            <h2 class="h5 mb-3">Locais cadastrados</h2>
            <div class="table-responsive"><table class="table table-striped align-middle mb-0">
                <thead><tr><th>ID</th><th>Nome</th><th>Bloco</th></tr></thead>
                <tbody>
                    <c:forEach var="laboratorio" items="${laboratorios}"><tr><td>${laboratorio.id}</td><td>${laboratorio.nome}</td><td>${laboratorio.bloco}</td></tr></c:forEach>
                    <c:if test="${empty laboratorios}"><tr><td colspan="3" class="text-center text-secondary">Nenhum laboratório cadastrado.</td></tr></c:if>
                </tbody>
            </table></div>
        </div></section>
    </main>
</body>
</html>
