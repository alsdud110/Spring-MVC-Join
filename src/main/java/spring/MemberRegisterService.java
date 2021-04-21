package spring;

import java.time.LocalDateTime;

import controller.RegisterCommand;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public String regist(RegisterCommand req) {
		Member member = memberDao.selectById(req.getId());
		if (member != null) {
			throw new DuplicateMemberException("dup id " + req.getId());
		}
		Member newMember = new Member(req.getId(),req.getPw(), req.getName(), req.getTel(), req.getBirth(),
				req.getAddr(), req.getEmail(),
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}

