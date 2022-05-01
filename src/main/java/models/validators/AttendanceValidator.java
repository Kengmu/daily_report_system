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
        if (!atWorkError.equals("")) {
            errors.add(atWorkError);
        }


      //休憩開始のチェック
        String breakStartError = validateBreak_Start(av.getBreak_Start());
        if (!breakStartError.equals("")) {
            errors.add(breakStartError);
        }


      //休憩終了のチェック
        String endOfBreakError = validateEnd_Of_Break(av.getEnd_Of_Break());
        if (!endOfBreakError.equals("")) {
            errors.add(endOfBreakError);
        }


      //退勤のチェック
        String leavingWorkError = validateLeaving_Work(av.getLeaving_Work());
        if (!leavingWorkError.equals("")) {
            errors.add(leavingWorkError);
        }


        return errors;

     }





private static String validateAt_Work(String At_Work) {
    if (At_Work == null || At_Work.equals("")) {
        return MessageConst.E_NOTITLE.getMessage();
    }

    //入力値がある場合は空文字を返却
    return "";
    }


private static String validateBreak_Start(String Break_Start) {
    if (Break_Start == null || Break_Start.equals("")) {
        return MessageConst.E_NOTITLE.getMessage();
    }

    //入力値がある場合は空文字を返却
    return "";
    }


private static String validateEnd_Of_Break(String End_Of_Break) {
    if (End_Of_Break == null || End_Of_Break.equals("")) {
        return MessageConst.E_NOTITLE.getMessage();
    }

    //入力値がある場合は空文字を返却
    return "";
    }


private static String validateLeaving_Work(String Leaving_Work) {
    if (Leaving_Work == null || Leaving_Work.equals("")) {
        return MessageConst.E_NOTITLE.getMessage();
    }

    //入力値がある場合は空文字を返却
    return "";
    }

}


