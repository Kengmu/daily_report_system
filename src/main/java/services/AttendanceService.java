package services;

import java.util.List;

import actions.views.AttendanceConverter;
import actions.views.AttendanceView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Attendance;





public class AttendanceService extends ServiceBase {

    /**
     * 指定した従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得しAttendanceViewのリストで返却する
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
     * 指定した従業員が作成した日報データの件数を取得し、返却する
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
     * 指定されたページ数の一覧画面に表示する日報データを取得し、AttendanceViewのリストで返却する
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
     * 日報テーブルのデータの件数を取得し、返却する
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
        em.persist(AttendanceConverter.toModel(av));
        em.getTransaction().commit();

    }



    /**
     * 勤怠データを更新する
     * @param av 勤怠データ
     */
    private void updateInternal(AttendanceView av) {

        em.getTransaction().begin();
        Attendance a = findOneInternal(av.getId());
        AttendanceConverter.copyViewToModel(a, av);
        em.getTransaction().commit();

    }





    public List<String> create(AttendanceView av) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }





    public List<String> update(AttendanceView av) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }



}
