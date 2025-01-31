package in.backend.core.auth.domain;

import in.backend.core.auth.domain.attributes.Authority;

public record Visitor(Long memberId, Authority authority) {

    private static final Long DEFAULT_ADMIN_ID = 1L;

    public static Visitor guest() {
        return new Visitor(0L, Authority.GUEST);
    }

    public static Visitor member(Long memberId) {
        return new Visitor(memberId, Authority.MEMBER);
    }

    public boolean isMember() {
        return authority == Authority.MEMBER;
    }

    public boolean isAdmin() {
        return memberId == DEFAULT_ADMIN_ID;
    }

    public Admin toAdmin() {
        return new Admin(memberId);
    }
}
