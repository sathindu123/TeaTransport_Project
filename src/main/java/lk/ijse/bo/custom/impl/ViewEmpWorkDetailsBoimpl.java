package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.ViewEmpWorkDetailsDAO;
import lk.ijse.entity.ViewEmpWorkDetails;

import java.sql.SQLException;

public class ViewEmpWorkDetailsBoimpl implements ViewEmpWorkDetailsDAO {
    @Override
    public String save(ViewEmpWorkDetails viewEmpWorkDetails) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(ViewEmpWorkDetails viewEmpWorkDetails) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
//    public List<ViewEmpWorkDetailsDto> loadEmp() throws SQLException, ClassNotFoundException {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01";
//
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT e.empId, e.name, e.address, e.telnb, em.date " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, startDate);
//        statement.setString(2, endDate);
//
//        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()){
//            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
//                    rst.getString(3),rst.getInt(4),rst.getString(5));
//            empList.add(dto);
//        }
//        return empList;
//    }
//
//    public List<ViewEmpWorkDetailsDto>  searchEmployes(String id) throws SQLException, ClassNotFoundException {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01";
//
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql ="SELECT e.empId, e.name, e.address, e.telnb, em.date " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE e.empId = ? AND em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,id);
//        statement.setString(2, startDate);
//        statement.setString(3, endDate);
//
//        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()){
//            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
//                    rst.getString(3),rst.getInt(4),rst.getString(5));
//            empList.add(dto);
//
//        }
//        if(empList != null ){
//            return empList;
//        }
//
//        return null;
//    }
//
//    public int searchEmpCount(String id) throws SQLException, ClassNotFoundException {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01";
//
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT COUNT(em.date) " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE e.empId = ? " +
//                "AND em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1,id);
//        statement.setString(2,startDate);
//        statement.setString(3,endDate);
//
//        ResultSet rst = statement.executeQuery();
//        int count = 0;
//        if (rst.next()){
//            count = rst.getInt(1);
//
//        }
//        return count;
//    }
//
//    public List<ViewEmpWorkDetailsDto> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql ="SELECT e.empId, e.name, e.address, e.telnb, em.date " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, strDate);
//        statement.setString(2, endDate);
//
//        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()){
//            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
//                    rst.getString(3),rst.getInt(4),rst.getString(5));
//            empList.add(dto);
//
//        }
//        if(empList != null ){
//            return empList;
//        }
//
//        return null;
//    }
////Invoice eke emp table eka load karan code eka
//    public List<ViewEmpWorkDetailsDto> loadtblEmp() throws SQLException, ClassNotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01";
//
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT e.empId,e.name,em.date " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId "+
//                "WHERE em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date ";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1,startDate);
//        statement.setString(2,endDate);
//
//        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//        while (rst.next()){
//            ViewEmpWorkDetailsDto empDtos = new ViewEmpWorkDetailsDto(rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3));
//            empList.add(empDtos);
//        }
//        if (empList != null){
//            return empList;
//        }
//        return null;
//    }
//
//    public int getCountEmp() throws SQLException, ClassNotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//
//        String startDate = formattedDate + "-01";
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT COUNT(em.date) " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE " +
//                "em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        statement.setString(1,startDate);
//        statement.setString(2,endDate);
//
//        ResultSet rst = statement.executeQuery();
//        int count = 0;
//        if (rst.next()){
//            count = rst.getInt(1);
//
//        }
//        return count;
//    }
//
//    public List<ViewEmpWorkDetailsDto> searchEmpId(String id) throws SQLException, ClassNotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//
//        String startDate = formattedDate + "-01";
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT e.empId,e.name,em.date " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId "+
//                "WHERE em.empId = ? AND em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date ";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1,id);
//        statement.setString(2,startDate);
//        statement.setString(3,endDate);
//
//        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//        while (rst.next()){
//            ViewEmpWorkDetailsDto empDtos = new ViewEmpWorkDetailsDto(rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3));
//            empList.add(empDtos);
//        }
//        if (empList != null){
//            return empList;
//        }
//        return null;
//    }
//
//    public int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {
//
//            now = now.minusMonths(1);
//        }
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//
//        String startDate = formattedDate + "-01";
//
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT COUNT(em.date) " +
//                "FROM employee e " +
//                "JOIN empworddetails em ON e.empId = em.empId " +
//                "WHERE e.empId = ? AND " +
//                "em.date BETWEEN ? AND ? " +
//                "ORDER BY em.date";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1,id);
//        statement.setString(2,startDate);
//        statement.setString(3,endDate);
//
//        ResultSet rst = statement.executeQuery();
//        int count = 0;
//        if (rst.next()){
//            count = rst.getInt(1);
//
//        }
//        return count;
//    }
//


}


