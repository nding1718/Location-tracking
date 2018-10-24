package demo.service.impl;

import demo.domain.User;
import demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by colt on 12/3/17.
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAvaliableUsers() {
        return null;
    }
}
