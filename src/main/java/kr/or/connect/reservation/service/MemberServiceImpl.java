package kr.or.connect.reservation.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dao.UserRoleDao;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.service.security.UserEntity;
import kr.or.connect.reservation.service.security.UserRoleEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    @Override
    public UserEntity getUser(String loginUserId) {
        User user = userDao.getMemberByEmail(loginUserId);
        return new UserEntity(user.getEmail(), user.getPassword());
    }

    @Override
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<UserRole> userRoles = userRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for(UserRole userRole : userRoles) {
            list.add(new UserRoleEntity(loginUserId, userRole.getRoleName()));
        }
        return list;
    }

    @Override
    @Transactional
    public void addMember(User user, boolean admin) {
        userDao.addMember(user);

        User selectedUser = userDao.getMemberByEmail(user.getEmail());
        Long userId = selectedUser.getId();
        if(admin) {
            userRoleDao.addRole(new UserRole(userId,Role.ROLE_ADMIN.toString()));
        }
        userRoleDao.addRole(new UserRole(userId,Role.ROLE_USER.toString()));
    }

    @Override
    public User getMemberByEmail(String email) {
        return userDao.getMemberByEmail(email);
    }
}
