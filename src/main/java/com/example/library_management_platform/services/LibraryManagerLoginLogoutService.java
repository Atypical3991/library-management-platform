package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import com.example.library_management_platform.models.entities.Sessions;
import com.example.library_management_platform.repositories.LibraryManagerRepository;
import com.example.library_management_platform.repositories.SessionsRepository;
import com.example.library_management_platform.services.interfaces.LoginLogoutInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LibraryManagerLoginLogoutService implements LoginLogoutInterface<LoginRequestModel> {

    @Autowired
    LibraryManagerRepository libraryManagerRepository;

    @Autowired
    SessionsRepository sessionsRepository;

    @Override
    public String login(LoginRequestModel loginRequestModel) {
        try {
            LibraryManager libraryManager = libraryManagerRepository.findTopByUsernameAndPassword(loginRequestModel.getUsername(), loginRequestModel.getPassword());
            if (libraryManager == null) {
                log.error("LibraryManagerLoginLogoutService, login library_manager not found. payload: {}", loginRequestModel);
                return null;
            }
            sessionsRepository.deleteAllByUserIdAndRole(libraryManager.getId(), libraryManager.getRole());
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", libraryManager.getUsername());
            claims.put("role", libraryManager.getRole());
            String token = JwtTokenUtil.generateJwt(claims);
            if (token != null) {
                Sessions sessions = new Sessions();
                sessions.setToken(token);
                sessions.setUserId(libraryManager.getId());
                sessionsRepository.save(sessions);
            }
            return token;
        } catch (Exception e) {
            log.error("LibraryManagerLoginLogoutService, login exception raised!! payload: {}", loginRequestModel, e);
            return null;
        }
    }

    @Override
    public String logout(Long id) {
        return null;
    }
}
