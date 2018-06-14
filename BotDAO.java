package telegram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.telegram.telegrambots.api.objects.Update;

import associacao.Associado;
import bancoDeDados.Conexao;
import excecoes.*;
import bancoDeDados.*;

public class BotDao {

	AssociacaoDAO associacaoDAO = new AssociacaoDAO();
	public double pagamentosAssociado( Long id, Update update) {
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		Bot bote = new Bot();
		double pagamentos = 0;
		try {
			statement = con.prepareStatement("select valor from associacao.pagamento where associado = "+id+"");
			ResultSet rs = statement.executeQuery();
		while(rs.next()) {
				pagamentos += rs.getInt("valor");
				// Faz Estilo calcular Frequencia; Se quiser adiciona mais coisas no Povoar, calcula a taxa e gg
				bote.imprimirMensagem("Taxa:" + rs.getString("taxa") + " Vigencia:" + rs.getInt("vigencia"), update);}
		}catch(Exception e) {
		}
		Conexao.closeConnection(con);
		return pagamentos;
		}
	
	public void calcularFrequencia(int id,String dataInicial,String dataFinal,Update update) throws ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		double numeroReunioes=0;
		double presenca =0;
		String presente;
		Associado aux= buscarAssociado(id);
		Bot bote = new Bot();
		try {
			statement = con.prepareStatement("select data,ata from associacao.reuniao where associacao = "+aux.getAssociacao()+"");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String datasReunioes =String.valueOf(rs.getLong("data"));
				if(datasReunioes.compareTo(dataFinal) <0) {
					presente = presente(id, rs.getLong("data"));
					bote.imprimirMensagem("Reunião: " + rs.getString("ata") + " Data: " + rs.getLong("data") + " Presente: " +  presente ,update);
					if(presente == "sim") {
						presenca++;
					}
					numeroReunioes++;
				}
				}
				bote.imprimirMensagem("A presença total foi de: " + presenca + "/" + numeroReunioes + " = "  + (presenca/numeroReunioes)*100+  "%",update);
			}catch(Exception e) { 
				System.out.println(e.getMessage());
			}
		Conexao.closeConnection(con);
	}
	
	private String presente(int id,long data) {
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		String presente = "não";
		try {
			statement = con.prepareStatement("select data from associacao.frequencia where associado = "+id+"");
			ResultSet rsFrequencia = statement.executeQuery();
			while(rsFrequencia.next()){
				if(rsFrequencia.getLong("data") == data) {
					presente = "sim";
					return presente;
					}else {
						presente = "não";
					}
				}
		}catch (Exception e) {
		}
		return presente;
	}
	public Associado buscarAssociado(int id) throws ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente{
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		Associado aux = null;		
			try {
				statement = con.prepareStatement("select * from associacao.associado where numero like "+id+"");
				ResultSet rs = statement.executeQuery();	
				while(rs.next()) { 
					aux = new Associado(rs.getInt("numero"), rs.getString("nome"), rs.getString("telefone"), rs.getLong("nascimento"),rs.getLong("data"));
					aux.setAssociacao(rs.getInt("associacao"));
					}
			}catch(SQLException e){
				throw new AssociacaoNaoExistente();
			}finally {
				Conexao.closeConnection(con);
			}
			return aux;
	}
}