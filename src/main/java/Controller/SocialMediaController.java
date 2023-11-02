package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterAccountHandler);
        app.post("/login", this::postLoginAccountHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        return app;
    }

    private void postRegisterAccountHandler(Context ctx) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account registerAccount = accountService.registerAccount(account);
        if (registerAccount != null) {
            ctx.json(mapper.writeValueAsString(registerAccount));
        } else {
            ctx.status(400);

        }

    }

    private void postLoginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loginAccount = accountService.loginAccount(account);
        if (loginAccount != null) {
            ctx.json(mapper.writeValueAsString(loginAccount));
        } else {
            ctx.status(401);
        }
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message postedMessage = messageService.postMessage(message);
        if(postedMessage != null)
        {
            ctx.json(mapper.writeValueAsString(postedMessage));
        }
        else
        {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx)
    {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

}