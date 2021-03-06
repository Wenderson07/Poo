package telegram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.telegram.telegrambots.api.objects.Update;

import associacao.Associado;
import associacao.Pagamento;
import associacao.Taxa;
import bancoDeDados.Conexao;
import excecoes.*;
import bancoDeDados.*;

public class BotDao {


	AssociacaoDAO associacaoDAO = new AssociacaoDAO();
	

	public void consultarPagamentos(Long id, String v, Update update) {
		Connection c = Conexao.getConnection();
		PreparedStatement stmt = null;
		Bot b = new Bot();
		
		int ano = 0;
		if(v.equals("2018"))
			ano = 2018;
		else if (v.equals("2017"))
			ano = 2017;
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2018, 01, 01);
		long ano18 = gc.getTimeInMillis();
		gc.set(2017, 01, 01);
		long ano17 = gc.getTimeInMillis();
		ArrayList<Taxa> taxas = new ArrayList<Taxa>();
		ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
		
		try {
			stmt = c.prepareStatement("select * from associacao.taxa where vigencia like " + ano + ";");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Taxa t = new Taxa(rs.getString("nome"), rs.getInt("vigencia"), rs.getDouble("valor"), rs.getInt("parcelas"),
						rs.getBoolean("administrativa"));
				t.setAssociacao(rs.getInt("associacao"));
				taxas.add(t);
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		 try {
			stmt = c.prepareStatement("select * from associacao.pagamento where associado like " + id + ";");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("vigencia") == ano) {
					Pagamento p = new Pagamento(rs.getInt("associado"), rs.getDouble("valor"),rs.getLong("data"), 
							rs.getString("nome"), rs.getInt("vigencia"));
					p.setAssociacao(rs.getInt("associacao"));
					pagamentos.add(p);
				}
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	Conexao.closeConnection(c);
        }
		boolean achou = false;
		for (Taxa t : taxas) {
			b.imprimirMensagem("Taxa: " + t.getNome() + " - Vigência: " + t.getVigencia(), update);
			for(Pagamento p : pagamentos) {
				if(p.getTaxa().equals(t.getNome())) {
					b.imprimirMensagem("Pagamento Realizado no Valor: " + p.getValorPago() + " || Data: " + p.getDataPagamento(), update);
					achou = true;
					break;
				}
			}
			if(achou) {
				achou = false;
			} else {
				b.imprimirMensagem("Pagamento não Realizado da Taxa: " + t.getNome(), update);
			}
		}
	}
	
	public long dataConvertida(String data) {
		String x = ("02/04/1997");
		String[] partes = x.split("/");
		int dia =Integer.parseInt(partes[0]);
		int mes =Integer.parseInt(partes[1]);
		int ano = Integer.parseInt(partes[2]);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(ano, mes, dia);
		long dataConvertida = gc.getTimeInMillis();
		return dataConvertida;
	}
	
public long primeiraReuniao(int associado)  {
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		Associado aux = null;
		try {
			aux = buscarAssociado(associado);
		} catch (Exception e) {
			e.printStackTrace();
			}
		long data = 0;
		try {
				statement = con.prepareStatement("select data from associacao.reuniao where associacao =" +aux.getAssociacao()+"");
				ResultSet rs = statement.executeQuery();
				rs.next();
				data= rs.getLong("data");
			}catch (Exception e) {
				
			}
		return data;
	}
	
public long ultimaReuinao(int associado)  {
	Connection con = Conexao.getConnection();
	PreparedStatement statement = null;
	Associado aux = null;
	try {
		aux = buscarAssociado(associado);
	} catch (Exception e) {
		e.printStackTrace();
		}
	long data = 0;
	try {
			statement = con.prepareStatement("select data from associacao.reuniao where associacao =" +aux.getAssociacao()+"");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			data= rs.getLong("data");}
		}catch (Exception e) {	
		}
	return data;
}
	public void calcularFrequencia(int id,String dataInicial,String dataFinal,Update update) throws ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		Connection con = Conexao.getConnection();
		PreparedStatement statement = null;
		double numeroReunioes=0;
		double presenca =0;
		String presente;
		Associado aux= buscarAssociado(id);
		Bot bote = new Bot();
		long dataInicialConvertida = dataConvertida(dataInicial);
		long dataFinalConvertida = dataConvertida(dataFinal);
		try {
			statement = con.prepareStatement("select data,ata from associacao.reuniao where associacao = "+aux.getAssociacao()+"");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				long data =rs.getLong("data");
				if(dataInicialConvertida < data && dataFinalConvertida > data) {
					presente = presente(id, data);
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
