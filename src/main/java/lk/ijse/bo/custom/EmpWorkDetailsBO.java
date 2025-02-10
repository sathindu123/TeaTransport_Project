package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmpWordDetailDto;
import lk.ijse.entity.EmpWordDetail;

public interface EmpWorkDetailsBO extends SuperBO {

    String addEmpDetails(EmpWordDetailDto empdto);
}
