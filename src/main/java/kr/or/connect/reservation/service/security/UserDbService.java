package kr.or.connect.reservation.service.security;

import java.util.List;

import kr.or.connect.reservation.dto.Member;

public interface UserDbService {
    public UserEntity getUser(String loginUserId);
    public List<UserRoleEntity> getUserRoles(String loginUserId);
    public void addMember(Member member, boolean admin);
    public Member getMemberByEmail(String email);
}
