package com.demo.supportportal.service.impl;

import com.demo.supportportal.domain.User;
import com.demo.supportportal.domain.UserPrincipal;
import com.demo.supportportal.repository.UserRepository;
import com.demo.supportportal.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@AllArgsConstructor
@Service
@Transactional
@Qualifier("UserDetailService")
public class UserServiceImpl implements UserService, UserDetailsService {

    // private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            // LOGGER.info("User not found with that name : " + username);
            throw new UsernameNotFoundException("User not found with that name : " + username);
        }else{
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            // LOGGER.info("Returning founded user : " + username);
            return userPrincipal;
        }
    }
}
