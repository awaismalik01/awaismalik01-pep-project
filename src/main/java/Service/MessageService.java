package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message postMessage(Message message) {

        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() <= 255) {
            return messageDAO.postMessage(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        Message message = this.messageDAO.getMessageById(message_id);
        
        if (message == null)
            return null;

        this.messageDAO.deleteMessageById(message);
        return message;
    }

    public Message updateMessageById(int message_id, String message_text) {
        Message message = this.messageDAO.getMessageById(message_id);
        
        if (message == null)
            return null;

        this.messageDAO.updateMessageById(message_text);
        message = this.messageDAO.getMessageById(message_id);
        return message;
    }

    public List<Message> getAllMessagesByUser(int account_id) {
        return this.messageDAO.getAllMessagesByUser(account_id);
    }
}
