package com.Generalov.RestAPIApplication.Controller;

import com.Generalov.RestAPIApplication.Entity.MenuEntity;
import com.Generalov.RestAPIApplication.Repository.MenuRepository;
import com.Generalov.RestAPIApplication.Resource.MenuResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    MenuResource[] getAll(@RequestParam(required = false) String name) {
        MenuEntity[] entities = menuRepository.select();
        return Arrays.stream(entities)
                .map(entity -> {
                    MenuResource resource = new MenuResource(entity);
                    return resource;
                })
                .toArray(MenuResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    MenuResource get(@PathVariable Integer id) {
        MenuEntity entity = menuRepository.select(id);
        if (entity == null) return null;
        MenuResource resource = new MenuResource(entity);
//        if (expand != null)
//            resource.setName(
//                    new EmployeeResource(employeeRepository.select(entity.getId()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    MenuResource post(@RequestBody MenuResource resource) {
        MenuEntity entity = menuRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new MenuResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    MenuResource put(@PathVariable Integer id,
                         @RequestBody MenuResource resource) {
        MenuEntity entity = menuRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new MenuResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    MenuResource delete(@PathVariable Integer id) {
        MenuEntity entity = menuRepository.delete(id);
        if (entity == null) return null;
        return new MenuResource(entity);
    }
}
