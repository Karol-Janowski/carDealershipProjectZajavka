package pl.zajavka.business.management;

import lombok.AllArgsConstructor;
import pl.zajavka.business.dao.management.CarDealershipManagementDAO;

@AllArgsConstructor
public class CarDealershipManagementService {

    private final CarDealershipManagementDAO carDealershipManagementDAO;

    public void purge() {
        carDealershipManagementDAO.purge();
    }
}
