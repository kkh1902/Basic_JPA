package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 업데이트로 이해 (save = update 느낌)
    public void save(Item item){
        // 새로 생성한 객체  신규로 등록
        if (item.getId() == null){
            em.persist(item);
        }else {
            // DB에 등록되거나 어디에서 가져옴
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select  i from  Item i", Item.class)
                .getResultList();
    }
}
