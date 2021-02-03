package kr.or.connect.reservation.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.connect.reservation.dao.MemberDao;
import kr.or.connect.reservation.dao.MemberRoleDao;
import kr.or.connect.reservation.dto.Member;
import kr.or.connect.reservation.dto.MemberRole;
import kr.or.connect.reservation.service.security.UserEntity;
import kr.or.connect.reservation.service.security.UserRoleEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    @Override
    public UserEntity getUser(String loginUserId) {
        Member member = memberDao.getMemberByEmail(loginUserId);
        return new UserEntity(member.getEmail(), member.getPassword());
    }

    @Override
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for(MemberRole memberRole : memberRoles) {
            list.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }
        return list;
    }

    @Override
    @Transactional
    public void addMember(Member member, boolean admin) {
        memberDao.addMember(member);

        Member selectedMember = memberDao.getMemberByEmail(member.getEmail());
        Long memberId = selectedMember.getId();
        if(admin) {
            memberRoleDao.addRole(new MemberRole(memberId,Role.ROLE_ADMIN.toString()));
        }
        memberRoleDao.addRole(new MemberRole(memberId,Role.ROLE_USER.toString()));
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }
}
