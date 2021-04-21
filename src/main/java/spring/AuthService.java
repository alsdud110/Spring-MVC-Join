package spring;

public class AuthService {

	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public AuthInfo authenticate(String id, String password) {
		Member member = memberDao.selectById(id);
		if (member == null) {
			throw new WrongIdPasswordException();
		}
		if (!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(),
				member.getEmail(),
				member.getName());
	}

}
