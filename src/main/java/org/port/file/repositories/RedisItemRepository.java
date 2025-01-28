package org.port.file.repositories;

import org.port.file.entities.RedisItem;
import org.springframework.data.repository.CrudRepository;

public interface RedisItemRepository extends CrudRepository<RedisItem, String> {

}
