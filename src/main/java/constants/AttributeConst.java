package constants;

import java.time.LocalDateTime;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //日報管理
    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content"),


    //勤怠
    ATTENDANCE("attendance"),
    ATTENDANCES("attendances"),
    ATT_COUNT("attendance_count"),
    ATT_DATE("attendance_date"),
    ATT_ID("id"),
    ATT_ATTENDANCE_AT_WORK("attendance_at_work"),
    ATT_ATTENDANCE_BREAK_START("attendance_break_start"),
    ATT_ATTENDANCE_END_OF_BREAK("attendance_end_of_break"),
    ATT_ATTENDANCE_LEAVING_WORK("attendance_leaving_work");


    private final String text;
    private final Integer i;
    private final LocalDateTime l;



    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
        this.l = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.l = null;
        this.i = i;
    }

    private AttributeConst(final LocalDateTime l) {
        this.l = l;
        this.text = null;
        this.i = null;
    }



    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }
    public LocalDateTime getLocalDateTimeValue() {
        return this.l;
    }


}
