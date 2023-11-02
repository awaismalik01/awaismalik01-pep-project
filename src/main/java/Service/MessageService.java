package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService()
    {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO)
    {
        this.messageDAO = messageDAO;
    }

    public Message postMessage(Message message)
    {   
        
        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() <= 255)
        {
            return messageDAO.postMessage(message);    
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return this.messageDAO.getAllMessages();
    }
}
