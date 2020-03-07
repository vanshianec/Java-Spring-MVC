package heroes.workshop.web.api.controllers;

import heroes.workshop.service.models.items.ItemCreateServiceModel;
import heroes.workshop.service.services.ItemsService;
import heroes.workshop.web.api.models.ItemCreateRequestModel;
import heroes.workshop.web.api.models.ItemResponseModel;
import heroes.workshop.web.base.BaseController;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ItemsApiController extends BaseController {
    private final ItemsService itemsService;
    private final ModelMapper mapper;

    @GetMapping(value = "/api/items")
    public ResponseEntity<List<ItemResponseModel>> getItems(HttpSession session) {
        String username = getUsername(session);
        List<ItemResponseModel> result = itemsService.getItemsForUser(username)
                .stream()
                .map(item -> mapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/items-all")
    public List<ItemResponseModel> getItems() throws InterruptedException {
        return itemsService.getAll()
                .stream()
                .map(item -> mapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/items/add-to-user/{id}")
    public void buyItem(@PathVariable long id, HttpSession session, HttpServletResponse response) throws IOException {
        String username = getUsername(session);
        itemsService.addToUserById(id, username);
        String heroName = getHeroName(session);


        response.sendRedirect("/heroes/details/" + heroName);
    }

    @PostMapping("/api/items")
    public ResponseEntity<Void> create(ItemCreateRequestModel requestModel) {
        ItemCreateServiceModel serviceModel = mapper.map(requestModel, ItemCreateServiceModel.class);
        itemsService.create(serviceModel);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/items/merchant");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
