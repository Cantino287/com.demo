//package com.demo.servicelmpl;
//
//import com.demo.POJO.Account;
//import com.demo.POJO.Shop;
//import com.demo.dao.AccountDao;
//import com.demo.dao.ShopDao;
//import com.demo.service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AccountServiceImpl implements AccountService {
//    @Autowired
//    private AccountDao accountDao;
//
//    @Autowired
//    private ShopDao shopDao;
//
//    @Override
//    public Account createAccount(Account account) {
//        if (account.getShop() != null && account.getShop().getId() != null) {
//            Optional<Shop> shopOptional = shopDao.findById(account.getShop().getId());
//            if (shopOptional.isPresent()) {
//                account.setShop(shopOptional.get());
//            } else {
//                throw new RuntimeException("Shop with ID " + account.getShop().getId() + " not found");
//            }
//        } else {
//            throw new RuntimeException("Shop ID is required for account creation");
//        }
//
//        return accountDao.save(account);
//    }
//
//
//    public Account findByEmail(String email) {
//        System.out.println("Looking for account with email: " + email);
//        return accountDao.findByEmail(email);
//    }
//
//
//    @Override
//    public Account updateAccount(Long id, Account updatedAccount) {
//        Account account = accountDao.findById(id)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//        account.setEmail(updatedAccount.getEmail());
//        account.setPassword(updatedAccount.getPassword());
//        account.setRole(updatedAccount.getRole());
//        return accountDao.save(account);
//    }
//
//    @Override
//    public void deleteAccount(Long id) {
//        accountDao.deleteById(id);
//    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        return accountDao.findAll();
//    }
//
//    @Override
//    public Account getAccountById(Long id) {
//        return accountDao.findById(id)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//    }
//}
//

package com.demo.servicelmpl;

import com.demo.POJO.Account;
import com.demo.POJO.Shop;
import com.demo.dao.AccountDao;
import com.demo.dao.ShopDao;
import com.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ShopDao shopDao;

    @Override
    public Account createAccount(Account account) {
        if ("employee".equalsIgnoreCase(account.getRole())) {
            // For employees, a valid shop must be linked
            if (account.getShop() != null && account.getShop().getId() != null) {
                Optional<Shop> shopOptional = shopDao.findById(account.getShop().getId());
                if (shopOptional.isPresent()) {
                    account.setShop(shopOptional.get());
                } else {
                    throw new RuntimeException("Shop with ID " + account.getShop().getId() + " not found");
                }
            } else {
                throw new RuntimeException("Shop ID is required for employee account creation");
            }
        } else if ("admin".equalsIgnoreCase(account.getRole())) {
            // For admins, shop must be null
            account.setShop(null);
        } else {
            throw new RuntimeException("Invalid role: " + account.getRole());
        }

        return accountDao.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        System.out.println("Looking for account with email: " + email);
        return accountDao.findByEmail(email);
    }

    @Override
    public Account updateAccount(Long id, Account updatedAccount) {
        Account account = accountDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setEmail(updatedAccount.getEmail());
        account.setPassword(updatedAccount.getPassword());
        account.setRole(updatedAccount.getRole());

        // Handle shop update logic based on role
        if ("employee".equalsIgnoreCase(updatedAccount.getRole())) {
            if (updatedAccount.getShop() != null && updatedAccount.getShop().getId() != null) {
                Shop shop = shopDao.findById(updatedAccount.getShop().getId())
                        .orElseThrow(() -> new RuntimeException("Shop not found"));
                account.setShop(shop);
            } else {
                throw new RuntimeException("Shop ID is required for employee account");
            }
        } else if ("admin".equalsIgnoreCase(updatedAccount.getRole())) {
            account.setShop(null); // Admin should not be linked to a shop
        }

        return accountDao.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}

