package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	private MemberDao memberDao;

	@Transactional
	public void changePassword(String id, String oldPwd, String newPwd) {
		Member member = memberDao.selectById(id);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);
//
//		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
