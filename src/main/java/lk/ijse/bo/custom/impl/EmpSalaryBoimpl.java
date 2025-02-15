package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.EmpSalaryDAO;
import lk.ijse.bo.custom.EmpSalaryBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.entity.EmpSalary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmpSalaryBoimpl implements EmpSalaryBO {

    EmpSalaryDAO empSalaryDAO = (EmpSalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEESALARY);

    public String save(EmpSalary dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO employesalary VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, dto.getEmpId());
        statement.setString(2, dto.getDate().toString()); // Use formatted date here
        statement.setInt(3, dto.getPrice());

        int resp = statement.executeUpdate();
        return resp > 0 ? "sucsess" : "Fail";
    }



    public List<EmpSalaryDto> loadPayPriceEmp() throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));



        List<EmpSalary> ar = empSalaryDAO.loadPayPriceEmp(startDate,endDate);

        List<EmpSalaryDto> dtoList = new ArrayList<>();

        for (EmpSalary rate : ar) {
            EmpSalaryDto dto = new EmpSalaryDto(rate.getEmpId(), rate.getDate(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;

    }


    public int totalSalaryEmp(String id) throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return empSalaryDAO.totalSalaryEmp(id,startDate,endDate);
    }

    public List<EmpSalaryDto> getEmpTotPayPrice(String id) throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<EmpSalary> ar = empSalaryDAO.getEmpTotPayPrice(id,startDate,endDate);

        List<EmpSalaryDto> dtoList = new ArrayList<>();

        for (EmpSalary rate : ar) {
            EmpSalaryDto dto = new EmpSalaryDto(rate.getEmpId(), rate.getDate(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public int getSalaryAll() throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return empSalaryDAO.getSalaryAll(startDate,endDate);
    }

    @Override
    public String insertSalarayEmp(EmpSalaryDto dto) throws SQLException, ClassNotFoundException {
        return empSalaryDAO.save(new EmpSalary(dto.getEmpId(),dto.getDate(),dto.getPrice()));
    }
}
