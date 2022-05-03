package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

/**
 * 勤怠インスタンスに設定されている値のバリデーションを行うクラス
 */
public class AttendanceValidator {

    /**
     * 勤怠インスタンスの各項目についてバリデーションを行う
     * @param rv 勤怠インスタンス
     * @return エラーのリスト
     */



    public static List<String> validate(AttendanceView av) {

        List<String> errors = new ArrayList<String>();


      //出勤のチェック
        String atWorkError = validateAt_Work(av.getAt_Work());

        if (!atWorkError.equals("ATT_ATTENDANCE_AT_WORK")) {
            errors.add(atWorkError);
        }

        return errors;

    }






private static String validateAt_Work(String At_Work) {
    if (At_Work == null || At_Work.equals("")) {
        return MessageConst.E_WORNG.getMessage();
    }

    //入力値がある場合は空文字を返却
    return "";
    }



}


