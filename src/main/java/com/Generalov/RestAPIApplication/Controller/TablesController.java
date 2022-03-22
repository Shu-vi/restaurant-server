package com.Generalov.RestAPIApplication.Controller;

import com.Generalov.RestAPIApplication.Entity.TablesEntity;
import com.Generalov.RestAPIApplication.Repository.TablesRepository;
import com.Generalov.RestAPIApplication.Resource.TablesResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/tables")
public class TablesController {
    private final TablesRepository tablesRepository;

    public TablesController(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TablesResource[] getAll(@RequestParam(required = false) String name) {
        TablesEntity[] entities = tablesRepository.select();
        return Arrays.stream(entities)
                .map(entity -> {
                    TablesResource resource = new TablesResource(entity);
                    return resource;
                })
                .toArray(TablesResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TablesResource get(@PathVariable Integer id,
                     @RequestParam(required = false) Object expand) {
        TablesEntity entity = tablesRepository.select(id);
        if (entity == null) return null;
        TablesResource resource = new TablesResource(entity);
//        if (expand != null)
//            resource.setName(
//                    new EmployeeResource(employeeRepository.select(entity.getId()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    TablesResource post(@RequestBody TablesResource resource) {
        TablesEntity entity = tablesRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new TablesResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TablesResource put(@PathVariable Integer id,
                     @RequestBody TablesResource resource) {
        TablesEntity entity = tablesRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new TablesResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TablesResource delete(@PathVariable Integer id) {
        TablesEntity entity = tablesRepository.delete(id);
        if (entity == null) return null;
        return new TablesResource(entity);
    }
}
