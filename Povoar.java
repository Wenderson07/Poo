package telegram;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import associacao.Associacao;
import associacao.Associado;
import associacao.AssociadoRemido;
import associacao.Reuniao;
import excecoes.AssociacaoJaExistente;
import excecoes.AssociacaoNaoExistente;
import excecoes.AssociadoJaExistente;
import excecoes.AssociadoJaRemido;
import excecoes.AssociadoNaoExistente;
import excecoes.FrequenciaIncompativel;
import excecoes.FrequenciaJaRegistrada;
import excecoes.ReuniaoJaExistente;
import excecoes.ReuniaoNaoExistente;
import excecoes.TaxaJaExistente;
import excecoes.TaxaNaoExistente;
import excecoes.ValorInvalido;
import javassist.expr.NewArray;
import wenderson.MinhaAssociacao;
import associacao.Taxa;

public class Povoar {
	
	public void povoador() throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente, ReuniaoJaExistente, AssociadoNaoExistente, ReuniaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel, TaxaJaExistente, AssociadoJaRemido, TaxaNaoExistente {
		MinhaAssociacao povoar = new MinhaAssociacao();
		povoar.limparBanco();
		Associacao a1 = new Associacao(3534, "Turma de Poo");	
		povoar.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1998, 02, 22);
		long nasc = gc.getTimeInMillis();
		gc.set(2018, 02, 27);
		long dataDeIngresso = gc.getTimeInMillis();
		Associado associado1 = new Associado(304631910, "Wenderson", "4002-8922", dataDeIngresso, nasc);
		povoar.adicionar(3534, associado1);
		
		gc.set(1999, 02, 02);
		nasc = gc.getTimeInMillis();
		Associado associado2 = new Associado(556803656, "Alexander", "9855-7145", dataDeIngresso, nasc);
		povoar.adicionar(3534, associado2);
		
		gc.set(1974, 10, 14);
		nasc = gc.getTimeInMillis();
		Associado associado3 = new Associado(42093344, "Pedro", "9877-9999", dataDeIngresso, nasc);
		povoar.adicionar(3534, associado3);

		gc.set(1999, 05, 01);
		nasc = gc.getTimeInMillis();
		Associado associado4 = new Associado(304331910, "Diego", "7175-1423", dataDeIngresso, nasc);
		povoar.adicionar(3534, associado4);
		
		gc.set(1990, 12, 25);
		nasc = gc.getTimeInMillis();
		Associado associado5 = new AssociadoRemido(100631910, "Natanael", "3144-4925", dataDeIngresso, nasc,nasc+1000000);
		povoar.adicionar(3534, associado5);
		
		
		Reuniao reuniao1 = new Reuniao(dataDeIngresso, "Aula de Introdução");
		povoar.adicionar(3534, reuniao1);
		povoar.registrarFrequencia(304631910, 3534, dataDeIngresso);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso);
		
		Reuniao reuniao2 = new Reuniao(dataDeIngresso+100000000, "Conceitos POO");
		povoar.adicionar(3534, reuniao2);
		povoar.registrarFrequencia(304631910, 3534, dataDeIngresso+100000000);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+100000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+100000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+100000000);
		
		Reuniao reuniao3 = new Reuniao(dataDeIngresso+300000000, "Implementações POO");
		povoar.adicionar(3534, reuniao3);
		povoar.registrarFrequencia(304631910, 3534, dataDeIngresso+300000000);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+300000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+300000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+300000000);
		
		Reuniao reuniao4 = new Reuniao(dataDeIngresso+500000000, "Reusabilidade POO");
		povoar.adicionar(3534, reuniao4);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+500000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+500000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+500000000);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso+500000000);
		
		Reuniao reuniao5 = new Reuniao(dataDeIngresso+700000000, "Qualidade do software");
		povoar.adicionar(3534, reuniao5);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+700000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+700000000);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso+700000000);
		
		Reuniao reuniao6 = new Reuniao(dataDeIngresso+900000000, "Teste de unidade ");
		povoar.adicionar(3534, reuniao6);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+900000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+900000000);
		
		Reuniao reuniao7 = new Reuniao(dataDeIngresso+1000000000, "Modularidade");
		povoar.adicionar(3534, reuniao7);
		povoar.registrarFrequencia(304631910, 3534, dataDeIngresso+1000000000);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+1000000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+1000000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+1000000000);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso+1000000000);
		
		Reuniao reuniao8 = new Reuniao(dataDeIngresso+1100000000, "Herança simples");
		povoar.adicionar(3534, reuniao8);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+1100000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+1100000000);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso+1100000000);
		
		Reuniao reuniao9 = new Reuniao(dataDeIngresso+1300000000, "Herança múltipla");
		povoar.adicionar(3534, reuniao9);
		povoar.registrarFrequencia(304631910, 3534, dataDeIngresso+1300000000);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+1300000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+1300000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+1300000000);
		
		Reuniao reuniao10 = new Reuniao(dataDeIngresso+1500000000, "Prova!");
		povoar.adicionar(3534, reuniao10);
		povoar.registrarFrequencia(556803656, 3534, dataDeIngresso+1500000000);
		povoar.registrarFrequencia(42093344, 3534, dataDeIngresso+1500000000);
		povoar.registrarFrequencia(304331910, 3534, dataDeIngresso+1500000000);
		povoar.registrarFrequencia(100631910, 3534, dataDeIngresso+1500000000);
		

		Taxa t1 = new Taxa("Eclipse", 2018, 1200.00, 12, false);
		Taxa t2 = new Taxa("Word", 2017, 600.00, 12, false);
		Taxa t3 = new Taxa("MySql", 2018, 2000.00, 8, false);
		Taxa t4 = new Taxa("Telegram", 2018, 1000.00, 10, true);
		
		povoar.adicionar(3534, t1);
		povoar.adicionar(3534, t2);
		povoar.adicionar(3534, t3);
		povoar.adicionar(3534, t4);
		Date hoje = new Date();
		povoar.registrarPagamento(3534, "Eclipse", 2018, 304631910, hoje.getTime(), 100);
		povoar.registrarPagamento(3534, "Eclipse", 2018, 42093344, hoje.getTime(), 120);
		povoar.registrarPagamento(3534, "Eclipse", 2018, 556803656, hoje.getTime(), 110);
		povoar.registrarPagamento(3534, "Eclipse", 2018, 304331910, hoje.getTime(), 115);
		povoar.registrarPagamento(3534, "Eclipse", 2018, 100631910, hoje.getTime(), 101);
		
		povoar.registrarPagamento(3534, "Word", 2017, 304631910, hoje.getTime(), 80);
		povoar.registrarPagamento(3534, "Word", 2017, 42093344, hoje.getTime(), 100);
		povoar.registrarPagamento(3534, "Word", 2017, 556803656, hoje.getTime(), 400);
		povoar.registrarPagamento(3534, "Word", 2017, 42093344, hoje.getTime(), 100);
		
		povoar.registrarPagamento(3534, "MySql", 2018, 304631910, hoje.getTime(), 220);
		povoar.registrarPagamento(3534, "MySql", 2018, 42093344, hoje.getTime(), 250);
		povoar.registrarPagamento(3534, "MySql", 2018, 556803656, hoje.getTime(), 200);
		povoar.registrarPagamento(3534, "MySql", 2018, 42093344, hoje.getTime(), 300);
		povoar.registrarPagamento(3534, "MySql", 2018, 100631910, hoje.getTime(), 400);
		
		povoar.registrarPagamento(3534, "Telegram", 2018, 556803656, hoje.getTime(), 200);
		povoar.registrarPagamento(3534, "Telegram", 2018, 42093344, hoje.getTime(), 150);
		povoar.registrarPagamento(3534, "Telegram", 2018, 304631910, hoje.getTime(), 120);
		povoar.registrarPagamento(3534, "Telegram", 2018, 304631910, hoje.getTime(), 100);
		povoar.registrarPagamento(3534, "Telegram", 2018, 100631910, hoje.getTime(), 800);
		
		
		
	}
}
