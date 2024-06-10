package in.backend.core.report.entity;

public enum ReportState {
    PROGRESS, // 생성 즉시 초기화 된 값
    GIVE_UP, // 포기 또는 오랜 시간 동안 진행하지 않은 경우
    COMPLETE // 완료
}
