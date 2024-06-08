package in.backend.domain.report.entity;


import in.backend.domain.question.entity.Answer;
import in.backend.domain.question.entity.QuestionEntity;
import in.backend.global.entity.BaseEntity;
import in.backend.report.domain.AIFeedback;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private QuestionEntity question;

    @Embedded
    private Answer answer;

    @Embedded
    private AIFeedback aiFeedback;

}
