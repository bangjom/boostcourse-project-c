package kr.or.connect.reservation.service.security;

import java.util.List;

import kr.or.connect.reservation.dto.User;

public interface UserDbService {
    public UserEntity getUser(String loginUserId);
    public List<UserRoleEntity> getUserRoles(String loginUserId);
    public void addMember(User user, boolean admin);
    public User getMemberByEmail(String email);
}
