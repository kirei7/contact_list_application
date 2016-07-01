package com.vlad.pet.contactlist.model.dao;

import com.vlad.pet.contactlist.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger("debug");
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void persist(User user) {
        manager.persist(user);
    }

    @Override
    public User remove(User user) {
        if (manager.contains(user)) {
            manager.remove(user);
        } else {
            User attached = find(user.getId());
            manager.remove(attached);
            return attached;
        }
        return user;
    }

    @Override
    public User removeById(Long id) {
        return remove(
                find(id)
        );
    }

    @Override
    public void merge(User user) {
        manager.merge(user);
    }

    @Override
    public User find(Long id) {
        if (id == null) return null;
        return manager.find(User.class, id);
    }

    @Override
    public User find(String name) {
        if (name.isEmpty()) return null;
        List<User> result = manager.createQuery("SELECT c FROM User c WHERE lower(c.nickName) LIKE lower(:name)")
                .setParameter("name", name)
                .getResultList();
        if (result.size() == 0) return null;
        return result.get(0);
    }

    @Override
    public Set<User> getAllUsers() {
        return new HashSet<>(
        manager.createQuery("SELECT c FROM User c")
                .getResultList()
        );
    }
}
