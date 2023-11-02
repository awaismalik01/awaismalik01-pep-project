package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account) {

        if (accountDAO.getUser(account) != null || account.getUsername().length() == 0
                || account.getPassword().length() < 4)
            return null;

        return accountDAO.registerUser(account);
    }

    public Account loginAccount(Account account) {

        Account obj = accountDAO.getUser(account);

        if (obj != null && obj.getUsername().equals(account.getUsername())
                && obj.getPassword().equals(account.getPassword())) {
            return obj;
        }
        return null;
    }
}
