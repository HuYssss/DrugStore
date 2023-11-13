package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.user;
import gr2.clc.drugstore.repository.userRepository;
import gr2.clc.drugstore.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userRepository repo;

    @Override
    public Iterable<user> getAll() {
        return this.repo.findAll();
    }

    @Override
    public String saveOrUpdate(user user) {
        repo.save(user);
        return null;
    }

    @Override
    public String delete(String id) {
        repo.deleteById(id);
        return null;
    }

    @Override
    public Optional<user> findById(String id) {
        return this.repo.findById(id);
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^US\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
