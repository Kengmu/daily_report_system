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
                av.getAt_work(),
                av.getBreak_start(),
                av.getEnd_of_break(),
                av.getLeaving_work(),
                av.getCreatedAt(),
                av.getUpdatedAt());

    }







    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param a Attendanceのインスタンス
     * @return AttendanceViewのインスタンス
     */
    public static AttendanceView toView(Attendance a) {

        if (a == null) {
            return null;
        }

        return new AttendanceView(
                a.getId(),
                EmployeeConverter.toView(a.getEmployee()),
                a.getAttendanceDate(),
                a.getAt_work(),
                a.getBreak_start(),
                a.getEnd_of_break(),
                a.getAt_work(),
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
        a.setAt_work(av.getAt_work());
        a.setBreak_start(av.getBreak_start());
        a.setEnd_of_break(av.getEnd_of_break());
        a.setLeaving_work(av.getLeaving_work());
        a.setCreatedAt(av.getCreatedAt());
        a.setUpdatedAt(av.getUpdatedAt());

    }

}