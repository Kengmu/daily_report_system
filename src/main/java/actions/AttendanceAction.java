package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AttendanceView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.AttendanceService;

/**
 * 日報に関する処理を行うActionクラス
 *
 */
public class AttendanceAction extends ActionBase {

    private AttendanceService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new AttendanceService();
        //メソッドを実行
        invoke();
        service.close();
    }






    public void atWork() throws ServletException, IOException {

        //CSRF対策 tokenのチェック


            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            LocalDate day = LocalDate.now();
            LocalDateTime at_work = LocalDateTime.now();


            //パラメータの値をもとに勤怠情報のインスタンスを作成する
            AttendanceView av = new AttendanceView(
                    null,
                    ev, //ログインしている従業員を、勤怠作成者として登録する
                    day,
                    at_work,
                    null,
                    null,
                    null,
                    null,
                    null);

            System.out.println("########## atWork: " + av.getAttendance_at_work());


            List<String> errors = service.create(av);


            if (errors != null) {

          //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }

        System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");
    }





    public void breakStart() throws ServletException, IOException {

        //CSRF対策 tokenのチェック




        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        LocalDate day = LocalDate.now();
        LocalDateTime break_start = LocalDateTime.now();



        //パラメータの値をもとに勤怠情報のインスタンスを作成する
        AttendanceView av = new AttendanceView(
                toNumber(getSessionScope(AttributeConst.ATT_ID)),
                ev, //ログインしている従業員を、勤怠作成者として登録する
                day,
                null,
                break_start,
                null,
                null,
                null,
                null);

        System.out.println("########## atWork: " + av.getAttendance_at_work());


        List<String> errors = service.update(av);


        if (errors != null) {
      //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }

    System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");


    }















    public void endOfBreak() throws ServletException, IOException {

    }


    public void leavingWork() throws ServletException, IOException {

    }





    public void edit() throws ServletException, IOException {



            //idを条件に従業員データを取得する
            AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

          //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            if (av == null || ev.getId() != av.getEmployee().getId()) {

              //該当の勤怠データが存在しない、またはログインしている従業員が勤怠の作成者でない場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
            } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.EMPLOYEE, av); //取得した従業員情報

            //編集画面を表示する
            forward(ForwardConst.FW_ATT_EDIT);

            }

    }






    public void show() throws ServletException, IOException {

        //idを条件に日報データを取得する
        AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if (av == null || ev.getId() != av.getEmployee().getId()) {
            //該当の日報データが存在しない、または
            //ログインしている従業員が日報の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ATTENDANCE, av); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_ATT_SHOW);
        }

    }



    public void entryNew() throws ServletException, IOException {


        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //勤怠情報の空インスタンスに、日報の日付＝今日の日付を設定する
        AttendanceView av = new AttendanceView();
        av.setAttendanceDate(LocalDate.now());

        System.out.println("##########?????????！！！！！ : " + av.getAttendanceDate());
        putRequestScope(AttributeConst.ATTENDANCE, av); //日付のみ設定済みの日報インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_ATT_NEW);

    }






    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */

    public void index() throws ServletException, IOException {



        //セッションからログイン中の従業員情報を取得
        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //ログイン中の従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得する
        int page = getPage();
        List<AttendanceView> attendances = service.getMinePerPage(loginEmployee, page);

        //ログイン中の従業員が作成した日報データの件数を取得
        long myAttendancesCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.ATTENDANCES, attendances); //取得した日報データ
        putRequestScope(AttributeConst.ATT_COUNT, myAttendancesCount); //ログイン中の従業員が作成した日報の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数



        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }



        //一覧画面を表示
        forward(ForwardConst.FW_ATT_INDEX);

    }


}