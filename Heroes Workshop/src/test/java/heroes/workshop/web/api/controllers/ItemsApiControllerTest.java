package heroes.workshop.web.api.controllers;

import heroes.workshop.data.models.Item;
import heroes.workshop.data.repositories.ItemsRepository;
import heroes.workshop.web.api.models.ItemResponseModel;
import heroes.workshop.web.base.ApiTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemsApiControllerTest extends ApiTestBase {
//    @MockBean
//    ItemsRepository itemsRepository;
//
//    @Test
//    void getAllItems_whenItems_shouldReturnItems() {
//        Item item = new Item();
//        item.setId(1);
//        item.setName("Sword");
//        Mockito.when(itemsRepository.findAll())
//                .thenReturn(List.of(item));
//
//        ItemResponseModel[] result = getRestTemplate()
//                .getForObject(
//                        getFullUrl("/api/items-all"),
//                        ItemResponseModel[].class);
//
//        assertEquals(1, result.length);
//        assertEquals(item.getId(), result[0].getId());
//        assertEquals(item.getName(), result[0].getName());
//    }
}
