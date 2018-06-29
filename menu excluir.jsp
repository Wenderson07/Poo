<%@ page import="bancoDeDados.*" %>
<title>Menu de Excluir</title>
</head>
<body>
		<form>
            campo:<select name="Campo para Excluir">
			<option value="Associacao" >Associacao</option>
			<option value="Associado">Associado</option>
			<option value="Pagamento">Pagamento</option>
			<option value="Frequencia">Frequencia</option>
			<option value="Taxa" >Taxa</option>
			<option value="Reuniao">Reuniao</option>
		</select>
		<input type="submit">
        </form>
        <br>
	<%
	String remido = request.getParameter("campo");
	switch(remido){
		case "Associacao":
			AssociacaoDAO associacao = new AssociacaoDAO();
			associacao.excluirTudo();
			break;
		case "Associado":
			 AssociadoDAO associado = new AssociadoDAO();
			 associado.excluirTudo();
			break;
		case "Pagamento":
			 PagamentoDAO pagamento = new PagamentoDAO();
			 pagamento.excluirTudo();
			break;
		case "Frequencia": 
			 FrequenciaDAO frequencia = new FrequenciaDAO();
			 frequencia.excluirTudo();
			break;
		case "Taxa": 
			TaxaDAO taxa = new TaxaDAO();
			taxa.excluirTudo();
			break;
		case "Reuniao": 
			ReuniaoDAO reuniao = new ReuniaoDAO();
			reuniao.excluirTudo();
			break;
	}
	%>

</body>
</html>
