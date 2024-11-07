package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public User findById(long id) {
        try{
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.id = :id", User.class);
            return query.setParameter("id", id)
                    .getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(User user) {
        entityManager.persist(user);
    }
    public void update(User user) {
        entityManager.merge(user);
    }
    public void delete(long id) {
        User user = findById(id);
        if(user != null) {
            entityManager.remove(user);
        }
    }
}
