package hse;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dinahas on 28.10.2017.
 * Telegram chatbot
 */

public class Bot extends TelegramLongPollingBot{

    Struct struct = new Struct(); //user's own calendar
    int num = 0; //flag
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private Date date = new Date();
	private long chat_id = null;

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()){
			String message_text = update.getMessage().getText();
            chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")){
            }
            else try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(format.parse(message_text));
                if(num == 1){
                    PassporOffice office = new PassporOffice();
                    PassporOffice office11 = office.needInfo(message_text);
                    struct.insertInfo(office11);
                }
                else if(num == 2){
                    Doctor doctor  = new Doctor();
                    Doctor doctor1 = doctor.needInfo(message_text);
                    struct.insertInfo(doctor1);
                }
                else if(num == 3){
                    Gym gym  = new Gym();
                    Gym gym1 = gym.needInfo(message_text);
                    struct.insertInfo(gym1);
                }
                else if(num == 4){
                    BedLinen bedLinen  = new BedLinen();
                    BedLinen bedLinen1 = bedLinen.needInfo(message_text);
                    struct.insertInfoInfo(bedLinen1);
                }
                else if(num == 5){
					struct.deleteInfo("Passport Office: section 3, 123 room", message_text);
				}
                else if(num == 6){
                    struct.deleteInfo("Doctor: section 1, 123 room", message_text);
                }
                else if(num == 7){
                    struct.deleteInfo("Gym: section 3, 127 room", message_text);
                }
                else if(num ==8){
                    struct.deleteInfo("Bed linen: section 3, 130 room", message_text);
                }
				sendRpl("Click the button to find any timetable " +
                            "you need or to create your own calendar.",
							"Calendar", "calendar",
							"Today", "today");
                } 
				catch (ParseException e) {
                    e.printStackTrace();
					sendMsg("No such command. " +
                            "Click /start to start using this bot.");
				}
        }
        else if (update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();
            int message_id = update.getCallbackQuery()
                    .getMessage().getMessageId();
            long chat_id = update.getCallbackQuery()
                    .getMessage().getChatId();

            if (call_data.equals("passport_office")){
                PassporOffice info = new PassporOffice();
                Calendar calendar = Calendar.getInstance();
                try {
                    info = info.needInfo(format.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
				SendRpl();
            }
            else if (call_data.equals("doctor")){
                Info info = new Doctor();
                Calendar calendar = Calendar.getInstance();
                try {
                    info = info.needInfo("16.12.2017");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
				SendRpl();
            }
            else if (call_data.equals("gym")){
                Info info = new Gym();
                Calendar calendar = Calendar.getInstance();
                try {
                    info = info.needInfo("16.12.2017");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("bed_linen")){
                Info info = new BedLinen();
                Calendar calendar = Calendar.getInstance();
                try {
                    info = info.needInfo("16.12.2017");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
				SendRpl();
            }
            else if (call_data.equals("today")){
				sendRpl("Select the required work schedule.",
						"Passport Office", "passport_office",
						"Doctor", "doctor",
						"Gym", "gym",
						"Issue of bed linen", "bed_linen",
						"To begin", "begin");

            }
            else if (call_data.equals("calendar")){
				sendRpl("Create your own calendar.",
						"Add", "Add",
						"Delete", "delete",
						"Save", "save",
						"Load", "load",
						"Show all", "show",
						"To begin", "begin");
            }
            else if(call_data.equals("show")){
                String info = struct.showInfo();
				sendRpl("All your tasks:",
						"Calendar", "calendar",
						"Today", "today");
				
                SendMessage message1 = new SendMessage()
                        .setChatId(chat_id)
                        .setText("No tasks");
                if (struct.isEmpty())
                    sendMsg("No tasks");
				else
					sendMsg(info);
            }
            else if (call_data.equals("save")) {
				struct.writeJson("file.json");
				sendMsg("Saved!");
				sendRpl("Click the button to find any timetable you need or to create your own calendar.",
						"Calendar", "calendar",
						"Today", "today");
            }
            else if (call_data.equals("load")) {
                Struct struct1 = new Struct();
                struct1.readJson("file.json");
                String info = struct1.showInfo();
				sendMsg("Loading your file...");
                if (struct1.isEmpty())
						sendMsg("File is empty!");
				else
						sendMsg(info);
				sendRpl("Click the button to find any timetable you need or to create your own calendar.",
						"Calendar", "calendar",
						"Today", "today");
            }
            else if (call_data.equals("add")){
				sendRpl("Select the required work schedule.",
						"Passport Office", "passport_office1",
						"Doctor", "doctor1",
						"Gym", "gym1",
						"Issue of bed linen", "bed_linen1");
            }
            else if (call_data.equals("passport_office1")){
                num = 1;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("doctor1")){
                num = 2;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("gym1")){
                num = 3;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("bed_linen1")){
                num = 4;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("begin")){
				sendRpl("Click the button to find any timetable you need.", 
						"Calendar", "calendar",
						"Today", "today");
            }
            else if (call_data.equals("delete")){
				sendRpl("Select the required work schedule.",
						"Passport Office", "passport_office2",
						"Doctor", "doctor2",
						"Gym", "gym2",
						"Issue of bed linen", "bed_linen2");
            }
            else if (call_data.equals("passport_office2")){
                num = 5;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("doctor2")){
                num = 6;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("gym2")){
                num = 7;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
            else if (call_data.equals("bed_linen2")){
                num = 8;
				sendMsg("Enter the day you need.Use dd.MM.yyyy format!");
            }
        }
    }

	//sending massages
	private SendMessage sendMsg(String text) {
		SendMessage message = new SendMessage()
            .setChatId(chat_id)
            .setText(text);
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	//sending massages with buttons
	private void sendRpl(String msgText, 
						String bttnText1 = null, String bttnID1 = null, 
						String bttnText2 = null, String bttnID2 = null,
						String bttnText3 = null, String bttnID3 = null,
						String bttnText4 = null, String bttnID4 = null,
						String bttnText5 = null, String bttnID5 = null,
						String bttnText6 = null, String bttnID6 = null,
						String bttnText7 = null, String bttnID7 = null,){
	    SendMessage message = SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(msgText);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
		if bttnText1 != null && bttnID1 != null {
			rowInline1.add(new InlineKeyboardButton()
				.setText(bttnText1)
				.setCallbackData(bttnID1));
		}
		if bttnText2 != null && bttnID2 != null {
			rowInline1.add(new InlineKeyboardButton()
				.setText(bttnText2)
				.setCallbackData(bttnID2));
		}
		if rowInline1 != null
			rowsInline.add(rowInline1);
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        if bttnText3 != null && bttnID3 != null {
			rowInline2.add(new InlineKeyboardButton()
				.setText(bttnTex3)
				.setCallbackData(bttnID3));
		}
		if bttnText4 != null && bttnID4 != null {
			rowInline2.add(new InlineKeyboardButton()
				.setText(bttnText4)
				.setCallbackData(bttnID4));
		}
		if rowInline2 != null
			rowsInline.add(rowInline2);
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        if bttnText5 != null && bttnID5 != null {
			rowInline3.add(new InlineKeyboardButton()
				.setText(bttnText5)
				.setCallbackData(bttnID5));
		}
		if bttnText6 != null && bttnID6 != null {
			rowInline3.add(new InlineKeyboardButton()
				.setText(bttnText6)
				.setCallbackData(bttnID6));
		}
		if rowInline3 != null
			rowsInline.add(rowInline3);
		List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        if bttnText7 != null && bttnID7 != null {
			rowInline4.add(new InlineKeyboardButton()
				.setText(bttnText7)
				.setCallbackData(bttnID7));
		}
		if rowInline4 != null
			rowsInline.add(rowInline4);
		if rowInline != null {
			inlineKeyboardMarkup.setKeyboard(rowsInline);
			message.setReplyMarkup(inlineKeyboardMarkup);
		}
        try {
            execute(message);
        } catch (TelegramApiException e) {
        e.printStackTrace();
        }
	}
	
    @Override
    public String getBotUsername() {
        return "*name*";
    }

    @Override
    public String getBotToken() {
        return "*token*";
    }
}