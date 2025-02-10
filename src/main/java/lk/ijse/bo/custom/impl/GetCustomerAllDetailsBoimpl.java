package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.GetCustomerAllDetailsDAO;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;


public class GetCustomerAllDetailsBoimpl implements GetCustomerAllDetailsDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    @Override
    public String save(CustomerManage customerManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(CustomerManage customerManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

//    public List<getCustomerAllDetailsDto> getAllCustomerDetails() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        LocalDateTime now = LocalDateTime.now();
//
//        // Adjust the date range to include the previous month's details if within the first 10 days of the current month
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
//            now = now.minusMonths(1);
//            if (now.getDayOfMonth() == 1) {
//                now = now.minusYears(1);
//            }
//        }
//        String ss = "";
//        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("MM");
//        String formattedDate1 = now.format(dateFormatter1);
//        int monthNumber = Integer.parseInt(formattedDate1);
//        for (int i = 0; i < dateArray.length; i++) {
//            if (monthNumber == i + 1) {
//                System.out.println("pako");
//                if (i + 1 == 12) {
//                    ss = dateArray[11];
//                } else {
//                    ss = dateArray[i + 1];
//                }
//                break;
//            }
//        }
//        DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("yyyy");
//        String formattedDate2 = now.format(dateFormatter2);
//        ss = ss+formattedDate2;
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01"; // Start of the month
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // End of the month
//
//        now = now.plusMonths(1);
//        DateTimeFormatter dateFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate4 = now.format(dateFormatter4);
//        String endoDate = formattedDate4+"-10";
//
//
//        String sql = "SELECT " +
//                "    c.id, " +
//                "    c.name, " +
//                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount, " +
//                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, " +
//                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal, " +
//                "    COALESCE(f.fertilizeTotal, 0) AS fertilizeTotal, " +
//                "    COALESCE(p.otherTotal, 0) AS otherTotal " +
//                "FROM " +
//                "    customer c " +
//                "LEFT JOIN " +
//                "    (SELECT " +
//                "        custId, " +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal, " +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal " +
//                "     FROM productpurchasecustomer " +
//                "          WHERE date BETWEEN ? AND ?" +
//                "     GROUP BY custId) p ON c.id = p.custId " +
//                "LEFT JOIN " +
//                "    (SELECT " +
//                "        custId, " +
//                "        (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount " +
//                "     FROM teabaginventory " +
//                "     WHERE date BETWEEN ? AND ? " +
//                "     GROUP BY custId) t ON c.id = t.custId " +
//                "LEFT JOIN " +
//                "    (SELECT custId, SUM(monthPrice) AS totalAdvance " +
//                "     FROM advance " +
//                "      where month = ? " +
//                "     GROUP BY custId) a ON c.id = a.custId " +
//                "LEFT JOIN " +
//                "    (SELECT custId, SUM(monthPrice) AS fertilizeTotal " +
//                "     FROM pohorapurchasecustomer " +
//                "       where month = ?" +
//                "     GROUP BY custId) f ON c.id = f.custId ";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        // Set parameters for date range in the teabaginventory subquery
//        statement.setString(1, startDate);
//        statement.setString(2, endoDate);
//        statement.setString(3, startDate);
//        statement.setString(4, endDate);
//        statement.setString(5, ss);
//        statement.setString(6, ss);
//
//        ResultSet rst = statement.executeQuery();
//
//        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();
//
//        while (rst.next()) {
//            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
//                    rst.getString("id"),                // Customer ID
//                    rst.getString("name"),              // Customer Name
//                    rst.getInt("totalLeafAmount"),      // Total Leaf Amount
//                    rst.getInt("totalAdvance"),         // Total Advance
//                    rst.getDouble("teaPacketTotal"),    // Tea Packet Total
//                    rst.getDouble("fertilizeTotal"),    // Fertilize Total
//                    rst.getDouble("otherTotal")         // Other Total
//            );
//            custDtos.add(dto);
//        }
//
//        return custDtos;
//    }
//
//
//
//    public List<getCustomerAllDetailsDto> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT \n" +
//                "    c.id, \n" +
//                "    c.name, \n" +
//                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
//                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
//                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
//                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
//                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
//                "FROM \n" +
//                "    customer c\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT \n" +
//                "        custId, \n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
//                "     FROM productpurchasecustomer\n" +
//                "     GROUP BY custId) p ON c.id = p.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT custId, (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
//                "     FROM teabaginventory\n" +
//                "     GROUP BY custId) t ON c.id = t.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT custId, SUM(price) AS totalAdvance \n" +
//                "     FROM advance \n" +
//                "     GROUP BY custId) a ON c.id = a.custId\n" +
//                "WHERE c.id = ?";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        statement.setString(1, id);
//        ResultSet rst = statement.executeQuery();
//
//        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();
//
//        while (rst.next()) {
//
//            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
//                    rst.getString("id"),         // Customer ID
//                    rst.getString("name"),       // Customer Name
//                    rst.getInt("totalLeafAmount"),  // Total Leaf Amount
//                    rst.getInt("totalAdvance"),     // Total Advance
//                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
//                    rst.getDouble("fertilizeTotal"), // Fertilize Total
//                    rst.getDouble("otherTotal")      // Other Total
//            );
//            custDtos.add(dto);
//        }
//
//        return custDtos;
//    }
//
//    public List<getCustomerAllDetailsDto> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//
//        String sql = "SELECT \n" +
//                "    c.id, \n" +
//                "    c.name, \n" +
//                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
//                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
//                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
//                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
//                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
//                "FROM \n" +
//                "    customer c\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT \n" +
//                "        custId, \n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
//                "     FROM productpurchasecustomer\n" +
//                "     WHERE date BETWEEN ? AND ? \n" +
//                "     GROUP BY custId) p ON c.id = p.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT custId, (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
//                "     FROM teabaginventory\n" +
//                "     WHERE date BETWEEN ? AND ? \n" +
//                "     GROUP BY custId) t ON c.id = t.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT custId, SUM(price) AS totalAdvance \n" +
//                "     FROM advance \n" +
//                "     WHERE date BETWEEN ? AND ? \n" +
//                "     GROUP BY custId) a ON c.id = a.custId";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        statement.setString(1, strDate);
//        statement.setString(2, endDate);
//        statement.setString(3, strDate);
//        statement.setString(4, endDate);
//        statement.setString(5, strDate);
//        statement.setString(6, endDate);
//        ResultSet rst = statement.executeQuery();
//
//        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();
//
//        while (rst.next()) {
//
//            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
//                    rst.getString("id"),         // Customer ID
//                    rst.getString("name"),       // Customer Name
//                    rst.getInt("totalLeafAmount"),  // Total Leaf Amount
//                    rst.getInt("totalAdvance"),     // Total Advance
//                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
//                    rst.getDouble("fertilizeTotal"), // Fertilize Total
//                    rst.getDouble("otherTotal")      // Other Total
//            );
//            custDtos.add(dto);
//        }
//
//        return custDtos;
//    }
//
//    public List<getCustomerAllDetailsDto> gettot() throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getInstance().getConnection();
////
////        String sql = "SELECT \n" +
////                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
////                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
////                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
////                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
////                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
////                "FROM \n" +
////                "    customer c\n" +
////                "LEFT JOIN \n" +
////                "    (SELECT \n" +
////                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
////                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
////                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
////                "     FROM productpurchasecustomer\n" +
////                "     GROUP BY custId) p ON c.id = p.custId\n" +
////                "LEFT JOIN \n" +
////                "    (SELECT (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
////                "     FROM teabaginventory\n" +
////                "     GROUP BY custId) t ON c.id = t.custId\n" +
////                "LEFT JOIN \n" +
////                "    (SELECT SUM(price) AS totalAdvance \n" +
////                "     FROM advance \n" +
////                "     GROUP BY custId) a ON c.id = a.custId";
////
////
////        PreparedStatement statement = connection.prepareStatement(sql);
////
////
////        ResultSet rst = statement.executeQuery();
////
////
////        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();
//        return null;
//
//    }
}
