package account.bank.client.Resources;

import account.bank.client.Entities.Account;

import javax.persistence.*;
import java.util.List;

public class AccountResource implements IAccountResource{
    private EntityManager entityManager;

    public AccountResource(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_ACC");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Account account){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            entityManager.persist(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public Account findById(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Account account = null;

        try{
            account = entityManager.find(Account.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> listAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            Query query = entityManager.createQuery("select a from Account a");
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public void update(Account account){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            entityManager.merge(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            Account account = entityManager.find(Account.class, id);
            entityManager.remove(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
