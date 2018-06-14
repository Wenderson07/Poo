package telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import associacao.*;
import excecoes.AssociacaoNaoExistente;
import excecoes.AssociadoJaExistente;
import excecoes.ValorInvalido;
import wenderson.*;
public class Bot extends TelegramLongPollingBot{
	MinhaAssociacao controle = new MinhaAssociacao();
	BotDao botDao = new BotDao();
	
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public void imprimirMensagem(String texto,Update update) {
		SendMessage send = new SendMessage();
		send.setText(texto);
		send.setChatId(update.getMessage().getChatId());
		try {
			sendMessage(send);
			System.out.println("Mensagem enviada!");
			
		} catch (TelegramApiException e) {
			e.getMessage();
		}

	}
	public void onUpdateReceived(Update update) {
		SendMessage send = new SendMessage();
		String nome = update.getMessage().getFrom().getFirstName();
		send.setChatId(update.getMessage().getChatId());
		System.out.println("Nome:" + update.getMessage().getFrom().getFirstName()+ " ID: "+ update.getMessage().getChatId() + " " + update.getMessage().getText());
		String mensagem = update.getMessage().getText();		
		String dataInicial = "1517523787151";
		String dataFinal =   "9999999999999";
		try {
				botDao.calcularFrequencia(304631910,dataInicial,dataFinal,update);
		} catch (ValorInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (AssociacaoNaoExistente e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AssociadoJaExistente e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
	
	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "528994101:AAE656HO54G_kcA_WSPzkTNnZrQPXLpF0Xo";
	}

}
