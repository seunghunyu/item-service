package hello.itemservice.config;

import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.jpa.JpaItemRepositoryV2;
import hello.itemservice.domain.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.domain.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservice.domain.repository.v2.ItemQueryRepository2;
import hello.itemservice.domain.repository.v2.ItemRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


@Configuration
@RequiredArgsConstructor
public class V2Config {

   private final EntityManager em;
   private final ItemRepositoryV2 itemRepositoryV2; //Spring Data JPA
   @Bean
   public ItemService itemService(){
       return new ItemServiceV2(itemRepositoryV2, itemQueryRepository2());
    }
   @Bean
   public ItemQueryRepository2 itemQueryRepository2(){
       return new ItemQueryRepository2(em);
   }
   @Bean
   public ItemRepository itemRepository(){
       return new JpaItemRepositoryV3(em);
    }

}