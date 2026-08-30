# Guia para Instalar Git e Fazer Commit do Projeto

## 1. Instalar Git

**Windows**: Baixe em https://git-scm.com/download/win e instale normalmente

## 2. Configurar Git (primeira vez)

Abra o Command Prompt e execute:

```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu-email@example.com"
```

## 3. Inicializar repositório e fazer commit

No diretório do projeto:

```bash
cd C:\Users\Pichau\Desktop\proyectos\inventory-it

git init

git add .

git commit -m "Initial commit: Inventory IT sistema completo com DAOs, Servlets e JSP Bootstrap"
```

## 4. (Opcional) Enviar para GitHub

Se quiser salvar no GitHub:

```bash
# Crie um repositório em https://github.com/new
# Depois execute:

git remote add origin https://github.com/seu-usuario/inventory-it.git
git branch -M main
git push -u origin main
```

---

**Após instalar Git, volte e execute os comandos acima.**
