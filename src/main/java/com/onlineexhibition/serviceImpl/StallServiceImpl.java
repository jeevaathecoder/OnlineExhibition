package com.onlineexhibition.serviceImpl;

import com.onlineexhibition.constants.OnlineExhibitionConstant;
import com.onlineexhibition.model.Stall;
import com.onlineexhibition.model.User;
import com.onlineexhibition.model.UserRole;
import com.onlineexhibition.repository.StallRepository;
import com.onlineexhibition.response.StallResponse;
import com.onlineexhibition.services.IUserService;
import com.onlineexhibition.services.StallService;
import com.onlineexhibition.services.UserRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StallServiceImpl implements StallService {

    @Autowired
    private StallRepository stallRepository;


    private OnlineExhibitionConstant onlineExhibitionConstant;


    @Autowired
    private UserRoleService userRoleServcie;

    @Autowired
    private IUserService userService;

    @Override
    public StallResponse saveStall(@Valid Stall stall) {
        UserRole roleExists = null;
        Stall isStallExisted = stallRepository.findByRoleId(stall.getUser().getId());
        User user = userService.findByUserId(stall.getUser().getId());
        if (user != null) {
            roleExists = userRoleServcie.findRoleById(user.getUser_type_id());
            if (roleExists == null) {
                return StallResponse.builder().responseMessage("ROLE DOESN'T EXISTS").build();
            } else if (roleExists != null && roleExists.getUser_role().equals(onlineExhibitionConstant.USER)) {
                return StallResponse.builder().responseMessage("INVALID IS EXHIBITOIR ID").build();
            } else if (roleExists != null && user.getStatus().equals(onlineExhibitionConstant.UNAUTHORIZED_USER)) {
                return StallResponse.builder().responseMessage("PLEASE GET APPRVOAL FROM ADMIN TO CREATE STALL").build();
            } else if (isStallExisted != null) {
                return StallResponse.builder().responseMessage("EXHIBITOR HAS STALL ALREADY").build();
            } else {
                Stall save = stallRepository.save(stall);
                return StallResponse.builder().responseMessage("SUCCESSFULLY STALL CREATED").stall(stall).build();
            }
        }
        return StallResponse.builder().responseMessage("EXHIBITOR DOESN'T EXIST").build();
    }

    @Override
    public List<Stall> fetchStallList() {
        return stallRepository.findAll();
    }

    @Override
    public Stall fetchStallById(Long stallId) {
        return stallRepository.findById(stallId).get();
    }

    @Override
    public void deleteStallById(Long stallId) {
        stallRepository.deleteById(stallId);
    }

    @Override
    public StallResponse updateStall(Long stallId, Stall updatedStall) {
                Optional<Stall> optionalStall = stallRepository.findById(stallId);

                if (optionalStall.isPresent()) {
                    Stall existingStall = optionalStall.get();

                    existingStall.setStallName(updatedStall.getStallName());
                    existingStall.setStallDescription(updatedStall.getStallDescription());
                    existingStall.setPhotoUrl(updatedStall.getPhotoUrl());
                    existingStall.setVideoUrl(updatedStall.getVideoUrl());
                    existingStall.setBrochureUrl(updatedStall.getBrochureUrl());
                    existingStall.setUser(updatedStall.getUser());

                    stallRepository.save(existingStall);

                    return new StallResponse("Stall updated successfully", true);
                } else {
                    return new StallResponse("Stall not found", false);
                }
            }

    @Override
    public Stall findByRoleId(Long userId) {
        System.out.println("userId"+userId);
        return stallRepository.findByRoleId(userId);

    }

}


