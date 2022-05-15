package models.validators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

/**
 * 勤怠インスタンスに設定されている値のバリデーションを行うクラス
 */
public class AttendanceValidator {


    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param break_start
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(AttendanceView av, LocalDateTime at_work) {

        List<String> errors = new ArrayList<String>();

        //休憩開始のチェック
        LocalDateTime attendance_break_start = validateAttendance_break_start(av.getAttendance_at_work());
        if (attendance_break_start != null) {
            ;
        }

        return errors;
    }



    public static List<String> validate1(AttendanceView av1, LocalDateTime break_start, LocalDateTime leaving_work) {

        List<String> errors = new ArrayList<String>();

        //休憩終了のチェック
        LocalDateTime attendance_end_of_break = validateAttendance_end_of_break(av1.getAttendance_break_start(),av1.getAttendance_leaving_work());
        if (attendance_end_of_break != null) {
            ;
        }


        return errors;
    }



    public static List<String> validate2(AttendanceView av2, LocalDateTime at_work, LocalDateTime break_start, LocalDateTime end_of_break) {

        List<String> errors = new ArrayList<String>();

        //退勤のチェック
        LocalDateTime attendance_leaving_work = validateAttendance_leaving_work(av2.getAttendance_at_work(),av2.getAttendance_break_start(),av2.getAttendance_end_of_break());
        if (attendance_leaving_work != null) {
            errors(attendance_leaving_work);
        }

        return errors;
    }







    /**
     * 出勤に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param attendance_break_start
     * @param attendance_end_of_break
     * @param attendance_leaving_work
     * @param title タイトル
     * @return エラーメッセージ
     */

    private static LocalDateTime validateAttendance_break_start(LocalDateTime at_work) {

        if (at_work == null) {
            return MessageConst.E_WORNG.getLocalDateTimeValue();
        }

        //入力値がある場合はnullを返却
        return null;
    }



    private static LocalDateTime validateAttendance_end_of_break(LocalDateTime attendance_break_start, LocalDateTime attendance_leaving_work) {

        if (attendance_break_start == null || attendance_break_start == null && attendance_leaving_work != null) {
            return MessageConst.E_WORNG.getLocalDateTimeValue();
        }

        //入力値がある場合はnullを返却
        return null ;
    }



    private static LocalDateTime validateAttendance_leaving_work(LocalDateTime attendance_at_work, LocalDateTime attendance_break_start, LocalDateTime attendance_end_of_break) {

        if (attendance_at_work == null && attendance_break_start == null && attendance_end_of_break == null || attendance_at_work == null) {
            return MessageConst.E_WORNG.getLocalDateTimeValue();
        }

        //入力値がある場合はnullを返却
        return null ;
    }








    private static void errors(LocalDateTime attendance_break_start) {
        // TODO 自動生成されたメソッド・スタブ

    }




}
