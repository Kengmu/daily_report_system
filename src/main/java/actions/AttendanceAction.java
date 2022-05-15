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


            if ((errors.size() > 0)) {

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_REP_EDIT);

            } else {

              //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }



        System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");
    }





    public void breakStart() throws ServletException, IOException {



        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        av.setAttendance_break_start(toLocalDateTime(getRequestParam(AttributeConst.ATT_ATTENDANCE_BREAK_START)));

        LocalDateTime at_work = LocalDateTime.now();


        System.out.println("########## _break_start0: " + av.getAttendance_break_start());
        System.out.println("########## _IDはははは: " + av.getId());


        List<String> errors = service.update(av, at_work);


        if ((errors.size() > 0)) {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ATTENDANCE, av); //入力された日報情報
            putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

            //編集画面を再表示
            forward(ForwardConst.FW_REP_EDIT);

        } else {


      //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }

    System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");



    }








    public void endOfBreak() throws ServletException, IOException {


      //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        AttendanceView av1 = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        av1.setAttendance_end_of_break(toLocalDateTime(getRequestParam(AttributeConst.ATT_ATTENDANCE_END_OF_BREAK)));


        LocalDateTime leaving_work = LocalDateTime.now();
        LocalDateTime break_start = LocalDateTime.now();


        System.out.println("########## _break_start0: " + av1.getAttendance_break_start());
        System.out.println("########## _IDはははは: " + av1.getId());


        List<String> errors = service.update1(av1, break_start, leaving_work);


        if ((errors.size() > 0)) {


            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ATTENDANCE, av1); //入力された日報情報
            putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

            //編集画面を再表示
            forward(ForwardConst.FW_REP_EDIT);

        } else {


      //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }


    System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");

    }




    public void leavingWork() throws ServletException, IOException {



      //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        AttendanceView av2 = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        av2.setAttendance_leaving_work(toLocalDateTime(getRequestParam(AttributeConst.ATT_ATTENDANCE_LEAVING_WORK)));


        LocalDateTime at_work = LocalDateTime.now();
        LocalDateTime break_start = LocalDateTime.now();
        LocalDateTime end_of_break = LocalDateTime.now();




        System.out.println("########## _break_start0: " + av2.getAttendance_break_start());
        System.out.println("########## _IDはははは: " + av2.getId());
        System.out.println("########## atWork: " + av2.getAttendance_at_work());


        List<String> errors = service.update2(av2, at_work, break_start, end_of_break);


        if ((errors.size() > 0)) {



            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ATTENDANCE, av2); //入力された日報情報
            putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

            //編集画面を再表示
            forward(ForwardConst.FW_REP_EDIT);

        } else {


      //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }




    System.out.println("＊＊＊＊＊＊＊＊＊＊＝＝＝＝＝");

    }




    private LocalDateTime toLocalDateTime(String requestParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }





    public void edit() throws ServletException, IOException {



            //idを条件に従業員データを取得する
            AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

            System.out.println("########## edit_IDは: " + av.getId());


          //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            if (av == null || ev.getId() != av.getEmployee().getId()) {

              //該当の勤怠データが存在しない、またはログインしている従業員が勤怠の作成者でない場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
            } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.EMPLOYEE, av); //取得した従業員情報


            //勤怠情報の空インスタンスに、日報の日付＝今日の日付を設定する
            av.setAttendanceDate(LocalDate.now());

            System.out.println("##########?????????！！！！！ : " + av.getAttendanceDate());
            putRequestScope(AttributeConst.ATTENDANCE, av); //日付のみ設定済みの日報インスタンス



            //編集画面を表示する
            forward(ForwardConst.FW_ATT_EDIT);

            }

    }






    public void show() throws ServletException, IOException {

        //idを条件に日報データを取得する
        AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        System.out.println("########## show_IDは: " + av.getId());


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