package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 勤怠情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)


public class AttendanceView {

    /**
     * id
     */
    private Integer id;

    /**
     * 勤怠を登録した従業員
     */
    private EmployeeView employee;

    /**
     * いつの勤怠かを示す日付
     */
    private LocalDate attendanceDate;

    /**
     * 出勤
     */
    private LocalDateTime attendance_at_work;


    /**
     * 休憩開始
     */

    private LocalDateTime attendance_break_start;

    /**
     * 休憩終了
     */
    private LocalDateTime attendance_end_of_break;

    /**
     * 退勤
     */
    private LocalDateTime attendance_leaving_work;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;




}
