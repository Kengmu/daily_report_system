package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceConverter;
import actions.views.AttendanceView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Attendance;





public class AttendanceService extends ServiceBase {





    /**
     * 指定した従業員が作成した勤怠データを、指定されたページ数の一覧画面に表示する分取得しAttendanceViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<AttendanceView> getMinePerPage(EmployeeView employee, int page) {

        List<Attendance> attendances = em.createNamedQuery(JpaConst.Q_ATT_GET_ALL_MINE, Attendance.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return AttendanceConverter.toViewList(attendances);
    }





    /**
     * 指定した従業員が作成した勤怠データの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_ATT_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }




    /**
     * 指定されたページ数の一覧画面に表示する勤怠データを取得し、AttendanceViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<AttendanceView> getAllPerPage(int page) {

        List<Attendance> attendances = em.createNamedQuery(JpaConst.Q_ATT_GET_ALL, Attendance.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return AttendanceConverter.toViewList(attendances);
    }




    /**
     * 勤怠テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long attendances_count = (long) em.createNamedQuery(JpaConst.Q_ATT_COUNT, Long.class)
                .getSingleResult();
        return attendances_count;
    }



    /**
     * idを条件に取得したデータをAttendanceViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public AttendanceView findOne(int id) {
        return AttendanceConverter.toView(findOneInternal(id));
    }















    public List<String> create(AttendanceView av) {


        List<String> errors = new ArrayList<String>();

            LocalDateTime ldt = LocalDateTime.now();
            av.setCreatedAt(ldt);
            av.setUpdatedAt(ldt);
            createInternal(av);



        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }










    public List<String> update(AttendanceView av) {


        List<String> errors = new ArrayList<String>();

            System.out.println("########## _break_start1: " + av.getAttendance_break_start());


            LocalDateTime ldt = LocalDateTime.now();

            av.setUpdatedAt(ldt);
            av.setAttendance_break_start(ldt);

            System.out.println("########## _break_start2: " + av.getAttendance_break_start());


            updateInternal(av);



        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }



    public List<String> update1(AttendanceView av1) {


        List<String> errors = new ArrayList<String>();

            System.out.println("########## _break_start1: " + av1.getAttendance_break_start());


            LocalDateTime ldt = LocalDateTime.now();

            av1.setUpdatedAt(ldt);
            av1.setAttendance_end_of_break(ldt);

            System.out.println("########## _break_start2: " + av1.getAttendance_break_start());


            updateInternal1(av1);



        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }




    public List<String> update2(AttendanceView av2) {


        List<String> errors = new ArrayList<String>();

            System.out.println("########## _break_start1: " + av2.getAttendance_break_start());

            LocalDateTime ldt = LocalDateTime.now();

            av2.setUpdatedAt(ldt);
            av2.setAttendance_leaving_work(ldt);

            System.out.println("########## _break_start2: " + av2.getAttendance_break_start());


            updateInternal2(av2);



        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }











    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Attendance findOneInternal(int id) {
        return em.find(Attendance.class, id);
    }




    /**
     * 勤怠データを1件登録する
     * @param av 勤怠データ
     */
    private void createInternal(AttendanceView av) {


        em.getTransaction().begin();
        System.out.println("########## model変換後atWork: " + AttendanceConverter.toModel(av).getAttendance_at_work());
        System.out.println("########## atWork: " + av.getAttendance_at_work());
        em.persist(AttendanceConverter.toModel(av));
        System.out.println("＊＊＊＊＊＊＊＊＊＊勤怠データを1件登録");
        em.getTransaction().commit();

    }











    /**
     * 勤怠データを更新する
     * @param av 勤怠データ
     * @param break_start
     */
    private void updateInternal(AttendanceView av) {

        em.getTransaction().begin();
        System.out.println("########## _break_start2.5: " + av.getAttendance_break_start());


        System.out.println("########## _IDはは: " + av.getId());
        Attendance a = findOneInternal(av.getId());

        System.out.println("########## _IDは: " + av.getId());
        System.out.println("########## _break_start3: " + av.getAttendance_break_start());


        AttendanceConverter.copyViewToModel(a, av);
        System.out.println("########## _break_start3.5: " + av.getAttendance_break_start());


        em.getTransaction().commit();

    }


    private void updateInternal1(AttendanceView av1) {

        em.getTransaction().begin();
        System.out.println("########## _break_start2.5: " + av1.getAttendance_break_start());


        System.out.println("########## _IDはは: " + av1.getId());
        Attendance a = findOneInternal(av1.getId());

        System.out.println("########## _IDは: " + av1.getId());
        System.out.println("########## _break_start3: " + av1.getAttendance_break_start());


        AttendanceConverter.copyViewToModel1(a, av1);
        System.out.println("########## _break_start3.5: " + av1.getAttendance_break_start());


        em.getTransaction().commit();

    }


    private void updateInternal2(AttendanceView av2) {

        em.getTransaction().begin();
        System.out.println("########## _break_start2.5: " + av2.getAttendance_break_start());


        System.out.println("########## _IDはは: " + av2.getId());
        Attendance a = findOneInternal(av2.getId());

        System.out.println("########## _IDは: " + av2.getId());
        System.out.println("########## _break_start3: " + av2.getAttendance_break_start());


        AttendanceConverter.copyViewToModel2(a, av2);
        System.out.println("########## _break_start3.5: " + av2.getAttendance_break_start());


        em.getTransaction().commit();

    }







}
