package pl.com.navcity.service;

import pl.com.navcity.model.Driver;
import pl.com.navcity.repository.DriverRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepositoryImpl driverDao;

    @Override
    @PostConstruct
    public void createSomeDrivers() {
        System.out.println(" class " + getClass());
        driverDao.save(new Driver("John","Smith","ger65748"));
        driverDao.save(new Driver("Michael","Iverson","cdf435678"));
        driverDao.save(new Driver("Brad","Pitt","wll299384"));
        driverDao.save(new Driver("George","Staford","asd4596032"));
        driverDao.save(new Driver("Kevin","McCallister","wef003388"));
        driverDao.save(new Driver("Joffrey","Baratheon","hhy145678"));
        driverDao.save(new Driver("Eddard","Start","qqw223455"));
        driverDao.save(new Driver("Allan","Watson","vvb352342"));
        driverDao.save(new Driver("Mike","Bauer","wfw774566"));
        driverDao.save(new Driver("Michael","Bradley","afs235234"));
        driverDao.save(new Driver("Keanu","Reeves","gpe340293"));
        driverDao.save(new Driver("Amanda","Cloney","mkk345263"));
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.findAll();
    }

    @Override
    public Driver getDriverById(Integer driverId) {
        return driverDao.getDriverById(driverId);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverDao.save(driver);
    }

    @Override
    public void updateDriver(Integer driverId, Driver driver) {

        Driver foundedDriver = driverDao.getDriverById(driverId);
        foundedDriver.setFirtsName(driver.getFirtsName());
        foundedDriver.setLastName(driver.getLastName());
        foundedDriver.setLicence(driver.getLicence());
        driverDao.save(foundedDriver);
    }

    @Override
    public void deleteDriverById(Integer driverId) {
        driverDao.deleteById(driverId);
    }
}
