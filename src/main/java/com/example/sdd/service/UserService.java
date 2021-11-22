package com.example.sdd.service;

import com.example.sdd.dao.RoleRepository;
import com.example.sdd.dao.UserRepository;
import com.example.sdd.entity.Role;
import com.example.sdd.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final Role roleAdmin;
    final Role roleUser;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            @Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
            @Value("${com.example.sdd.role.admin.id}") String roleAdminIdString,
            @Value("${com.example.sdd.role.user.id}") String roleUserIdString
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleAdmin = roleRepository.getById(Long.decode(roleAdminIdString));
        this.roleUser = roleRepository.getById(Long.decode(roleUserIdString));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(new HashSet<>(Collections.singletonList(this.roleUser)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
