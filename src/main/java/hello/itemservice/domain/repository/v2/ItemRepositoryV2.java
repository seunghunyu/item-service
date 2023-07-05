package hello.itemservice.domain.repository.v2;

import hello.itemservice.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {

}
