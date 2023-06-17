package shubham.csvapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shubham.csvapi.entity.DataEntity;

@Repository
public interface DataRepository extends CrudRepository<DataEntity,Integer> {

    DataEntity findByCode(String code);
}
