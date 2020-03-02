package spring_essentials.car_dealer_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_essentials.car_dealer_shop.data.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
