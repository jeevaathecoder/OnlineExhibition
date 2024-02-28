package com.onlineexhibition.serviceImpl;
import com.onlineexhibition.constants.OnlineExhibitionConstant;
import com.onlineexhibition.repository.*;
import com.onlineexhibition.model.User;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.request.UserRequest;
import com.onlineexhibition.services.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
private UserRoleRepository userRoleRepository;


    public  OnlineExhibitionConstant onlineExhibitionConstant;


    @Override
    public User createUser(SignupRequest request) {
        Boolean existsByEmail = userRepository.existsByEmail(request.getEmail());
        if(!existsByEmail) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setMobile(request.getMobile());
            user.setUser_type_id(request.getUser_type_id());
            user.setPassword(request.getPassword());
            user.setStatus(onlineExhibitionConstant.UNAUTHORIZED_USER);
            User create_user = userRepository.save(user);
            return create_user;
        }
        return null;
    }

    @Override
    public String authorize(@Valid UserRequest request) {
        Optional<User> adminexisted = userRepository.findById(request.getAdmin_user_id());
        if(adminexisted.isPresent()&& adminexisted.get()!=null) {
            String role = userRoleRepository.getRoleById(adminexisted.get().getUser_type_id());
            Optional<User> isEXHIBITOR = userRepository.findById(request.getUserId());
            User validUser = isEXHIBITOR.get();
            String roleofUser = userRoleRepository.getRoleById(validUser.getUser_type_id());
            if(role==null) {
                return "ADMIN ID ENTERED WAS INCORRECT";
            }
            else if(role.equals(onlineExhibitionConstant.EXHIBITOR)||role.equals(onlineExhibitionConstant.USER)) {
                return "ENTER ID IS USER OR EXHIBITOR";
            }
            else if(isEXHIBITOR==null) {
                return onlineExhibitionConstant.INVALID_USER;
            }
            else if(roleofUser.equals(onlineExhibitionConstant.USER)) {
                return onlineExhibitionConstant.IS_USER;
            }
            else {
                validUser.setStatus(onlineExhibitionConstant.AUTHORIZED_USER);
                userRepository.save(validUser);
                return onlineExhibitionConstant. AUTHORIZED_USER;
            }

        }
        return onlineExhibitionConstant.ADMIN_DOESNT_EXISTED;
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id).get();
    }


    public List<User> getBendingExhibitors() {
        return userRepository.getBendingExhibitors(OnlineExhibitionConstant.UNAUTHORIZED_USER,2l);
    }

    public List<User> getApprovedExhibitors() {
        return userRepository.getApprovedExhibitors(OnlineExhibitionConstant.AUTHORIZED_USER,2l);
    }
}



