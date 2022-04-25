package ru.geekbrains.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    @Query("select p " +
            " from Product p " +
            "where (p.cost like concat('%', :minCost, '%') or :minCost is null) and " +
            "      (p.cost like concat('%', :maxCost, '%') or :maxCost is null)")
    List<Product> findProductByFilter(@Param("minCost") Long minCost,
                                      @Param("maxCost") Long maxCost);



//    @Query("select u " +
//            " from Product u " +
//            "where (u.cost like concat('%', :cost, '%') or :cost is null) and " +
//            "      (u.cost like concat('%', :cost, '%') or :cost is null)")
//    List<Product> findProductByCostBetween(@Param("mincost") String mincost,
//                                          @Param("maxcost") String maxcost);


}
