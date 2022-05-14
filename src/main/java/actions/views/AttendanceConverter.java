package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Attendance;

/**
 * 勤怠データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class AttendanceConverter {




    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param av AttendanceViewのインスタンス
     * @return Attendanceのインスタンス
     */
    public static Attendance toModel(AttendanceView av) {

        return new Attendance(
                av.getId(),
                EmployeeConverter.toModel(av.getEmployee()),
                av.getAttendanceDate(),
                av.getAttendance_at_work(),
                av.getAttendance_break_start(),
                av.getAttendance_end_of_break(),
                av.getAttendance_leaving_work(),
                av.getCreatedAt(),
                av.getUpdatedAt());
    }







    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param a Attendanceのインスタンス
     * @return AttendanceViewのインスタンス
     */
    public static AttendanceView toView(Attendance a) {


        return new AttendanceView(
                a.getId(),
                EmployeeConverter.toView(a.getEmployee()),
                a.getAttendanceDate(),
                a.getAttendance_at_work(),
                a.getAttendance_break_start(),
                a.getAttendance_end_of_break(),
                a.getAttendance_leaving_work(),
                a.getCreatedAt(),
                a.getUpdatedAt());


    }


    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<AttendanceView> toViewList(List<Attendance> list) {
        List<AttendanceView> evs = new ArrayList<>();

        for (Attendance a : list) {
            evs.add(toView(a));
        }

        return evs;
    }




    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param a DTOモデル(コピー先)
     * @param av Viewモデル(コピー元)
     */
    public static void copyViewToModel(Attendance a, AttendanceView av) {
        a.setId(av.getId());
        a.setEmployee(EmployeeConverter.toModel(av.getEmployee()));
        a.setAttendanceDate(av.getAttendanceDate());
        a.setAttendance_at_work(av.getAttendance_at_work());
        a.setAttendance_break_start(av.getAttendance_break_start());
        a.setAttendance_end_of_break(av.getAttendance_end_of_break());
        a.setAttendance_leaving_work(av.getAttendance_leaving_work());
        a.setCreatedAt(av.getCreatedAt());
        a.setUpdatedAt(av.getUpdatedAt());
    }


    public static void copyViewToModel1(Attendance a, AttendanceView av1) {
        a.setId(av1.getId());
        a.setEmployee(EmployeeConverter.toModel(av1.getEmployee()));
        a.setAttendanceDate(av1.getAttendanceDate());
        a.setAttendance_at_work(av1.getAttendance_at_work());
        a.setAttendance_break_start(av1.getAttendance_break_start());
        a.setAttendance_end_of_break(av1.getAttendance_end_of_break());
        a.setAttendance_leaving_work(av1.getAttendance_leaving_work());
        a.setCreatedAt(av1.getCreatedAt());
        a.setUpdatedAt(av1.getUpdatedAt());

    }


    public static void copyViewToModel2(Attendance a, AttendanceView av2) {
        a.setId(av2.getId());
        a.setEmployee(EmployeeConverter.toModel(av2.getEmployee()));
        a.setAttendanceDate(av2.getAttendanceDate());
        a.setAttendance_at_work(av2.getAttendance_at_work());
        a.setAttendance_break_start(av2.getAttendance_break_start());
        a.setAttendance_end_of_break(av2.getAttendance_end_of_break());
        a.setAttendance_leaving_work(av2.getAttendance_leaving_work());
        a.setCreatedAt(av2.getCreatedAt());
        a.setUpdatedAt(av2.getUpdatedAt());

    }




}