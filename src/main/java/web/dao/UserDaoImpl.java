package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Component
public class UserDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

//    @Transactional(readOnly = true)
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

//    @Transactional(readOnly = true)
    public User findById(long id) {
//        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
//        query.setParameter("id", id);
//        return query.getResultList().stream().findFirst().orElse(null);
        //entityManager.
        //return entityManager.find(User.class, id);
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id", User.class);
        User user =  query.setParameter("id", id)
                .getSingleResult();
        System.out.println(user);
        return user;
    }

    public void save(User user) {
        entityManager.persist(user);
    }
    public void update(long id, User user) {
        User userToBeUpdate = findById(id);
        if(userToBeUpdate != null) {
            userToBeUpdate.setFirstName(user.getFirstName());
            userToBeUpdate.setLastName(user.getLastName());
            userToBeUpdate.setEmail(user.getEmail());
            userToBeUpdate.setAge(user.getAge());
            entityManager.merge(userToBeUpdate);
        }
    }
    public void delete(long id) {
        entityManager.remove(findById(id));
    }
}
